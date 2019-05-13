package me.gaigeshen.wechat.mp.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号配置属性
 *
 * @author gaigeshen
 */
@ConfigurationProperties(WechatMpProperties.PREFIX)
public class WechatMpProperties {

  static final String PREFIX = "wechat.mp";

  private String appid;
  private String secret;
  private String token;
  private String encodingAesKey;

  /**
   * 如果需要网页开发，例如网页授权，需要开启此项
   */
  private boolean enableJsApi = false;
  /**
   * 如果需要卡券开发，需要开启此项
   */
  private boolean enableCard = false;

  private Http http = new Http();

  public static class Http {
    private int connectionRequestTimeout = 2000;
    private int connectTimeout = 2000;
    private int socketTimeout = 3000;

    public int getConnectionRequestTimeout() {
      return connectionRequestTimeout;
    }
    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
      this.connectionRequestTimeout = connectionRequestTimeout;
    }
    public int getConnectTimeout() {
      return connectTimeout;
    }
    public void setConnectTimeout(int connectTimeout) {
      this.connectTimeout = connectTimeout;
    }
    public int getSocketTimeout() {
      return socketTimeout;
    }
    public void setSocketTimeout(int socketTimeout) {
      this.socketTimeout = socketTimeout;
    }
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getEncodingAesKey() {
    return encodingAesKey;
  }

  public void setEncodingAesKey(String encodingAesKey) {
    this.encodingAesKey = encodingAesKey;
  }

  public boolean isEnableJsApi() {
    return enableJsApi;
  }

  public void setEnableJsApi(boolean enableJsApi) {
    this.enableJsApi = enableJsApi;
  }

  public boolean isEnableCard() {
    return enableCard;
  }

  public void setEnableCard(boolean enableCard) {
    this.enableCard = enableCard;
  }

  public Http getHttp() {
    return http;
  }

  public void setHttp(Http http) {
    this.http = http;
  }
}
