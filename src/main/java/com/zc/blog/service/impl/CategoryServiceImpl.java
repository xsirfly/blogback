package com.zc.blog.service.impl;

import com.zc.blog.base.BaseServiceImpl;
import com.zc.blog.domain.Category;
import com.zc.blog.mapper.CategoryMapper;
import com.zc.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/4.
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        setMapper(categoryMapper);
    }

}
