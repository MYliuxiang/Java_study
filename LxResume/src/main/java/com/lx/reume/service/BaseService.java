package com.lx.reume.service;

import com.lx.reume.bean.Website;

import java.util.List;

public interface BaseService<T> {
    boolean remove(Integer id);
    boolean remove(List<Integer> ids);
    boolean save(T bean);

    T get(Integer id);
    List<T> list();
    int count();
}
