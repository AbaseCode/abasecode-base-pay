package com.abasecode.opencode.pay.form;

import com.abasecode.opencode.pay.entity.PayChannel;
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
public class PayCloseForm implements Serializable {
    private static final long serialVersionUID = 8764108092461510085L;
    /**
     * 支付通道
     */
    @NotNull
    private PayChannel payChannel;
    /**
     * 用户订单号
     */
    @NotNull
    private String outTradeNo;
}
