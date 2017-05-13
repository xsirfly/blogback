package com.zc.blog.base;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/4.
 */
public interface BaseService<T> {
    List<T> findAll();

    int update(T t);

    int insert(T t);

    int deleteById(int id);
}
