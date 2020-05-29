package org.example.config;

import org.example.utils.ContextSwitchDataSourceUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return ContextSwitchDataSourceUtils.getCurrentLocalDataSourceKey().getDataSourceKey();
    }
}
