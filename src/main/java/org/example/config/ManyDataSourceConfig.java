package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.HashMap;

@Configuration
@MapperScan(basePackages = "classpath:org.example.mapper")
public class ManyDataSourceConfig {
    @Bean
    @Primary
    public DynamicDataSource dynamicDataSouces(@Qualifier("db1") HikariDataSource dataSource, @Qualifier("db2") HikariDataSource dataSource2){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("db1",dataSource);
        objectObjectHashMap.put("db2",dataSource2);
        dynamicDataSource.setTargetDataSources(objectObjectHashMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        return dynamicDataSource;
    }

    @Bean
    @Primary
    public SqlSessionFactoryBean sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath:mapper/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setTypeAliasesPackage("org.example.domain");
        return sqlSessionFactoryBean;
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        SqlSessionFactory object = sqlSessionFactoryBean.getObject();
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(object);
        return sqlSessionTemplate;
    }

}
