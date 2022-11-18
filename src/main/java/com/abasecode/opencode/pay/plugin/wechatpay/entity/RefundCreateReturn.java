package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
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
public class RefundCreateReturn extends BaseReturn implements Serializable {
    /**
     * 微信支付退款单号
     * string[1, 32]
     * 必
     */
    @JSONField(name = "refund_id")
    private String refundId;
    /**
     * 商户退款单号
     * string[1, 64]
     * 必
     */
    @JSONField(name = "out_refund_no")
    private String outRefundNo;
    /**
     * 微信支付订单号
     * string[1, 32]
     * 必
     */
    @JSONField(name = "transaction_id")
    private String transactionId;
    /**
     * 商户订单号
     * string[1, 32]
     * 必
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 退款渠道
     * string[1, 16]
     * 必
     */
    private String channel;
    /**
     * 退款入账账户
     * string[1, 64]
     * 必
     */
    @JSONField(name = "user_received_account")
    private String userReceivedAccount;
    /**
     * 退款成功时间
     * string[1, 64]
     * 否
     */
    @JSONField(name = "success_time")
    private String successTime;
    /**
     * 退款创建时间
     * string[1, 64]
     * 必
     */
    @JSONField(name = "create_time")
    private String createTime;
    /**
     * 退款状态
     * string[1, 32]
     * 必
     */
    private String status;
    /**
     * 资金账户
     * string[1, 32]
     * 否
     */
    @JSONField(name = "funds_account")
    private String fundsAccount;
    /**
     * 金额详细信息
     * 必
     */
    @JSONField(name = "amount")
    private AmountRefundReturn amount;

    // todo promotion_detail 否
}
