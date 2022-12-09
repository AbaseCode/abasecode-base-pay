package com.abasecode.opencode.pay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Data
@Accessors(chain = true)
public class BaseOrder implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * 订单号，32位
     */
    @NotNull
    private String outTradeNo;
    /**
     * 总价（分）
     */
    @NotNull
    private int amount;
    /**
     * 商品标题
     */
    @NotNull
    private String subject;
    /**
     * 商品详情，可空。支付宝或微信均适用
     */
    private List<BaseGoodDetail> details;
    /**
     * 交易结束时间。支付宝或微信均适用。
     * 非必须
     * 格式：yyyy-MM-dd HH:mm:ss
     * 例如：2022-11-28 11:59:59
     * 时区为东八区
     */
    private String timeExpire;
    /**
     * 微信，支付宝:公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。
     * 支付宝只会在同步返回（包括跳转回商户网站）和异步通知时将该参数原样返回。
     * 本参数必须进行UrlEncode之后才可以发送给支付宝。
     * 微信只能传128个字节。
     */
    private String otherParams;

}
