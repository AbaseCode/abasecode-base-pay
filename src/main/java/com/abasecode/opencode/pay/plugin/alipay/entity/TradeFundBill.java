package com.abasecode.opencode.pay.plugin.alipay.entity;

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
public class TradeFundBill implements Serializable {
    private static final long serialVersionUID = 628157169205539619L;
    /**
     * 交易使用的资金渠道，详见 支付渠道列表
     */
    @JSONField(name = "fund_channel")
    private String fundChannel;
    /**
     * 该支付工具类型所使用的金额
     */
    private String amount;
    /**
     * 渠道实际付款金额
     */
    @JSONField(name = "real_amount")
    private String realAmount;
    /**
     * 渠道所使用的资金类型,目前只在资金渠道(fund_channel)是银行卡渠道(BANKCARD)的情况下才返回该信息(DEBIT_CARD:借记卡,CREDIT_CARD:信用卡,MIXED_CARD:借贷合一卡)
     */
    @JSONField(name = "fund_type")
    private String fundType;
}
