package com.zc.blog.helper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zc.blog.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by zhangcong on 2017/5/4.
 */
public class ApiHelper {

    private static Logger logger = LoggerFactory.getLogger(ApiHelper.class);


    public static ResponseEntity withResponseEntity(ApiHandler handler) {
        ObjectNode result = JsonHelper.createObjectNode();
        ObjectNode data = result.putObject("data");
        try {
            handler.handle(data);
        } catch (BadRequestException e) {
            logger.error("error -> {}", e);
            result.put("success", false);
            result.put("error", e.toString());
            return new ResponseEntity(result.toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("error -> {}", e);
            result.put("success", false);
            result.put("error", e.toString());
            return new ResponseEntity(result.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.put("success", true);
        return new ResponseEntity(result.toString(), HttpStatus.OK);
    }

    public interface ApiHandler {
        void handle(ObjectNode objectNode) throws Exception;
    }
}
