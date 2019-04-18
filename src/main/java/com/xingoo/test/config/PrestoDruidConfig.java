package com.xingoo.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class PrestoDruidConfig {

    @Bean(name = "prestoDataSource", destroyMethod = "close")
    public DataSource prestoDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName("com.facebook.presto.jdbc.PrestoDriver");
        dataSource.setUrl("jdbc:presto://hnode5:8001/cassandra");
        dataSource.setUsername("sirius");
        dataSource.setPassword("");
        //其他属性配置
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(6);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(600000);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(25200000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setLogAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        try {
            dataSource.setFilters("stat,slf4j,mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "prestoJdbcTemplate")
    public JdbcTemplate prestoJdbcTemplate(@Qualifier("prestoDataSource") DataSource prestoDataSource){
        return new JdbcTemplate(prestoDataSource);
    }
}
