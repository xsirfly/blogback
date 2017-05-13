package com.zc.blog.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zc.blog.domain.Comment;
import com.zc.blog.exception.BadRequestException;
import com.zc.blog.helper.ApiHelper;
import com.zc.blog.helper.JsonHelper;
import com.zc.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/10.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public ResponseEntity getCommentByArticleId(@RequestParam("article_id") int articleId) {
        return ApiHelper.withResponseEntity(objectNode -> {
            List<Comment> comments = commentService.findCommentByArticleId(articleId);
            ArrayNode arrayNode = objectNode.putArray("comments");
            arrayNode.addAll(JsonHelper.buildArrayNode(comments));
        });
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity updateComment(@RequestBody Comment comment) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (comment.getId() <= 0) {
                throw new BadRequestException();
            }
            int rows = commentService.update(comment);
            objectNode.put("update_rows", rows);
        });
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity insertComment(@RequestBody Comment comment) {
        return ApiHelper.withResponseEntity(objectNode -> {
            int rows = commentService.insert(comment);
            objectNode.put("insert_rows", rows);
        });
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public ResponseEntity deleteComment(@PathVariable int id) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (id <= 0) {
                throw new BadRequestException();
            }
            int rows = commentService.deleteById(id);
            objectNode.put("delete_rows", rows);
        });
    }
}
