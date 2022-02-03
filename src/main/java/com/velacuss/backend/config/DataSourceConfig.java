package com.velacuss.backend.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "velacussDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    DataSource velacussDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "velacussJdbcTemplate")
    JdbcTemplate velacussJdbcTemplate(@Qualifier("velacussDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
