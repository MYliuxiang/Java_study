package com.lx.reume.dao;

import com.lx.reume.bean.User;

public interface UserDao extends BaseDao<User> {
    User get(User user);

}