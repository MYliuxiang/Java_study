package com.lx.reume.service.impl;

import com.lx.reume.bean.Website;
import com.lx.reume.dao.BaseDao;
import com.lx.reume.service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected BaseDao<T> dao = newDao();

    protected BaseDao<T> newDao(){
        try {
            String clsName = getClass().getName()
                    .replace(".service.", ".dao.")
                    .replace("Service", "Dao");
            return (BaseDao<T>) Class.forName(clsName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Override
    public boolean remove(List<Integer> ids) {
        return dao.remove(ids);
    }

    @Override
    public boolean save(T bean) {
        return dao.save(bean);
    }

    @Override
    public T get(Integer id) {
        return dao.get(id);
    }

    @Override
    public List<T> list() {
        return dao.list();
    }

    @Override
    public int count() {
        return dao.count();
    }
}
