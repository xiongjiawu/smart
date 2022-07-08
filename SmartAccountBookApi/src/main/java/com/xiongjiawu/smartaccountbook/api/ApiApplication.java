package com.xiongjiawu.smartaccountbook.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@EnableCaching
@ComponentScan(basePackages = {"com.xiongjiawu.smartaccountbook"})
@MapperScan("com.xiongjiawu.smartaccountbook.common.dao")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ApiApplication {

    private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class, args);

        log.info("智慧账单系统api服务已启动!");
    }
}
