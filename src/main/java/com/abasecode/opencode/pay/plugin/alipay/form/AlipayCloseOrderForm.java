package com.abasecode.opencode.pay.plugin.alipay.form;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class AlipayCloseOrderForm implements Serializable {
    /**
     * 商户订单号（二选一）
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 支付宝交易号（二选一）
     */
    @JSONField(name = "trade_no")
    private String tradeNo;
    /**
     * 商家操作员编号 id，可选
     */
    @JSONField(name = "operator_id")
    private String operatorId;
}
