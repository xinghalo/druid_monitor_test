package com.xingoo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {

    @Autowired
    @Qualifier("prestoJdbcTemplate")
    private JdbcTemplate prestoJdbcTemplate;

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqlJdbcTemplate;

    public List<Map<String, Object>> query1(String a){
        String sql = "select * from standford.image_ocr where type = "+a;
        List<Map<String, Object>> rows = mysqlJdbcTemplate.query(sql, (RowMapper<Map<String, Object>>) (rs, index) -> {
            ResultSetMetaData metadata = rs.getMetaData();
            int count = metadata.getColumnCount();
            HashMap row = new HashMap();
            for (int i = 1; i <= count; ++i) {
                row.put(metadata.getColumnName(i), rs.getObject(i));
            }
            return row;
        });
        return rows;
    }

    public List<Map<String, Object>> query2(String a){
        String sql = "select * from hive.market.ai_ocr_d_t where brand="+a;
        List<Map<String, Object>> rows = prestoJdbcTemplate.query(sql, (RowMapper<Map<String, Object>>) (rs, index) -> {
            ResultSetMetaData metadata = rs.getMetaData();
            int count = metadata.getColumnCount();
            HashMap row = new HashMap();
            for (int i = 1; i <= count; ++i) {
                row.put(metadata.getColumnName(i), rs.getObject(i));
            }
            return row;
        });
        return rows;
    }
}
