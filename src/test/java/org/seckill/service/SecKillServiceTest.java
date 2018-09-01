package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SecKillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SecKillCloseException;
import org.seckill.exception.SecKillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by FisherBi on 2018/8/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SecKillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @Test
    public void getSecKillList() throws Exception {
        List<SecKill> list = secKillService.getSecKillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() throws Exception {
        SecKill secKill = secKillService.getById(1000);
        logger.info("seckill={}", secKill);
    }

    @Test
    public void testSecKillLogic() throws Exception {
        long id = 1000;
        Exposer exposer = secKillService.exportSecKillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 12343242141L;
            String md5 = exposer.getMd5();
            try {
                SecKillExecution secKillExecution = secKillService.executeSecKill(id, phone, md5);
                logger.info("result={}", secKillExecution);
            } catch (RepeatKillException | SecKillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void executeSecKill() throws Exception {
        long id = 1000;
        long phone = 12343242141L;
        String md5 = "75ea0578d11656b498fefc2ce08642f1";
        try {
            SecKillExecution secKillExecution = secKillService.executeSecKill(id, phone, md5);
            logger.info("result={}", secKillExecution);
        } catch (RepeatKillException | SecKillCloseException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void executeSecKillProcedure(){
        long secKillId= 1002L;
        long phone = 1362734321;
        Exposer exposer = secKillService.exportSecKillUrl(secKillId);
        if(exposer.isExposed()){
            String md5 = exposer.getMd5();
            SecKillExecution secKillExecution = secKillService.executeSecKillProcedure(secKillId, phone, md5);
            logger.info(secKillExecution.getStateInfo());
        }

    }

}