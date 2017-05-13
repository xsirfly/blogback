package com.zc.blog.service.impl;

import com.zc.blog.base.BaseService;
import com.zc.blog.base.BaseServiceImpl;
import com.zc.blog.domain.Article;
import com.zc.blog.mapper.ArticleMapper;
import com.zc.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/11.
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        setMapper(articleMapper);
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> findOutLine() {
        return articleMapper.findOutLine();
    }

    @Override
    public String findArticleHtmlById(int id) {
        return articleMapper.findArticleHtmlById(id);
    }
}
