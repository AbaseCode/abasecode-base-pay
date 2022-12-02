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
public class PayNotify implements Serializable {
    private static final long serialVersionUID = 5851302634186636366L;
    /**
     * 通道
     */
    private PayChannel payChannel;
    /**
     * 状态
     * 付款（支付宝，微信）
     * 退单（支付宝，微信）
     * 支付宝的（trade_status）
     * 微信的的（trade_state）和（refund_status）
     */
    private String status;
    /**
     * 商户订单号
     * 付款（支付宝，微信）
     * 退单（支付宝，微信）
     * 商户订单号
     */
    private String outTradeNo;
    /**
     * 支付通道订单号
     * 付款（支付宝，微信）
     * 退单（支付宝，微信）
     * 渠道订单号
     * 支付宝（trade_no）
     * 微信（transaction_id）
     */
    private String tradeNo;
    /**
     * 付款（支付宝，微信）
     * 退单（支付宝）微信退款返回空，“”
     * 应用id
     */
    private String appId="";
    /**
     * 商户号
     * 付款（支付宝，微信）
     * 退单（支付宝，微信）
     * mchid（微信）
     * seller_id（支付宝）
     */
    private String sellerId;

    /**
     * 付款（支付宝，微信）
     * 退单（支付宝，微信）
     * 支付宝的（totalAmount)
     * 微信的（amount.total)
     */
    private int totalAmount;
    /**
     * 付款（支付宝，微信）
     * 退单（支付宝，微信）
     * 支付宝的totalAmount
     * 微信的（amount.total)
     */
    private String totalAmountMoney;

    /**
     * 付款默认为0
     * 退单（支付宝=refund_fee，微信=refund）
     */
    private int refundAmount=0;
    /**
     * 付款（默认为0.00）
     * 退单（支付宝=refund_fee，微信=refund）
     */
    private String refundAmountMoney="";

    /**
     * 付款（支付宝，微信）
     * 退单（支付宝），退单时微信返回空。。
     * 支付宝的 gmt_payment
     * 微信的 success_time
     */
    private String payTime;
    /**
     * 付款（支付宝，微信）
     * 退单（支付宝，微信）
     * 付款通知时间、退单通知时间
     * 支付宝的 notify_time
     * 微信的 create_time
     */
    private String notifyTime;

    /**
     * 退款时间
     * 付款（空，空）
     * 退单（支付宝=gmt_refund，微信=success_time）
     */
    private String refundTime;
    /**
     * 成功0，失败-1
     */
    private int code;
    /**
     * 1=付款，2=退款
     */
    private int type;

}
