package com.lx.reume.dao.impl;

import com.lx.reume.bean.Company;
import com.lx.reume.dao.CompanyDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao {

    @Override
    public boolean save(Company bean) {
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

    public Company get(Integer id) {
        String sql = "SELECT id, created_time, name, image, intro FROM award WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Company.class), id);
    }

    @Override
    public List<Company> list() {
        String sql = "SELECT id, created_time, name, image, intro FROM award";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Company.class));
    }
}