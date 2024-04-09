package com.surge.aliyun.oss.service;

import java.io.InputStream;

public interface AliYunOssService {

    String uploadFile(String prefix, String filename, InputStream inputStream, String contentType);

    String uploadFile(String prefix, String filename, InputStream inputStream);

}
