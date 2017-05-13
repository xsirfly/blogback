package com.zc.blog.service.impl;

import com.zc.blog.base.BaseServiceImpl;
import com.zc.blog.domain.Comment;
import com.zc.blog.mapper.CommentMapper;
import com.zc.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangcong on 2017/5/10.
 */
@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    private CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        setMapper(commentMapper);
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> findCommentByArticleId(int articleId) {
        List<Comment> comments = commentMapper.findCommentByArticleId(articleId);
        List<Comment> result = buildComment(comments, 0, 0);
        return result;
    }

    /*
     * 递归是否会引起性能,栈溢出等问题
     */
    private List<Comment> buildComment(List<Comment> comments, int replyId, int level) {
        List<Comment> result = comments.stream().
                filter(comment -> comment.getReplyId() == replyId).
                collect(Collectors.toList());
        result.stream().forEach(comment -> {
            comment.setLevel(level >= 2 ? 2 : level);
            comment.setReplys(buildComment(comments, comment.getId(), level + 1));
        });
        return result;
    }

}
