package com.ljx.service.impl;

import com.ljx.annotation.ExtTransaction;
import com.ljx.dao.UserDao;
import com.ljx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: LinjianXiong
 * Date: 2019/4/26
 * Time: 17:28
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @ExtTransaction
    public void add(){
        userDao.add("张三",13);
//        int i = 1/0;
        System.out.println("############################");
        userDao.add("李四", 14);
    }
}
