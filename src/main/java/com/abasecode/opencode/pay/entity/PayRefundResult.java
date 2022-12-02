package com.abasecode.opencode.pay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class PayRefundResult implements Serializable {

    private static final long serialVersionUID = -8370328458203385256L;
    /**
     * 通道
     */
    private PayChannel payChannel;
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 渠道订单号(微信为transaction_id，支付宝为trade_no)
     */
    private String tradeNo;
    /**
     * 渠道退单号（支付宝同tradeNo，微信为refund_id）
     */
    private String refundNo;
    /**
     * 退款单号
     * 对应支付宝的 out_request_no
     * 对应微信的 out_refund_no
     */
    private String outRefundNo;
    /**
     * 退款时间
     * 成功才有
     * 格式如 2020-12-01 16:18:12
     */
    private String successTime;

    /**
     * 退款状态。
     * 微信枚举值：
     * SUCCESS：退款成功
     * CLOSED：退款关闭
     * PROCESSING：退款处理中
     * ABNORMAL：退款异常
     * 支付宝值：
     */
    private String status;

    /**
     * 退款总金额。
     * 指该笔交易累计已经退款成功的金额。
     */
    private int refundAmount;
    /**
     * 退款总金额。（小数格式）
     * 指该笔交易累计已经退款成功的金额。
     */
    private String refundAmountMoney;

    /**
     * 支付宝详情
     */
    private PayRefundResultAlipay refundResultAlipay;
    /**
     * 微信详情
     */
    private PayRefundResultWechat refundResultWechat;

}
