package cn.liubaiyang;

import cn.liubaiyang.client.DdnsApiService;
import cn.liubaiyang.client.config.Context;
import cn.liubaiyang.client.factory.DdnsApiServiceFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ${NAME}
 * @Description:
 * @Author aiden
 * @Date 2024/9/18 上午9:07
 */
@Slf4j
public class DdnsApplication {

    public static void main(String[] args_) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        Context.ddnsConfigList.forEach(
                ddnsConfig -> {
                    scheduledExecutorService.scheduleWithFixedDelay(() -> {
                        DdnsApiService ddnsApiService = DdnsApiServiceFactory.getDdnsApiService(ddnsConfig);
                        ddnsApiService.syncDnsRecord();
                    }, 2,10, TimeUnit.SECONDS);
                }
        );

    }
}