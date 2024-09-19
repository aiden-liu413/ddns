package cn.liubaiyang.client.impl;

import cn.hutool.core.collection.CollUtil;
import cn.liubaiyang.client.config.Context;
import cn.liubaiyang.model.dto.DomainRecord;
import junit.framework.TestCase;

import java.util.List;

/**
 * @ClassName AliyunDdnsApiServiceTest
 * @Description:
 * @Author aiden
 * @Date 2024/9/18 上午11:33
 */
public class AliyunDdnsApiServiceTest extends TestCase {

    public void testListSubDomainRecords() {
        Context.ddnsConfigList.forEach(ddnsConfig -> {
            AliyunDdnsApiService ddnsApiService = new AliyunDdnsApiService(ddnsConfig);
            List<DomainRecord> list = ddnsApiService.listSubDomainRecords(ddnsConfig.getSubDomain() + "." + ddnsConfig.getDomain());
            System.out.println(list);
        });

    }

    public void testUpdateSubDomainRecord() {
        Context.ddnsConfigList.forEach(ddnsConfig -> {
            AliyunDdnsApiService ddnsApiService = new AliyunDdnsApiService(ddnsConfig);
            List<DomainRecord> list = ddnsApiService.listSubDomainRecords(ddnsConfig.getSubDomain() + "." + ddnsConfig.getDomain());
            if(CollUtil.isNotEmpty(list)) {
                DomainRecord domainRecord = list.get(0);
                ddnsApiService.updateSubDomainRecord(domainRecord.getRecordId(),"A","www","127.0.0.1");
            }
        });

    }
}