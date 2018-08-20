package org.seckill.exception;

/**
 * 秒杀相关业务异常
 * Created by FisherBi on 2018/8/20.
 */
public class SecKillException extends RuntimeException{
    public SecKillException(String message) {
        super(message);
    }

    public SecKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
