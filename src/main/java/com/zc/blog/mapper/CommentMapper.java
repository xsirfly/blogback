package com.zc.blog.mapper;

import com.zc.blog.base.BaseMapper;
import com.zc.blog.domain.Comment;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/10.
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> findCommentByArticleId(int articleId);
}
