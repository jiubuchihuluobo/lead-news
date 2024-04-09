package com.surge.aliyun.oss.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.StorageClass;
import com.surge.aliyun.oss.service.AliYunOssService;
import com.surge.aliyun.oss.service.impl.AliYunOssServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {AliYunOssProperties.class})
public class AliYunOssConfiguration {

    private final AliYunOssProperties properties;

    public AliYunOssConfiguration(AliYunOssProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ClientBuilderConfiguration getConfig() {
        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
        config.setMaxConnections(this.properties.getMaxConnections());
        config.setSocketTimeout(this.properties.getSocketTimeout());
        config.setConnectionTimeout(this.properties.getConnectionTimeout());
        config.setConnectionRequestTimeout(this.properties.getConnectionRequestTimeout());
        config.setIdleConnectionTime(this.properties.getIdleConnectionTime());
        config.setMaxErrorRetry(this.properties.getMaxErrorRetry());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public OSS getOssClient() {
        ClientBuilderConfiguration config = this.getConfig();
        OSS ossClient = new OSSClientBuilder().build(this.properties.getEndPoint(), this.properties.getAccessKeyId(), this.properties.getAccessKeySecret(), config);
        if (!ossClient.doesBucketExist(properties.getBucketName())) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(properties.getBucketName());
            createBucketRequest.setStorageClass(StorageClass.Standard);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        return ossClient;
    }

    @Bean
    @ConditionalOnMissingBean
    public AliYunOssService aliYunOSSService(OSS ossClient, AliYunOssProperties properties) {
        return new AliYunOssServiceImpl(ossClient, properties);
    }


}
