package com.zc.blog.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zc.blog.domain.Article;
import com.zc.blog.exception.BadRequestException;
import com.zc.blog.helper.ApiHelper;
import com.zc.blog.helper.DateHelper;
import com.zc.blog.helper.JsonHelper;
import com.zc.blog.md.Md;
import com.zc.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangcong on 2017/5/10.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json;chartset=utf-8"})
    public ResponseEntity insertArticle(Article article) {
        return ApiHelper.withResponseEntity(objectNode -> {
            String html = new Md().parser(article.getMd());
            article.setHtml(html);
            article.setDate(DateHelper.formatDate(new Date()));
            int rows = articleService.insert(article);
            objectNode.put("insert_rows", rows);
        });
    }

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getArticle() {
        return ApiHelper.withResponseEntity(objectNode -> {
            List<Article> articles = articleService.findAll();
            ArrayNode arrayNode = objectNode.putArray("articles");
            arrayNode.addAll(JsonHelper.buildArrayNode(articles));
        });
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getArticleHtmlById(@PathVariable int id) {
        return ApiHelper.withResponseEntity(objectNode -> {
            String html = articleService.findArticleHtmlById(id);
            objectNode.put("html", html);
        });
    }

    @RequestMapping(value = "/outline", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getArticleOutLine() {
        return ApiHelper.withResponseEntity(objectNode -> {
            List<Article> articles = articleService.findOutLine();
            ArrayNode arrayNode = objectNode.putArray("articles");
            arrayNode.addAll(JsonHelper.buildArrayNode(articles));
        });
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity updateArticle(Article article) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (article.getId() <= 0) {
                throw new BadRequestException();
            }
            int rows = articleService.update(article);
            objectNode.put("update_rows", rows);
        });
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public ResponseEntity deleteArticle(@PathVariable int id) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (id <= 0) {
                throw new BadRequestException();
            }
            int rows = articleService.deleteById(id);
            objectNode.put("delete_rows", rows);
        });
    }

}
