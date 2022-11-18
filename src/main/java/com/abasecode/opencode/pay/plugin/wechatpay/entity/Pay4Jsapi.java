package com.abasecode.opencode.pay.plugin.wechatpay.entity;

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
public class Pay4Jsapi implements Serializable {
    /**
     * 应用ID
     * string[1,32]
     * 必
     */
    private String appid;
    /**
     * 直连商户号
     * string[1,32]
     * 必
     */
    private String mchid;
    /**
     * 商品描述
     * string[1,127]
     * 必
     */
    private String description;
    /**
     * 商户订单号
     * string[6,32]
     * 必
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    /**
     * 通知地址
     * string[1,256]
     * 必
     */
    @JSONField(name = "notify_url")
    private String notifyUrl;

    /**
     * 订单优惠标记
     * string[1,32]
     * 否
     */
    @JSONField(name = "goods_tag")
    private String goodsTag;

    /**
     * 交易结束时间
     * string[1,64]
     * 否
     */
    @JSONField(name = "time_expire")
    private String timeExpire;

    /**
     * 附加数据
     * string[1,128]
     * 否
     */
    private String attach;
    /**
     * 订单金额信息
     * 是
     */
    private AmountOrder amount;

    /**
     * 支付者信息
     * 是
     */
    private Payer payer;

}
