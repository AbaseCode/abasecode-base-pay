package com.abasecode.opencode.pay.entity;

import com.abasecode.opencode.pay.plugin.wechatpay.entity.AmountRefundReturn;
import com.abasecode.opencode.pay.plugin.wechatpay.entity.WechatPromotion;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jon
 * url: <a href="https://jon.wiki">Jon's blog</a>
 */
@Data
@Accessors(chain = true)
public class PayRefundResultWechat implements Serializable {
    private static final long serialVersionUID = -157566418752030367L;

    /**
     * 取当前退款单的退款入账方，有以下几种情况：
     * 1）退回银行卡：{银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱:支付用户零钱
     * 3）退还商户:商户基本账户商户结算银行账户
     * 4）退回支付用户零钱通:支付用户零钱通
     */
    private String userReceivedAccount;

    /**
     * 退款所使用资金对应的资金账户类型
     * 枚举值：
     * UNSETTLED : 未结算资金
     * AVAILABLE : 可用余额
     * UNAVAILABLE : 不可用余额
     * OPERATION : 运营户
     * BASIC : 基本账户（含可用余额和不可用余额）
     */
    private String fundsAccount;
    /**
     * 微信退款信息
     */
    private AmountRefundReturn amount;

    /**
     * 优惠退款信息
     */
    private List<WechatPromotion> promotionDetail;
}
