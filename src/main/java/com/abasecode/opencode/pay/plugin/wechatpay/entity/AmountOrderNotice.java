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
public class AmountOrderNotice implements Serializable {
    /**
     * 订单总金额，单位为分。
     * int
     * 必
     */
    private int total;
    /**
     * CNY：人民币，境内商户号仅支持人民币。
     * string[1,16]
     * 必
     */
    private String currency;

    /**
     * 用户支付金额
     * int
     * 必
     */
    @JsonProperty("payer_total")
    private int payerTotal;

    /**
     * 用户支付币种
     * string[1,16]
     * 必
     */
    @JsonProperty("payer_currency")
    private String payerCurrency;
}
