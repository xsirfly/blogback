package com.zc.blog.service.impl;

import com.zc.blog.base.BaseServiceImpl;
import com.zc.blog.domain.Tag;
import com.zc.blog.mapper.TagMapper;
import com.zc.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangcong on 2017/5/4.
 */
@Service
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        setMapper(tagMapper);
    }

}
