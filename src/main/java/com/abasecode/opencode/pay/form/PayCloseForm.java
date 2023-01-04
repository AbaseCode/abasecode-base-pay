package com.abasecode.opencode.pay.form;

import com.abasecode.opencode.pay.entity.PayChannel;
import jakarta.validation.constraints.NotNull;
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
