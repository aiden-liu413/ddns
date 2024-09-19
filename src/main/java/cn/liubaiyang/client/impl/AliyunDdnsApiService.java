package cn.liubaiyang.client.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.liubaiyang.client.DdnsApiService;
import cn.liubaiyang.client.config.AliyunConfig;
import cn.liubaiyang.client.config.DdnsConfig;
import cn.liubaiyang.model.dto.DomainRecord;
import com.aliyun.alidns20150109.models.DescribeSubDomainRecordsRequest;
import com.aliyun.alidns20150109.models.DescribeSubDomainRecordsResponse;
import com.aliyun.alidns20150109.models.DescribeSubDomainRecordsResponseBody;
import com.aliyun.alidns20150109.models.UpdateDomainRecordRequest;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName aliyunDdnsApiService
 * @Description:
 * @Author aiden
 * @Date 2024/9/18 上午9:39
 */
@Slf4j
public class AliyunDdnsApiService implements DdnsApiService {

    private com.aliyun.alidns20150109.Client client;

    private DdnsConfig ddnsConfig;

    public AliyunDdnsApiService(){
    }

    public AliyunDdnsApiService(DdnsConfig ddnsConfig){
        this.ddnsConfig = ddnsConfig;
        this.client = AliyunConfig.createClient(ddnsConfig);
    }

    @Override
    public List<DomainRecord> listSubDomainRecords(String subDomain) {
        DescribeSubDomainRecordsRequest request = new DescribeSubDomainRecordsRequest().setSubDomain(subDomain);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            DescribeSubDomainRecordsResponse recordsResponse = client.describeSubDomainRecordsWithOptions(request, runtime);
            List<DescribeSubDomainRecordsResponseBody.DescribeSubDomainRecordsResponseBodyDomainRecordsRecord> list =
                    recordsResponse.getBody().getDomainRecords().getRecord();
            return BeanUtil.copyToList(list, DomainRecord.class);
        } catch (Exception error) {
            log.error(error.getMessage(), error);
        }
        return Collections.emptyList();
    }

    @Override
    public void updateSubDomainRecord(String recordId, String type, String rr, String value) {
        UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest();
        updateDomainRecordRequest.setRecordId(recordId);
        updateDomainRecordRequest.setRR(rr);
        updateDomainRecordRequest.setType(type);
        updateDomainRecordRequest.setValue(value);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            client.updateDomainRecordWithOptions(updateDomainRecordRequest, runtime);
        } catch (Exception error) {
             log.error(error.getMessage(), error);
        }
    }

    @Override
    public Logger getLog() {
        return log;
    }

    @Override
    public DdnsConfig getDdnsConfig() {
        return ddnsConfig;
    }
}
