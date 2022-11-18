# 关于AbaseCode

AbaseCode OpenCode是一套开源合集。包括基础包、工具包、安全包、token包、支付包、excel包等。

开源项目的组件做到开箱即用，方便更多的开发者节省重复的工作，更专注于业务逻辑代码编写。

我是Jon，一名全栈开发者，专注于学习和传播技术知识。希望这些工具包能够帮上你，欢迎有的朋友加入这个开源项目。

project homepage : https://abasecode.com

project github : https://github.com/abasecode

Jon's blog : https://jon.wiki

e-mail: ijonso123@gmail.com

# About abasecode-base-pay

这是一个封装了支付宝和微信的支付库。

# 快速开始

## Step 1: setting the pom.xml add dependency

``` xml
<dependency>
    <groupId>com.abasecode.opencode</groupId>
    <artifactId>abasecode-base-pay</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Step 2: 配置 application.yaml

``` yaml
app:
  pay:
    wechat:
      app-appid: XXXX
    alipay:
      appid: xxxx
```
## Step 3: 注解 

```java
@EnableBasePay
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```