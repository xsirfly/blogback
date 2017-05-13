package com.zc.blog.mapper;

import com.zc.blog.base.BaseMapper;
import com.zc.blog.domain.Article;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/11.
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> findOutLine();

    String findArticleHtmlById(int id);
}
