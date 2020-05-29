## 多数据源的一些问题 2020/05/29记录
* 事务的SpringBoot自动配置类：**DataSourceTransactionManagerAutoConfiguration**

* Service之间调用方法，不会执行AOP的切入方法，需要通过接口进行访问才可以，由于使用的JDK代理，故不测试cglib是否可以解决AspectJ切入的问题

