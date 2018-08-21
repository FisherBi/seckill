package org.seckill.service.impl;

import org.seckill.dao.SecKillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SecKillExecution;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SecKillCloseException;
import org.seckill.exception.SecKillException;
import org.seckill.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by FisherBi on 2018/8/21.
 */
public class SecKillServiceImpl implements SecKillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private SecKillDao secKillDao;

    private SuccessKilledDao successKilledDao;

    //MD5盐值字符串，用于混淆MD5
    private final String salt = "asdfwqrdasfdsf2342344$#@$@$@$##@$adf3";

    @Override
    public List<SecKill> getSecKillList() {
        return secKillDao.queryAll(0, 4);
    }

    @Override
    public SecKill getById(long secKillId) {
        return secKillDao.queryById(secKillId);
    }

    @Override
    public Exposer exportSecKillUrl(long secKillId) {
        SecKill secKill = secKillDao.queryById(secKillId);
        if (secKill == null) {
            return new Exposer(false, secKillId);
        }
        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, secKillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(secKillId);
        return new Exposer(true, md5, secKillId);
    }

    private String getMD5(long secKillId) {
        String base = secKillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    public SecKillExecution executeSecKill(long secKillId, long userPhone, String md5) throws SecKillException, RepeatKillException, SecKillCloseException {
        if (md5 == null || md5.equals(getMD5(secKillId))) {
            throw new SecKillException("seckill data rewrite");
        }
        //执行秒杀逻辑：减库存+记录购买行为
        Date nowTime = new Date();
        try {
            int updateCount = secKillDao.reduceNumber(secKillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录
                throw new SecKillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);
                //唯一：seckillId, userPhone
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(secKillId, userPhone);
                    return new SecKillExecution(secKillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SecKillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //编译期异常转化为运行期异常
            throw new SecKillException("seckill inner error:" + e.getMessage());
        }
    }
}
