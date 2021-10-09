package com.lx.reume.service;

import com.lx.reume.bean.User;

public interface UserService extends BaseService<User> {
    User get(User user);
}