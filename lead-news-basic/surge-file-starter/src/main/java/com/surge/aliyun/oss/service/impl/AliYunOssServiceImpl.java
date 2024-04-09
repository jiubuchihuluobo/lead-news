package com.surge.aliyun.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.surge.aliyun.oss.config.AliYunOssProperties;
import com.surge.aliyun.oss.service.AliYunOssService;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component("AliYunOssServiceImpl")
public class AliYunOssServiceImpl implements AliYunOssService {

    private final OSS OSSClient;

    private final AliYunOssProperties properties;

    public AliYunOssServiceImpl(OSS ossClient, AliYunOssProperties properties) {
        OSSClient = ossClient;
        this.properties = properties;
    }

    public String buildKey(String prefix, String filename) {
        String separator = "/";
        LocalDate localDate = LocalDate.now();
        StringBuilder stringBuilder = new StringBuilder();

        String year = String.valueOf(localDate.getYear());
        stringBuilder.append(year).append(separator);

        String month = String.format("%02d", localDate.getMonthValue());
        stringBuilder.append(month).append(separator);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String day = formatter.format(localDate);
        stringBuilder.append(day).append(separator);

        stringBuilder.append(filename);

        return prefix + separator + stringBuilder;
    }

    @Override
    public String uploadFile(String prefix, String filename, InputStream inputStream, String contentType) {
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentType(contentType);

        String key = this.buildKey(prefix, filename);

        PutObjectRequest putObjectRequest = new PutObjectRequest(this.properties.getBucketName(), key, inputStream, objectMeta);
        this.OSSClient.putObject(putObjectRequest);

        return key;
    }

    @Override
    public String uploadFile(String prefix, String filename, InputStream inputStream) {
        return this.uploadFile(prefix, filename, inputStream, "image/png");
    }

}
