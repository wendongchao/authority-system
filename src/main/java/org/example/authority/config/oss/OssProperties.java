package org.example.authority.config.oss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-08
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OssProperties {
    private String endPoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
}