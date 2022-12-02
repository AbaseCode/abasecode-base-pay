package com.abasecode.opencode.pay.plugin.alipay.form;

import lombok.Data;
import lombok.experimental.Accessors;

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
public class AlipayRefundForm implements Serializable {
    /**
     * 商户订单号
     */
    private String outTradeNo;
    /**
     *支付宝交易号
     */
    private String tradeNo;
    /**
     * 退款金额。
     * 需要退款的金额，该金额不能大于订单金额，单位为元，支持两位小数。
     * 注：如果正向交易使用了营销，该退款金额包含营销金额，支付宝会按业务规则分配营销和买家自有资金分别退多少，默认优先退买家的自有资金。
     * 如交易总金额100元，用户支付时使用了80元自有资金和20元无资金流的营销券，商家实际收款80元。
     * 如果首次请求退款60元，则60元全部从商家收款资金扣除退回给用户自有资产；
     * 如果再请求退款40元，则从商家收款资金扣除20元退回用户资产以及把20元的营销券退回给用户（券是否可再使用取决于券的规则配置）。
     */
    private String refundAmount;
    /**
     * 退款原因说明。
     * 商家自定义，将在会在商户和用户的pc退款账单详情中展示
     */
    private String refundReason;
    /**
     * 退款请求号。
     * 标识一次退款请求，需要保证在交易号下唯一，如需部分退款，则此参数必传。
     * 针对同一次退款请求，如果调用接口失败或异常了，重试时需要保证退款请求号不能变更，防止该笔交易重复退款。支付宝会保证同样的退款请求号多次请求只会退一次。
     */
    private String outRequestNo;
    /**
     * 查询选项。
     * 商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。支持：
     * refund_detail_item_list：退款使用的资金渠道；
     * deposit_back_info：触发银行卡冲退信息通知
     */
    private List<String> queryOptions;
}
