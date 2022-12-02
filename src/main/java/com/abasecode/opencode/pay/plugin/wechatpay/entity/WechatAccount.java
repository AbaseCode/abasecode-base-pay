package com.abasecode.opencode.pay.plugin.wechatpay.entity;

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
public class WechatAccount implements Serializable {
    private static final long serialVersionUID = 1570869504383128647L;
    /**
     * 出资账户类型
     * 下面枚举值多选一。
     * 枚举值：
     * AVAILABLE : 可用余额
     * UNAVAILABLE : 不可用余额
     */
    private String account;
    /**
     * 金额
     * 对应账户出资金额
     */
    private int amount;
}
