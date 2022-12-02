package com.abasecode.opencode.pay.entity;

import lombok.Getter;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Getter
public enum PayStatus {
    /**
     * 交易创建，等待买家付款
     */
    WAIT_PAY(1,"等待付款"),
    /**
     * 交易后退款
     */
    TRADE_REFUND(2,"交易后退款"),
    /**
     * 未付款交易超时关闭，或支付完成后全额退款
     */
    TRADE_CLOSED(3,"交易关闭"),
    /**
     * 交易支付成功
     */
    TRADE_SUCCESS(4,"交易成功"),
    /**
     * 交易结束，不可退款
     */
    TRADE_FINISHED(5,"交易结束"),
    /**
     *
     */
    TRADE_FAILED(5,"交易失败"),
    /**
     * 未知状态
     */
    UNKNOWN(7,"未知状态，可能用了其他支付渠道");
    private int code;
    private String name;
    private PayStatus(int code,String name){
        this.code = code;
        this.name = name;
    }
}
