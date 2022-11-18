package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Data
@Accessors(chain = true)
@ToString
public class RefundNoticeResourceOrigin implements Serializable {
    /**
     * 直连商户号
     * string[1,32]
     * 必
     */
    @JsonProperty("mchid")
    private String mchid;
    /**
     * 商户订单号
     * string[1,32]
     * 必
     */
    @JsonProperty("out_trade_no")
    private String outTradeNo;
    /**
     * 微信支付订单号
     * string[1,32]
     * 必
     */
    @JsonProperty("transaction_id")
    private String transactionId;
    /**
     * 商户退款单号
     * string[1,64]
     * 必
     */
    @JsonProperty("out_refund_no")
    private String outRefundNo;
    /**
     * 微信支付退款单号
     * string[1,32]
     * 必
     */
    @JsonProperty("refund_id")
    private String refundId;
    /**
     * 退款状态
     * string[1,16]
     * 必
     */
    @JsonProperty("refund_status")
    private String refundStatus;
    /**
     * 退款成功时间
     * string[1,64]
     * 否
     */
    @JsonProperty("success_time")
    private String successTime;
    /**
     * 退款入账账户
     * string[1,64]
     * 必
     */
    @JsonProperty("user_received_account")
    private String userReceivedAccount;
    /**
     * 金额信息
     * 必
     */
    @JsonProperty("amount")
    private AmountRefundNotice amount;
}

