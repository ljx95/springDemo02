package com.ljx.test;

import com.ljx.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author: LinjianXiong
 * Date: 2019/4/26
 * Time: 18:32
 */
public class test01 {
    public static void main(String[] args){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) classPathXmlApplicationContext.getBean("userServiceImpl");
        userService.add();
    }
}
