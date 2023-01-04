package com.abasecode.opencode.pay.config;

import com.abasecode.opencode.pay.plugin.wechatpay.constant.WechatConstant;
import com.abasecode.opencode.pay.plugin.wechatpay.util.WechatUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.cert.X509Certificate;

import static com.abasecode.opencode.pay.util.BaseUtils.getURI;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Component
@Configuration
public class WechatProperties implements ApplicationRunner {
    @Bean
    @ConfigurationProperties(prefix = "app.pay.wechat")
    public WechatConfigParam wechatConfigParam() {
        return new WechatConfigParam();
    }

    @Getter
    @Setter
    public static class WechatConfigParam {
        private String appAppid;
        private String microAppid;
        private String mpAppid;
        private String appSecret;
        private String microSecret;
        private String mpSecret;
        private String mchid;
        private String certPath;
        private String certKey;
        private String v3key;
        private String baseDomain;
        private String payNotifyUrl;
        private String refundNotifyUrl;
        private String mpCodeReturnUrl;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WechatConstant.wechatAppAppid = this.wechatConfigParam().appAppid;
        WechatConstant.wechatMpAppid = this.wechatConfigParam().mpAppid;
        WechatConstant.wechatMicroAppid = this.wechatConfigParam().microAppid;
        WechatConstant.wechatAppSecret = this.wechatConfigParam().appSecret;
        WechatConstant.wechatMpSecret = this.wechatConfigParam().mpSecret;
        WechatConstant.wechatMicroSecret = this.wechatConfigParam().microSecret;

        WechatConstant.wechatMchid = this.wechatConfigParam().mchid;
        WechatConstant.wechatBaseDomain = this.wechatConfigParam().baseDomain;
        WechatConstant.wechatCertUrl = this.wechatConfigParam().certPath;
        WechatConstant.wechatCertKey = this.wechatConfigParam().certKey;
        WechatConstant.wechatPayNotifyUrl = getURI(WechatConstant.wechatBaseDomain, this.wechatConfigParam().payNotifyUrl);
        WechatConstant.wechatRefundNotifyUrl = getURI(WechatConstant.wechatBaseDomain, this.wechatConfigParam().refundNotifyUrl);
        WechatConstant.wechatCodeReturnUrl = getURI(WechatConstant.wechatBaseDomain, this.wechatConfigParam().mpCodeReturnUrl);
        WechatConstant.wechatV3key = this.wechatConfigParam().v3key;
        WechatConstant.wechatPrivateKey = WechatUtils.getPrivateKey();
        WechatConstant.wechatSerialNo = WechatUtils.getSerialNumber();
        WechatConstant.wechatCertificateMap = WechatUtils.refreshCertificate();
        X509Certificate certificate = WechatUtils.getCertificate();
    }

}