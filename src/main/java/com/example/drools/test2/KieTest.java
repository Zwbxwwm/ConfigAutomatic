package com.example.drools.test2;

import com.example.drools.common.Base;
import com.example.drools.entity.Response;
import com.example.drools.entity.didiOrder;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;

import static com.example.drools.common.Base.getSession;
/*
此处的demo基于drools7版本，通用于drools6、7，；
实现简单的金额判断，使用到agenda-group的概念。
 */
public class KieTest {
    public static void main(String[] args) {
        Base bas = new Base();
        System.setProperty("drools.dateformat", "dd-MM-yyyy");
        KieSession kieSession = getSession("test-group1");
        long engineTimeStart = System.currentTimeMillis();
        didiOrder order = new didiOrder();
        order.setOrderAmount(new BigDecimal("50"));
        Response response = new Response();
        kieSession.insert(order);
        kieSession.insert(response);
        int count = kieSession.fireAllRules();
        long engineTimeEnd = System.currentTimeMillis();
        System.out.println("执行引擎所花时间："+(engineTimeEnd-engineTimeStart)+"毫秒...");
        System.out.println(count);
        System.out.println(order.getOrderId());
        System.out.println(response.getData());
        kieSession.dispose();
    }
}
