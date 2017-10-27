package com.kilfat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.kilfat",
        excludeFilters = {@ComponentScan.Filter(Configuration.class)})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.kilfat")
public class TestConfig {

}