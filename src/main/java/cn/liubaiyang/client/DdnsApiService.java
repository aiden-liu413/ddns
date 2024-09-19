package cn.liubaiyang.client;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.liubaiyang.client.config.DdnsConfig;
import cn.liubaiyang.client.factory.DdnsApiServiceFactory;
import cn.liubaiyang.model.dto.DomainRecord;
import org.slf4j.Logger;

import java.util.List;

/**
 * @ClassName DdnsClient
 * @Description:
 * @Author aiden
 * @Date 2024/9/18 上午9:38
 */
public interface DdnsApiService {
    String DOT = ".";

    List<DomainRecord> listSubDomainRecords(String subDomain);

    void updateSubDomainRecord(String recordId, String type, String rr, String value);

    default void syncDnsRecord() {
        DdnsConfig ddnsConfig = getDdnsConfig();
        Logger log = getLog();
        List<DomainRecord> list = listSubDomainRecords(ddnsConfig.getSubDomain() + DOT + ddnsConfig.getDomain());
        if(CollUtil.isNotEmpty(list)) {
            DomainRecord domainRecord = list.get(0);
            log.info(">>>>解析记录:{}", JSONUtil.toJsonPrettyStr(domainRecord));
            String pubIp = HttpUtil.get(ddnsConfig.getIpInterface()).trim();
            if(domainRecord.getValue().equals(pubIp)){
                log.info(">>>>公网ip与之前的解析记录一致, 不做更新");
                return;
            }
            updateSubDomainRecord(domainRecord.getRecordId(), ddnsConfig.getType(), ddnsConfig.getSubDomain(),pubIp);
            log.info(">>>>公网ip更新成功, domain:{}, subdomain:{}, pubIp:{}",
                    ddnsConfig.getDomain(), ddnsConfig.getSubDomain(), pubIp);

        }
    }

    Logger getLog();

    DdnsConfig getDdnsConfig();
}
