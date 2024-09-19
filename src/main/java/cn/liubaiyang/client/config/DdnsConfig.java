package cn.liubaiyang.client.config;

import cn.liubaiyang.model.ienum.DdnsProviderEnum;
import lombok.Data;

/**
 * @ClassName DdnsConfig
 * @Description:
 * @Author aiden
 * @Date 2024/9/19 上午9:18
 */
@Data
public class DdnsConfig{
    private String domain;
    private String accessKey;
    private String secretKey;
    private String subDomain;
    private String ipInterface;
    private String Type = "A";
    private DdnsProviderEnum provider;
}