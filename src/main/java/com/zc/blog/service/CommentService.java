package com.zc.blog.service;

import com.zc.blog.base.BaseService;
import com.zc.blog.domain.Comment;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/10.
 */
public interface CommentService extends BaseService<Comment> {
    List<Comment> findCommentByArticleId(int articleId);
}
