package com.zc.blog.service;

import com.zc.blog.domain.SearchResult;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/14.
 */
public interface SearchService {
    List<SearchResult> search(String query);
}
