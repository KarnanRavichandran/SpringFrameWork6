package com.timothy.databasedemo.jpahibernate.JDBCDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCREPOSITORY {
    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY = """
            insert into course(id,name,author)
            values(1,'paul-course','paul');
            """;

    public void insert(){
        springJdbcTemplate.update(INSERT_QUERY);
    }
}
