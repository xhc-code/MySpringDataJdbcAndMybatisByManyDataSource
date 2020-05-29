package org.example.config;

import org.apache.ibatis.session.SqlSession;
import org.example.config.strategy.MyBatisNamespaceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.mybatis.MyBatisDataAccessStrategy;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.MySqlDialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration(proxyBeanMethods = false)
public class MyBatisJdbcConfig extends AbstractJdbcConfiguration {

    @Autowired
    private SqlSession session;

    @Bean
    @Override
    public DataAccessStrategy dataAccessStrategyBean(NamedParameterJdbcOperations operations, JdbcConverter jdbcConverter, JdbcMappingContext context, Dialect dialect) {
        return MyBatisDataAccessStrategy.createCombinedAccessStrategy(context,jdbcConverter,operations,session,new MyBatisNamespaceStrategy(),dialect);
    }

    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        return MySqlDialect.INSTANCE;
    }

}
