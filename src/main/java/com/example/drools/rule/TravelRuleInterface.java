package com.example.drools.rule;

/**
 * 定义接口
 */
public interface TravelRuleInterface {

    String getCompanyName();
    
    void setCompanyName(String companyName);
    
    String getExpenseType();
    
    void setExpenseType(String expenseType);
    
    String getRule();
    
    void setRule(String rule);
    
    String getApplyPoint();
    
    void setApplyPoint(String applyPoint);
    
    String getRuleAction();
    
    void setRuleAction(String ruleAction);
    
    String getAlertMessage();
    
    void setAlertMessage(String alertMessage);
    
    String getRuleId();
    
    void setRuleId(String ruleId);
    
    String getDroolsRule();
    
    void setDroolsRule(String droolsRule);
}
