package com.abasecode.opencode.pay.config;

import com.abasecode.opencode.pay.plugin.alipay.constant.AliConstant;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Jon
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/abasecode">project github</a>
 * url: <a href="https://abasecode.com">AbaseCode.com</a>
 */
@Component
@Configuration
@Data
public class AlipayProperties implements ApplicationRunner{
    @Bean
    @ConfigurationProperties(prefix = "app.pay.alipay")
    public AliConfigParam aliConfigParam(){
        return new AliConfigParam();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AliConstant.appid = this.aliConfigParam().appid;
        AliConstant.appPrivateKey =this.aliConfigParam().appPrivateKey;
        AliConstant.appPublicKey =this.aliConfigParam().appPublicKey;
        AliConstant.alipayPublicKey =this.aliConfigParam().alipayPublicKey;
        AliConstant.encryptKey=this.aliConfigParam().encryptKey;
        AliConstant.encryptType=this.aliConfigParam().encryptType;
        AliConstant.payNotifyUrl= this.aliConfigParam().baseDomain +this.aliConfigParam().payNotifyUrl;
        AliConstant.payReturnUrl=this.aliConfigParam().baseDomain +this.aliConfigParam().payReturnUrl;
    }

    @Setter
    @Getter
    public static class AliConfigParam {
        private String appid;
        private String appPrivateKey;
        private String appPublicKey;
        private String alipayPublicKey;
        private String payNotifyUrl;
        private String payReturnUrl;
        private String baseDomain;
        private String encryptKey;
        private String encryptType;
    }
}
