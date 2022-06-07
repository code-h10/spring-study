package com.spring.oauth2.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ReplicationDataSourceRouter extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        ReplicationType dataSourceFrom = ReplicationDataBaseContextHolder.get();
        if (dataSourceFrom == ReplicationType.READ) {
            return ReplicationType.READ;
        }
        return ReplicationType.WRITE;
    }
}
