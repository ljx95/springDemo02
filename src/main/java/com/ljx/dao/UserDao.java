package com.ljx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * author: LinjianXiong
 * Date: 2019/4/26
 * Time: 18:57
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name, int age){
        String sql  = "insert into tb_user(name, age) values (?,?)";
        int udateResult = jdbcTemplate.update(sql,name,age);
        System.out.println("updateResult:" + udateResult);
    }
}
