package com.example.drools.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 【一个规则节点信息的定义】
 * 如下字段上的注释所示
 */
@Data
public class MatchInvoiceTravelRuleRequestDto {
    //规则ID
    private String ruleOID;
    
    //出差申请ID
    private String invoiceOID;
    
    //出差信息-人员级别、出差地点、出差费用等等
    private Map<String, String> data = new HashMap<>();
    
    //规则执行结果
    private RuleResult result = RuleResult.OK;
    
    //备注-规则明细
    private String remarkMessage;
    
}
