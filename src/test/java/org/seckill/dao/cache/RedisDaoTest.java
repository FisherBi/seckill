package org.seckill.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SecKillDao;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by FisherBi on 2018/8/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RedisDaoTest {

    private long id = 1001;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SecKillDao secKillDao;


    @Test
    public void testSecKill() throws Exception {
        //get and put
        SecKill secKill = redisDao.getSecKill(id);
        if(secKill == null){
            secKill = secKillDao.queryById(id);
            if(secKill != null){
               String result = redisDao.putSecKill(secKill);
                System.out.println(result);
                SecKill secKill1 = redisDao.getSecKill(id);
                System.out.println(secKill);
            }
        }
    }

}