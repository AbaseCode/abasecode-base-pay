package com.abasecode.opencode.pay.form;

import com.abasecode.opencode.pay.entity.PayChannel;
import com.abasecode.opencode.pay.entity.PayType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */

@Data
@Accessors(chain = true)
public class PayRefundQueryForm implements Serializable {
    private static final long serialVersionUID = 7527239355089100054L;
    /**
     * 支付通道
     */
    @NotNull
    private PayChannel payChannel;
    /**
     * 支付类型
     */
    @NotNull
    private PayType payType;
    /**
     * 商户退单号
     */
    @NotNull
    private String outRefundNo;
    /**
     * 商户单号
     */
    @NotNull
    private String outTradeNo;
}
