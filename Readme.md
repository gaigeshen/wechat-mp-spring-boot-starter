## 微信公众号开发工具自动配置
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/gaigeshen/wechat-mp-spring-boot-starter.svg?branch=develop)](https://travis-ci.org/gaigeshen/wechat-mp-spring-boot-starter)
[![Maven Central](https://img.shields.io/maven-central/v/me.gaigeshen.wechat/wechat-mp-spring-boot-starter.svg)](http://mvnrepository.com/artifact/me.gaigeshen.wechat/wechat-mp-spring-boot-starter)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/me.gaigeshen.wechat/wechat-mp-spring-boot-starter.svg)](https://oss.sonatype.org/content/repositories/snapshots/me/gaigeshen/wechat/wechat-mp-spring-boot-starter)
[![GitHub last commit](https://img.shields.io/github/last-commit/gaigeshen/wechat-mp-spring-boot-starter.svg)](https://github.com/gaigeshen/wechat-mp-spring-boot-starter/commits)

- 直接添加依赖即可，替换具体的版本号
```
<dependency>
  <groupId>me.gaigeshen.wechat</groupId>
  <artifactId>wechat-mp-spring-boot-starter</artifactId>
  <version>${VERSION}</version>
</dependency>
```

- 快速配置项目，必须提供`appid`，`secret`，和`token`
```
wechat.mp:
  appid: ${your-appid}
  secret: ${your-secret}
  token: ${your-token}
  // 如果提供了此项，接收到的消息以及回复的消息都会先进行解密或者加密
  encoding-aes-key: ${your-encoding-aes-key}
  // 是否开启jsapi签名的支持
  enable-js-api: ${true|false}
  // 是否开启卡券签名的支持
  enable-card: ${true|false}
```

- 已经开启消息处理，请实现对应的`MessageProcessor`且设为*SpringBean*
- 在你的类中注入`RequestExecutor`用于主动请求接口
- 如果开启了jsapi签名的支持，在你的类中注入`JsApiSignatureCalculator`用于计算签名
- 如果开启了卡券签名的支持，在你的类中注入`CardSignatureCalculator`用于计算签名