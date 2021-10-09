package com.lx.reume.dao.impl;

import com.lx.reume.bean.Website;
import com.lx.reume.dao.WebsiteDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class WebsiteDaoImpl extends BaseDaoImpl<Website> implements WebsiteDao {


    @Override
    public boolean save(Website bean) {
        Integer id = bean.getId();
        List<Object> args = new ArrayList<>();
        args.add(bean.getFooter());
        String sql;
        if (id == null || id < 1){
            //添加
            sql = "INSERT INTO website(footer) VALUES (?)";

        }else{
            //修改
            sql = "UPDATE website SET footer = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    @Override
    public Website get(Integer id) {
        return null;
    }

    @Override
    public List<Website> list() {
        String sql = "SELECT id, created_time, footer FROM website";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Website.class));
    }
}
