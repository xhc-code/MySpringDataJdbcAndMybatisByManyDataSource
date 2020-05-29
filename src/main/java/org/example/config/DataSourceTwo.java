package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceTwo {

    @Bean(value = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public HikariDataSource dataSource2() {
        HikariDataSource dataSource = new HikariDataSource();
        return dataSource;
    }
}
