package com.abasecode.opencode.pay.plugin.wechatpay.form;


import com.abasecode.opencode.pay.plugin.wechatpay.entity.RefundNotice;
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
public class FormRefundNotice extends RefundNotice implements Serializable {
}
