package cn.liubaiyang.client.config;

import com.aliyun.alidns20150109.Client;
import lombok.Data;

/**
 * @ClassName AliyunConfig
 * @Description:
 * @Author aiden
 * @Date 2024/9/18 上午9:50
 */

public class AliyunConfig {

    public static Client createClient(DdnsConfig ddnsConfig){
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考。
        // 建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html。
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(ddnsConfig.getAccessKey())
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(ddnsConfig.getSecretKey());
        // Endpoint 请参考 https://api.aliyun.com/product/Alidns
        config.endpoint = "alidns.cn-hangzhou.aliyuncs.com";
        Client client = null;
        try {
            client = new Client(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return client;
    }

}
