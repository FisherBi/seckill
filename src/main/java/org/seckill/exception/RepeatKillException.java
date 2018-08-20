package org.seckill.exception;

/**
 * 重复秒杀异常(运行期异常,spring声明事务只接收运行期异常回滚策略)
 * Created by FisherBi on 2018/8/20.
 */
public class RepeatKillException extends SecKillException{
    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message, Throwable cause){
        super(message, cause);
    }
}
