package com.raj.ms.feedback.config;

import oracle.jdbc.datasource.impl.OracleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {

 @Bean
  public DataSource getDataSource() throws SQLException {

    OracleDataSource dataSource = new OracleDataSource();

    dataSource.setURL("jdbc:oracle:thin:@192.168.0.236:1521/xepdb1");
    dataSource.setUser("schema_bkcrt_ms_product");
    dataSource.setPassword("schema_bkcrt_ms_product");

    return dataSource;
  }
}
