package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SecKillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SecKillCloseException;
import org.seckill.exception.SecKillException;

import java.util.List;

/**
 * 业务接口
 * Created by FisherBi on 2018/8/20.
 */
public interface SecKillService{

    List<SecKill> getSecKillList();

    SecKill getById(long secKillId);

    /**
     * 输出秒杀接口地址，否则输出系统时间和秒杀时间
     * @param secKillId
     */
    Exposer exportSecKillUrl(long secKillId);

    /**
     * 执行秒杀操作
     * @param secKillId
     * @param userPhone
     * @param md5
     */
    SecKillExecution executeSecKill(long secKillId, long userPhone, String md5)
        throws SecKillException, RepeatKillException, SecKillCloseException;
}
