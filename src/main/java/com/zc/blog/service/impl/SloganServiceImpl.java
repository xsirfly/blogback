package com.zc.blog.service.impl;

import com.zc.blog.base.BaseMapper;
import com.zc.blog.base.BaseService;
import com.zc.blog.base.BaseServiceImpl;
import com.zc.blog.domain.Slogan;
import com.zc.blog.mapper.SloganMapper;
import com.zc.blog.service.SloganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/3.
 */
@Service
public class SloganServiceImpl extends BaseServiceImpl<Slogan> implements SloganService {

    @Autowired
    public SloganServiceImpl(SloganMapper sloganMapper) {
        setMapper(sloganMapper);
    }

}
