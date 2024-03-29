package com.example.drools.rule;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javafunk.excelparser.SheetParser;
import org.kie.api.io.ResourceType;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;


/*
rule最终生成用例
 */
public class TengXunTravleRuelTest {
    
    static String packageName = "drools";
    static List<String> importPackages = Arrays.asList(
            "java.math.*"
            , "com.example.drools.entity.*");
    
    
    static String sheetName = "工作表1";
    static String fileName = "腾讯差标规则1.xlsx";
//    static String fileName = "副本中集差标规则Final.xlsx";
    
    static String objectType = "MatchInvoiceTravelRuleRequestDto";
    static String objectVar = "$invoice";
    
    static String resultPropertyName = "result";
    static String alertMessagePropertyName = "remarkMessage";
    
    //规则内容属性别名，如{EXP_LEVEL}IN[一般员工]，EXP_LEVEL 可以起别名
    static Map<String, String> rulePropertyNameAlias = new HashMap<>();
    
    static {
        rulePropertyNameAlias.put("\\{.*LEVEL.*\\}", "EXPENSE_LEVEL");
        rulePropertyNameAlias.put("\\{.*LOCATION.*\\}", "city");
        rulePropertyNameAlias.put("\\{.*FEE.*\\}", "INVOICE_AMOUNT");
        rulePropertyNameAlias.put("\\{.*DAYS.*\\}", "days");
    }
    
    public static void main(String[] args) throws IOException {
        //使用POI API获取工作表
        SheetParser parser = new SheetParser();
        InputStream inputStream = TengXunTravleRuelTest.class.getClassLoader().getResourceAsStream(fileName);
        Sheet sheet = new XSSFWorkbook(inputStream).getSheet(sheetName);
        //调用工作解析器
        List<TravelRule> entityList = parser.createEntity(sheet, TravelRule.class, t -> t.printStackTrace());
        
        //打印
        entityList = entityList.stream()
                               .filter(t -> t.getCompanyName() != null && !t.getCompanyName().isEmpty())
                               .collect(Collectors.toList());
        
        
        System.out.println(new Gson().toJson(entityList));
        
        Set<String> set = entityList.stream().map(TravelRule::getRuleId).collect(Collectors.toSet());
        if (set.size() != entityList.size()) {
            throw new IllegalArgumentException("id 有重复");
        }
        
        
        entityList.stream()
                  .map(TengXunTravleRuelTest::travelRuleGenerate)
                  .forEach(t -> {
                      System.out.println(t);
                  });
    }
    
    
    public static TravelRule travelRuleGenerate(TravelRule travelRule) {
        TravelRuleGenerate travelRuleGenerate = new TravelRuleGenerate();
        travelRuleGenerate.setPackageName(packageName);
        travelRuleGenerate.setImportPackages(importPackages);
        travelRuleGenerate.setRuleName(travelRule.getRule());
        travelRuleGenerate.setObjectType(objectType);
        travelRuleGenerate.setObjectVar(objectVar);
        travelRuleGenerate.setTravelRule(travelRule);
        travelRuleGenerate.setResultPropertyName(resultPropertyName);
        travelRuleGenerate.setAlertMessagePropertyName(alertMessagePropertyName);
        travelRuleGenerate.setRulePropertyNameAlias(rulePropertyNameAlias);
        String generate = travelRuleGenerate.Generate();
        
        KieSessionUtil kieSessionUtil = new KieSessionUtil();
        kieSessionUtil.addContent(generate, ResourceType.DRL);
        kieSessionUtil.verifyRules();
        travelRule.setDroolsRule(generate);
        return travelRule;
    }
    
}
