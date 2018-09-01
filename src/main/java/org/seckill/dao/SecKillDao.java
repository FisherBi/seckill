package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    int reduceNumber(@Param("secKillId") long secKillId, @Param("killTime") Date killTime);

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
    List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 使用存储过程秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);
}
