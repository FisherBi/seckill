package org.seckill.dao;

import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;

/**
 * Created by FisherBi on 2018/8/19.
 */
public interface SecKillDao {

    /**
     * 减库存
     * @param secKillId
     * @param killTime
     * @return 如果影响行数>1,表示记录更新行数
     */
    int reduceNumber(long secKillId, Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param secKillId
     * @return
     */
    SecKill queryById(long secKillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<SecKill> queryAll(int offset, int limit);
}