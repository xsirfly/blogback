package com.zc.blog.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zc.blog.domain.Category;
import com.zc.blog.domain.Tag;
import com.zc.blog.exception.BadRequestException;
import com.zc.blog.helper.ApiHelper;
import com.zc.blog.helper.JsonHelper;
import com.zc.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/4.
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getAllTag() {
        return ApiHelper.withResponseEntity(objectNode -> {
            List<Tag> tags = tagService.findAll();
            ArrayNode arrayNode = objectNode.putArray("tags");
            arrayNode.addAll(JsonHelper.buildArrayNode(tags));
        });
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity updateTag(@RequestBody Tag tag) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (tag.getId() <= 0) {
                throw new BadRequestException();
            }
            int rows = tagService.update(tag);
            objectNode.put("update_rows", rows);
        });
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity insertTag(@RequestBody Tag tag) {
        return ApiHelper.withResponseEntity(objectNode -> {
            int rows = tagService.insert(tag);
            objectNode.put("insert_rows", rows);
        });
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public ResponseEntity deleteCategory(@PathVariable int id) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (id <= 0) {
                throw new BadRequestException();
            }
            int rows = tagService.deleteById(id);
            objectNode.put("delete_rows", rows);
        });
    }
}
