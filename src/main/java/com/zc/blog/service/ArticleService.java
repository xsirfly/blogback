package com.zc.blog.service;

import com.zc.blog.base.BaseService;
import com.zc.blog.domain.Article;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/11.
 */
public interface ArticleService extends BaseService<Article> {

    List<Article> findOutLine();

    String findArticleHtmlById(int id);

}
