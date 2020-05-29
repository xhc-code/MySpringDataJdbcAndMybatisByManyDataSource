package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSourceOne {

    @Bean(value = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        return dataSource;
    }

}
