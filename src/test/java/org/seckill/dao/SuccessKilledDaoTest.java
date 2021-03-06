package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by FisherBi on 2018/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1001L;
        long phone = 13453234532L;
        int insertCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount="+insertCount);
    }

    @Test
    public void queryByIdWithSecKill() throws Exception {
        long id = 1001L;
        long phone = 13453234532L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSecKill());
    }

}