package com.abasecode.opencode.pay.plugin.wechatpay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PayNoticeResourceOrigin implements Serializable {
    /**
     * 应用ID
     * string[1,32]
     * 必
     */
    @JsonProperty("appid")
    private String appid;
    /**
     * 商户号
     * string[1,32]
     * 必
     */
    @JsonProperty("mchid")
    private String mchid;
    /**
     * 商户订单号
     * string[6,32]
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
     * 交易类型
     * string[1,16]
     * 必
     */
    @JsonProperty("trade_type")
    private String tradeType;
    /**
     * 交易状态
     * string[1,32]
     * 必
     */
    @JsonProperty("trade_state")
    private String tradeState;
    /**
     * 交易状态描述
     * string[1,256]
     * 必
     */
    @JsonProperty("trade_state_desc")
    private String tradeStateDesc;
    /**
     * 付款银行
     * string[1,32]
     * 必
     */
    @JsonProperty("bank_type")
    private String bankType;
    /**
     * 附加数据
     * string[1,128]
     * 否
     */
    @JsonProperty("attach")
    private String attach;
    /**
     * 支付完成时间
     * string[1,64]
     * 必
     */
    @JsonProperty("success_time")
    private String successTime;
    /**
     * 支付者信息
     * 必
     */
    @JsonProperty("payer")
    private Payer payer;
    /**
     * 订单金额信息
     */
    @JsonProperty("amount")
    private AmountOrderNotice amount;
}
