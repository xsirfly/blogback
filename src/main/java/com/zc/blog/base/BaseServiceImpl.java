package com.zc.blog.base;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/4.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseMapper<T> mapper;

    public List<T> findAll() {
        return mapper.findAll();
    }

    public int insert(T t) {
        return mapper.insert(t);
    }

    public int update(T t) {
        return mapper.update(t);
    }

    public int deleteById(int id) {
        return mapper.deleteById(id);
    }

    protected void setMapper(BaseMapper<T> mapper) {
        this.mapper = mapper;
    }

}
