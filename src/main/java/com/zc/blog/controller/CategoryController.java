package com.zc.blog.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zc.blog.domain.Category;
import com.zc.blog.exception.BadRequestException;
import com.zc.blog.helper.ApiHelper;
import com.zc.blog.helper.JsonHelper;
import com.zc.blog.mapper.CategoryMapper;
import com.zc.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/4.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getAllCategory() {
        return ApiHelper.withResponseEntity(objectNode -> {
            List<Category> categories = categoryService.findAll();
            ArrayNode arrayNode = objectNode.putArray("categories");
            arrayNode.addAll(JsonHelper.buildArrayNode(categories));
        });
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity updateCategory(@RequestBody Category category) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (category.getId() <= 0) {
                throw new BadRequestException();
            }
            int rows = categoryService.update(category);
            objectNode.put("update_rows", rows);
        });
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity insertCategory(@RequestBody Category category) {
        return ApiHelper.withResponseEntity(objectNode -> {
            int rows = categoryService.insert(category);
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
            int rows = categoryService.deleteById(id);
            objectNode.put("delete_rows", rows);
        });
    }
}
