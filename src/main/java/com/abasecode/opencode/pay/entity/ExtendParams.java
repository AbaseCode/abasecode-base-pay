package com.abasecode.opencode.pay.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */

@Data
@Accessors(chain = true)
public class ExtendParams {
    /**
     * 系统商编号
     * 该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
     */
    private String sysServiceProviderId;
    /**
     * 使用花呗分期要进行的分期数
     */
    private String hbFqNum;
    /**
     * 使用花呗分期需要卖家承担的手续费比例的百分值，传入100代表100%
     */
    private String hbFqSellerPercent;
    /**
     * 行业数据回流信息, 详见：地铁支付接口参数补充说明
     */
    private String industryRefluxInfo;
    /**
     * 卡类型
     */
    private String cardType;
    /**
     * 特殊场景下，允许商户指定交易展示的卖家名称
     */
    private String specifiedSellerName;
}
