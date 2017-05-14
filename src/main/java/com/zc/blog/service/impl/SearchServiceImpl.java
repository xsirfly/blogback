package com.zc.blog.service.impl;

import com.zc.blog.domain.SearchResult;
import com.zc.blog.helper.EsHelper;
import com.zc.blog.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhangcong on 2017/5/14.
 */
@Service
public class SearchServiceImpl implements SearchService {

    Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Override
    public List<SearchResult> search(String query) {
        Client client = null;
        List<SearchResult> results = new ArrayList<SearchResult>();
        try {
            client = EsHelper.getClient();
            SearchResponse response = client.prepareSearch(EsHelper.getIndex())
                    .setQuery(QueryBuilders.queryStringQuery(query))
                    .get();
            for(int i = 0; i < response.getHits().getTotalHits(); i++) {
                SearchHit hit = response.getHits().getAt(i);
                SearchResult result = new SearchResult();
                result.setArticleId((int)hit.getSource().get("id"));
                result.setAuthor((String)hit.getSource().get("author"));
                result.setOutline((String)hit.getSource().get("outline"));
                result.setScore((double)hit.getScore());
                result.setDate((String)hit.getSource().get("date"));
                results.add(result);
            }
            results = results.stream().sorted().collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.toString());
            throw new RuntimeException(e);
        } finally {
            client.close();
        }
        return results;
    }

}
