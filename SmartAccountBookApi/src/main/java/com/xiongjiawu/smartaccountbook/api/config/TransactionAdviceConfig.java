package com.xiongjiawu.smartaccountbook.api.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TransactionAdviceConfig extends com.zhangzlyuyx.easy.mybatis.config.TransactionAdviceConfig {
	
	private static final String AOP_POINTCUT_EXPRESSION = "(execution(* com.xiongjiawu..service.impl.*.*(..))) or (execution(* com.sunrise..facade.impl.*.*(..)))";
	
	@Override
	public void init() {
		this.setAopPointcutExpression(AOP_POINTCUT_EXPRESSION);
		this.setRequiredMethods("add*,insert*,save*,delete*,remove*,update*,modify*,edit*,repair*,exec*,set*");
		this.setSupportMethods("select*,get*,query*,find*,list*,count*,is*,exists*,*");;
	}
}
