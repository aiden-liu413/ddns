package cn.liubaiyang.client.config;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName Context
 * @Description:
 * @Author aiden
 * @Date 2024/9/18 上午10:16
 */
@Slf4j
public class Context {
    public static List<DdnsConfig> ddnsConfigList;

    public static final String APP_CONFIG_CONFIG_JSON = "/app/config/config.json";

    static {
        Resource resourceObj = null;
        try {
            String configStr = "";
            boolean exist = FileUtil.exist(APP_CONFIG_CONFIG_JSON);
            if(exist){
                configStr = FileUtil.readUtf8String(APP_CONFIG_CONFIG_JSON);
                log.info(">>>>配置内容 从/app/config/config.json加载<<<<\n{}", configStr);
            }
            if(StrUtil.isEmpty(configStr)){
                resourceObj = ResourceUtil.getResourceObj("./config.json");
                configStr = resourceObj.readStr(StandardCharsets.UTF_8);
            }
            log.info(">>>>配置内容 从classpath:/config.json加载<<<<\n{}", configStr);
            List<DdnsConfig> configList = JSONUtil.toList(configStr, DdnsConfig.class);
            configList.forEach(Context::validConfig);
            Context.ddnsConfigList = configList;
        } catch (IllegalArgumentException illegalArgumentException){
            log.error("配置文件解析失败 cause:{},程序退出", illegalArgumentException.getMessage());
            System.exit(0);
        } catch (Exception e) {
            log.error(">>>>配置文件不存在或读取失败,请检查是否正确配置 cause:{}<<<<", e.getMessage());
        }

    }

    public static void validConfig(DdnsConfig ddnsConfig) {
        Assert.notBlank(ddnsConfig.getAccessKey(), "ak can not be null");
        Assert.notBlank(ddnsConfig.getAccessKey(), "sk can not be null");
        Assert.notBlank(ddnsConfig.getAccessKey(), "sub domain can not be null");
        Assert.notBlank(ddnsConfig.getAccessKey(), "domain can not be null");
        Assert.notBlank(ddnsConfig.getAccessKey(), "ipInterface can not be null");
    }
}
