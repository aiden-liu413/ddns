package cn.liubaiyang.client.factory;

import cn.liubaiyang.client.DdnsApiService;
import cn.liubaiyang.client.config.DdnsConfig;
import cn.liubaiyang.client.impl.AliyunDdnsApiService;
import cn.liubaiyang.model.ienum.DdnsProviderEnum;

/**
 * @ClassName DdnsApiServiceFactory
 * @Description:
 * @Author aiden
 * @Date 2024/9/19 上午9:26
 */
public class DdnsApiServiceFactory {

    public static DdnsApiService getDdnsApiService(DdnsConfig   ddnsConfig) {
        DdnsProviderEnum provider = ddnsConfig.getProvider();
        DdnsApiService apiService = null;
        switch (provider){
            case ALI_DNS: apiService = new AliyunDdnsApiService(ddnsConfig);
                break;
            case GOOGLE_DNS:
                break;
            case CLOUDFLARE_DNS:
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return apiService;
    }
}
