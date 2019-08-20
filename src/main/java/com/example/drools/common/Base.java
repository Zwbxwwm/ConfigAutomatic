package com.example.drools.common;

import com.example.drools.entity.Response;
import com.example.drools.entity.didiOrder;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;

public class Base {
    public static KieSession getSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //kmodule.xml 中定义的 ksession name
        KieSession kieSession = kieContainer.newKieSession("all-rules");
        return kieSession;
    }

    public static KieSession getSession(String agendaGroupName) {
        long sessionTimeStart = System.currentTimeMillis();
        KieSession session = getSession();
        if (StringUtils.isNoneBlank(agendaGroupName)) {
            session.getAgenda().getAgendaGroup(agendaGroupName).setFocus();
        }
        long sessionTimeEnd = System.currentTimeMillis();
        System.out.println("获取session所花时间："+(sessionTimeEnd-sessionTimeStart)+"毫秒...");
        return session;
    }

}