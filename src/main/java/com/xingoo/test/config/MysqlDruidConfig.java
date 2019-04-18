package com.xingoo.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MysqlDruidConfig {

    @Bean(name = "mysqlDataSource", destroyMethod = "close")
    public DataSource mysqlDataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://10.10.5.11:3306/standford?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("tiangou");
        dataSource.setPassword("tiangou123");
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
            dataSource.setFilters("mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource mysqlDataSource){
        return new JdbcTemplate(mysqlDataSource);
    }
}
