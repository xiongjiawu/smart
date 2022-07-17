package com.xiongjiawu.smartaccountbook.api.config;


import com.xiongjiawu.smartaccountbook.common.base.BaseResult;
import com.xiongjiawu.smartaccountbook.common.util.TranslationUtil;
import com.zhangzlyuyx.easy.mybatis.PageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 基础数据代码、用户名自动转义
 */
@Aspect
@Component
public class TranslationInject {

    private static Log logger = LogFactory.getLog(com.xiongjiawu.smartaccountbook.api.config.TranslationInject.class);


    @Pointcut("execution(* com.xiongjiawu.smartaccountbook.api..*Controller.*(..))")
    private void controllerCutMethod() {
    }

    @AfterReturning(value = "controllerCutMethod()", returning = "returnValue")
    public void doTranslation(JoinPoint jp, Object returnValue) throws Throwable {
        if (returnValue instanceof BaseResult) {
            Object data = ((BaseResult) returnValue).getData();
            if (data != null) {
                if (data instanceof PageResult) {
                    TranslationUtil.TransforBeanByBaseData(((PageResult) data).getRows());
                } else {
                    TranslationUtil.TransforBeanByBaseData(data);
                }
            }
        }
    }
}
