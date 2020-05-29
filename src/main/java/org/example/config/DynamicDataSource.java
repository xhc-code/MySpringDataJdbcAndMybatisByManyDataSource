package org.example.config;

import org.example.utils.ContextSwitchDataSourceUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        ContextSwitchDataSourceUtils.DataSourceEnum currentLocalDataSourceKey = ContextSwitchDataSourceUtils.getCurrentLocalDataSourceKey();

        return currentLocalDataSourceKey == null ? null:currentLocalDataSourceKey.getDataSourceKey();
    }
}
