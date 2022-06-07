package com.spring.oauth2.config.datasource;

import org.springframework.util.Assert;

public class ReplicationDataBaseContextHolder {

    private static ThreadLocal<ReplicationType> CONTEXT = new ThreadLocal<>();

    public static void set(ReplicationType dataSourceType) {
        Assert.notNull(dataSourceType, "dataSourceType cannot be null");
        CONTEXT.set(dataSourceType);
    }

    public static ReplicationType get() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
