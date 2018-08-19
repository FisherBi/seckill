package org.seckill.dao;

import org.seckill.entity.SuccessKilled;

/**
 * Created by FisherBi on 2018/8/19.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复
     * @param secKillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(long secKillId, long userPhone);

    /**
     * 根据id查询SuccessKilled并携带SecKill对象实体
     * @return
     */
    SuccessKilled queryByIdWithSecKill(long SecKillId);

}
