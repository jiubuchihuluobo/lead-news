package com.surge.aliyun.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("aliyun.oss")
public class AliYunOssProperties {

    private Integer maxConnections;

    private Integer socketTimeout;

    private Integer connectionTimeout;

    private Integer connectionRequestTimeout;

    private Integer idleConnectionTime;

    private Integer maxErrorRetry;

    private String userAgent;

    private String proxyHost;

    private Integer proxyPort;

    private String proxyUsername;

    private String proxyPassword;

    private String endPoint;

    private String bucketName;

    private String accessKeyId;

    private String accessKeySecret;

}
