package com.xiongjiawu.smartaccountbook.api.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybatis分页插件配置
 * @author xWang
 * @Date 2019-07-12
 */
@Configuration
public class MyBatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum","false");
        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","false");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}