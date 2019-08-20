# ConfigAutomatic
**common：** 

*
    Base 是获取KieSession的基础类，内部包括两个重构的getSession方法
    
    
    getSession(String xxxx);*获取指定的KieSession.
    getSession();遍历所有符合条件的KieSession并且执行
*
    RuleResult 是表明rule执行结果的枚举类；只包括code（标记执行状态）和description（执行结果的描述）两个字段

**entity**
    
    所有实体类集合，类似于表单组装时的各个部分
    
**test1**
    
    test1主要是演示drools5的运行模式，与drools6、7的差距很大，并且操作步骤复杂。
    
    
**test2**
    
    test2演示如何对drools6/7进行操作，设计到Ccommon中base类的使用
    
**test3**
*
    规则：
    
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
    解释：此处kieSession的使用类似于实际业务场景中比较复杂的情况，从规则展示可以看出规则树的模型。
    
 **rule**

     
    无用的类：FilloExcel,XlsmapperExcel,ZhongJiTravleRuleTest这三个类都是用于测试用例，此处不给于删除是为了帮助使用者理解。
 
 *
    TravelRuleInterface
    
        定义数据get和set的接口，对数据的答大体操作。详细见代码实现。
        
 *
    KieSessionUtil
    
        通过继承KieHelper重写内部方法，提供多个excuteInsertObject(...)方法实现业务上的需求
        
 *
    TravelRule
        
        创建实体对应规则生成之后的规则信息、提示内容、规则ID等；详细见代码实现。
        
 *
    TravelRuleSheet
    
        最终规则生成前的初步准备，最后才进行组装。
  
 *
    TengXunTravleRuelTest
    
        主运行程序，把之前蓑鲉全部结合起来，进行规则的检验，是否有效并且输出。
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

