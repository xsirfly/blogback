package com.zc.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.zc.blog.domain.Slogan;
import com.zc.blog.exception.BadRequestException;
import com.zc.blog.helper.ApiHelper;
import com.zc.blog.helper.JsonHelper;
import com.zc.blog.service.SloganService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangcong on 2017/5/3.
 */
@RestController
@RequestMapping("/slogan")
public class SloganController {

    @Resource
    private SloganService sloganService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity getAllSlogan() {
        return ApiHelper.withResponseEntity(objectNode -> {
            List<Slogan> slogans = sloganService.findAll();
            ArrayNode arrayNode = objectNode.putArray("slogans");
            arrayNode.addAll(JsonHelper.buildArrayNode(slogans));
        });
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = {"application/json;charset=UTF-8"})
    public ResponseEntity deleteSlogan(@PathVariable("id") int id) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (id < 0) {
                throw new BadRequestException();
            }
            int rows = sloganService.deleteById(id);
            objectNode.put("delete_rows", rows);
        });
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity updateSlogan(@RequestBody Slogan slogan) {
        return ApiHelper.withResponseEntity(objectNode -> {
            if (slogan.getId() <= 0) {
                throw new BadRequestException();
            }
            int rows = sloganService.update(slogan);
            objectNode.put("update_rows", rows);
        });
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public ResponseEntity insertSlogan(@RequestBody Slogan slogan) {
        return ApiHelper.withResponseEntity(objectNode -> {
            int rows = sloganService.insert(slogan);
            objectNode.put("insert_rows", rows);
        });
    }
}
