package me.gaigeshen.wechat.mp.spring.boot.autoconfigure;

import me.gaigeshen.wechat.mp.Config;
import me.gaigeshen.wechat.mp.RequestExecutor;
import me.gaigeshen.wechat.mp.card.ticket.CardSignatureCalculator;
import me.gaigeshen.wechat.mp.commons.HttpClientExecutor;
import me.gaigeshen.wechat.mp.jssdk.JsApiSignatureCalculator;
import me.gaigeshen.wechat.mp.message.DefaultMessageProcessorChain;
import me.gaigeshen.wechat.mp.message.MessageProcessor;
import me.gaigeshen.wechat.mp.message.MessageProcessorChain;
import me.gaigeshen.wechat.mp.message.ServletMessageFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Map;

/**
 * 微信公众号自动配置
 *
 * @author gaigeshen
 */
@Configuration
@EnableConfigurationProperties(WechatMpProperties.class)
@ServletComponentScan(basePackageClasses = { ServletMessageFilter.class })
public class WechatMpAutoConfiguration {
  private final WechatMpProperties properties;

  public WechatMpAutoConfiguration(WechatMpProperties properties) {
    this.properties = properties;
  }

  // 卡券签名相关
  @Bean
  @ConditionalOnProperty(prefix = WechatMpProperties.PREFIX, name = "enable-js-api")
  public CardSignatureCalculator cardSignatureCalculator(RequestExecutor executor) {
    return new CardSignatureCalculator(executor);
  }

  // 网页开发相关
  @Bean
  @ConditionalOnProperty(prefix = WechatMpProperties.PREFIX, name = "enable-card")
  public JsApiSignatureCalculator jsApiSignatureCalculator(RequestExecutor executor) {
    return new JsApiSignatureCalculator(executor);
  }

  // 用于消息处理
  @Bean
  public MessageProcessorChain messageProcessorChain(ApplicationContext ctx) {
    // 获取所有的消息处理器
    // 包含所有的事件消息处理器
    Map<String, MessageProcessor> processors = ctx.getBeansOfType(MessageProcessor.class);
    return new DefaultMessageProcessorChain(new ArrayList<>(processors.values()));
  }

  @Bean(destroyMethod = "close")
  public RequestExecutor wechatRequestExecutor(Config config) {
    WechatMpProperties.Http http = properties.getHttp();
    HttpClientExecutor httpclient = new HttpClientExecutor(
            http.getConnectionRequestTimeout(), http.getConnectTimeout(), http.getSocketTimeout());
    return new RequestExecutor(httpclient, config);
  }

  @Bean
  public Config wechatConfig() {
    String appid = properties.getAppid();
    String secret = properties.getSecret();
    String token = properties.getToken();
    String encodingAesKey = properties.getEncodingAesKey();
    Assert.state(StringUtils.isNotBlank(appid), "appid is required");
    Assert.state(StringUtils.isNotBlank(secret), "secret is required");
    Assert.state(StringUtils.isNotBlank(token), "token is required");
    return Config.builder().appid(appid).secret(secret).token(token).encodingAesKey(encodingAesKey).build();
  }
}
