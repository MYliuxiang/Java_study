package com.lx.reume.service.impl;

import com.lx.reume.bean.User;
import com.lx.reume.dao.UserDao;
import com.lx.reume.service.UserService;


public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Override
    public User get(User user) {
        return ((UserDao) dao).get(user);
    }
}