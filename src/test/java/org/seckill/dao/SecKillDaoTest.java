package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by FisherBi on 2018/8/19.
 * 配置spring和junit整合，junit启动时加载pringIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SecKillDaoTest {

    //注入Dao实现依赖
    @Autowired
    private SecKillDao secKillDao;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = secKillDao.reduceNumber(1000l, killTime);
        System.out.println("updateCount-->" + updateCount);
    }

    @Test
    public void queryAll() throws Exception {
        List<SecKill> secKillList = secKillDao.queryAll(0, 100);
        for(SecKill secKill : secKillList){
            System.out.println(secKill);
        }
    }

    /**
     * java没有保存形参的记录：queryAll(int offset, int limit) -> queryAll(arg0, arg1)
     * mybatis @param() 解决这个问题
     *  @throws Exception
     */
    @Test
    public void queryById() throws Exception {
        long id = 1000;
        SecKill secKill = secKillDao.queryById(id);
        System.out.println(secKill.getName());
        System.out.println(secKill);
    }

}