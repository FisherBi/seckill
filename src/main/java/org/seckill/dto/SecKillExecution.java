package org.seckill.dto;

import org.seckill.entity.SuccessKilled;

/**
 * 封装秒杀执行后的结果
 * Created by FisherBi on 2018/8/20.
 */
public class SecKillExecution {

    private long secKillId;

    //秒杀执行结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //秒杀成功对象
    private SuccessKilled successKilled;

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    public SecKillExecution(long secKillId, int state, String stateInfo, SuccessKilled successKilled) {
        this.secKillId = secKillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successKilled = successKilled;
    }

    public SecKillExecution(long secKillId, int state, String stateInfo) {
        this.secKillId = secKillId;
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
