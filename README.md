# About

AbaseCode open source project is a set of open source collection . Including the base package , toolkit , security
package , token package , payment package , excel package and so on.

Open source project components to do out of the box, to facilitate more developers to save duplication of work, more
focused on business logic code writing.

I am Jon, a developer who focuses on learning and spreading technical knowledge. I hope these toolkits can help you, and
welcome any friends to join this open source project.

project homepage : https://abasecode.com

project github : https://github.com/abasecode

Jon's blog : https://jon.wiki

e-mail: ijonso123@gmail.com

# About abasecode-base-pay

This is an aggregated payment tool for WeChat Pay and Alipay, implementing payment (callback), payment query, refund (callback), and refund query functions.

# Quick Start

## Step 1: setting the pom.xml add dependency

``` xml
<dependency>
    <groupId>com.abasecode.opencode</groupId>
    <artifactId>abasecode-base-pay</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Step 2: setting application.yaml

``` yaml
app:
  pay:
    wechat:
      app-appid: XXXX
    alipay:
      appid: xxxx
```
## Step 3: annotation

```java
@EnableBasePay
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```