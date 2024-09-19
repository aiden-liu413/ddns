package cn.liubaiyang.model.dto;

import lombok.Data;

/**
 * @ClassName DomainRecord
 * @Description:
 * @Author aiden
 * @Date 2024/9/18 上午9:40
 */
@Data
public class DomainRecord {
    public String domainName;
    public String line;
    public Boolean locked;
    public Long priority;
    public String RR;
    public String recordId;
    public String remark;
    public String status;
    public Long TTL;
    public String type;
    public String value;
    public Integer weight;
}
