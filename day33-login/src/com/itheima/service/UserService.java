package com.itheima.service;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;

public class UserService {
    public User login(String username, String password) throws Exception {
        UserDao userDao=new UserDao();
        User user=userDao.login(username,password);
        return user;
    }
}
