package com.example.drools.test3;

import com.example.drools.common.Base;
import com.example.drools.entity.From;
import com.example.drools.entity.Person;
import org.kie.api.runtime.KieSession;

/*
引擎执行规则：
    1.首先判断人员信息
        1.1 如果人员的等级==L1 则报销比例是100%；
        1.2 如果人员的等级==L2
            1.2.1 如果人员的职称在B以上（包括B），则报销比例是50%；
            1.2.2 如果人员的职称是C，则报销金额是45%；
        1.3 如果人员等级==L3
            1.3.1如果人员职称是A,
                1.3.1.1 如果工龄大于10年，报销比例是40%；
                1.3.1.2 如果工龄在10年到5年之间，报销比例是35%;
                1.3.1.3 如果工龄在5年以下，报销比例30%；
            1.3.2 如果人员职称是B，
                1.3.2.1 如果工龄大于十年，报销比例是35%；
                1.3.2.2 如果工龄在5年到10年之间，报销比例是30%；
                1.3.2.3 如果工龄在5年以下，报销比例是25%；
            1.3.3 如果人员职称是C
                1.3.3.1如果工龄大于10年，报销比例是30%；
                1.3.3.1如果工龄在5年到10年之间，报销比例是25%；
                1.3.3.1如果工龄在5年以下则报销比例是20%；
 */


//上述规则与设计业务场景较为符合，但仍未涉及数据操作操作

public class FromTest {
    public static void main(String[] args) {
        System.setProperty("drools.dateformat", "dd-MM-yyyy");
        Base base = new Base();
        KieSession kieSession = base.getSession("from-groupA");
        From from = new From();
        Person person = new Person();
        person.setAge(18);
        person.setJobNum("11111");
        person.setLevel(3);
        person.setTitle("C");
        person.setSex(1);
        person.setWorkTime(9);
        from.setPerson(person);
        kieSession.insert(from);
        kieSession.fireAllRules();
        System.out.println(from.getRatio());
    }
}