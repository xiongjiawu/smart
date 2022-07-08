package com.xiongjiawu.smartaccountbook.common.annotation;


import com.xiongjiawu.smartaccountbook.common.enums.EnumTranStatus;

import java.lang.annotation.*;

/**
 * 翻译字典值、用户名
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
@Documented
@Inherited
public @interface TranslationDict {

    /**
     * 翻译类型:默认字典翻译
     * EnumTranStatus.baseData-BaseData字典表翻译
     * EnumTranStatus.user-用户名翻译
     */
    EnumTranStatus type() default EnumTranStatus.baseData;

    /**
     * 翻译字段,
     * 字典表翻译:默认原字段名+Desc
     * 用户名翻译:默认name
     */
    String field() default "";

    /**
     * BaseData表ParNode值，不传默认将字段拆分获取ParNode
     */
    String fieldType() default "";
}

