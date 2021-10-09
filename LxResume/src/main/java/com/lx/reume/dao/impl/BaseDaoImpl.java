package com.lx.reume.dao.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.lx.reume.dao.BaseDao;
import com.lx.reume.util.Strings;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    protected static JdbcTemplate tpl;
    static {
        try(InputStream is = BaseDaoImpl.class.getClassLoader().getResourceAsStream("druid.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            tpl = new JdbcTemplate(ds);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String table = table();

    private String table(){
        //根据泛型类型获取表明
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class beanCls = (Class) type.getActualTypeArguments()[0];
        return Strings.underlineCase(beanCls.getSimpleName());
    }

    public boolean remove(Integer id) {

        String sql = "DELETE FROM" + table + "WHERE id = ?";
        return tpl.update(sql,id) > 0;
    }

    @Override
    public boolean remove(List<Integer> ids) {

        List<Object> args = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(table).append(" WHERE id in (");
        for (Integer id : ids) {
            args.add(id);
            sql.append("?, ");
        }
        sql.replace(sql.length() - 2, sql.length(), ")");
        // DELETE FROM education WHERE id in (?, ?, ?)
        return tpl.update(sql.toString(), args.toArray()) > 0;
    }

    public  int count(){
        String sql = "SELECT COUNT(*) FROM " + table;
        return  tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Integer.class));
    }

}
