package com.spring.oauth2.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReplicationRoutingConfiguration {

    @Autowired
    MasterDetails masterDetails;

    @Autowired
    SlaveDetails slaveDetails;

    @Primary
    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(replicationDataSource());
    }

    @Bean
    public DataSource replicationDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        DataSource masterDataSource = masterDataSource();
        DataSource slaveDataSource = slaveDataSource();
        targetDataSources.put(ReplicationType.WRITE, masterDataSource);
        targetDataSources.put(ReplicationType.READ, slaveDataSource);

        ReplicationDataSourceRouter clientRoutingDatasource = new ReplicationDataSourceRouter();
        clientRoutingDatasource.setTargetDataSources(targetDataSources);
        clientRoutingDatasource.setDefaultTargetDataSource(masterDataSource);
        return clientRoutingDatasource;
    }

    @Bean
    public DataSource masterDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(masterDetails.getDriverClassName());
        hikariDataSource.setJdbcUrl(masterDetails.getUrl());
        hikariDataSource.setUsername(masterDetails.getUsername());
        hikariDataSource.setPassword(masterDetails.getPassword());
        hikariDataSource.setMaximumPoolSize(10);
        return hikariDataSource;
    }

    @Bean
    public DataSource slaveDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(slaveDetails.getDriverClassName());
        hikariDataSource.setJdbcUrl(slaveDetails.getUrl());
        hikariDataSource.setUsername(slaveDetails.getUsername());
        hikariDataSource.setPassword(slaveDetails.getPassword());
        hikariDataSource.setMaximumPoolSize(10);
        return hikariDataSource;
    }

}
