package com.zc.blog.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zc.blog.domain.SearchResult;
import com.zc.blog.helper.ApiHelper;
import com.zc.blog.helper.EsHelper;
import com.zc.blog.helper.JsonHelper;
import com.zc.blog.service.SearchService;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/14.
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity search(@RequestParam("query") String query) {
        return ApiHelper.withResponseEntity(objectNode -> {
            List<SearchResult> results = searchService.search(query);
            ArrayNode arrayNode = objectNode.putArray("results");
            arrayNode.addAll(JsonHelper.buildArrayNode(results));
        });
    }
}
