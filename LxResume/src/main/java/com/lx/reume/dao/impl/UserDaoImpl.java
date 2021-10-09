package com.lx.reume.dao.impl;

import com.lx.reume.bean.User;
import com.lx.reume.dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public boolean save(User bean) {
        Integer id = bean.getId();
        List<Object> args = new ArrayList<>();

        String sql;
        if (id == null || id < 1) { // 添加
            sql = "INSERT INTO award(name, image, intro) VALUES(?, ?, ?)";
        } else {
            sql = "UPDATE award SET name = ?, image = ?, intro = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    public User get(Integer id) {
        String sql = "SELECT id, created_time, name, image, intro FROM award WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> list() {
        String sql = "SELECT id, created_time, name, image, intro FROM award";
        return tpl.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User get(User user) {
        String sql = "SELECT id, created_time, password, email, phone, intro, name, birthday, address, phone, job, trait, interests FROM user WHERE email = ? AND password = ?";
        List<User> users = tpl.query(sql, new BeanPropertyRowMapper<>(User.class), user.getEmail(), user.getPassword());
        return users.isEmpty() ? null : users.get(0);
    }
}