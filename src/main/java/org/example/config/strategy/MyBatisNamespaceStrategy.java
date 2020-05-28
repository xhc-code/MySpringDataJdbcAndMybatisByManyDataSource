package org.example.config.strategy;

import org.springframework.data.jdbc.mybatis.NamespaceStrategy;

public class MyBatisNamespaceStrategy implements NamespaceStrategy {

    @Override
    public String getNamespace(Class<?> domainType) {
        return "org.example.mapper.".concat(domainType.getSimpleName()).concat("Mapper");
    }

}
