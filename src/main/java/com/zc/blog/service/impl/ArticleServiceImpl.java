package com.zc.blog.service.impl;

import com.zc.blog.base.BaseService;
import com.zc.blog.base.BaseServiceImpl;
import com.zc.blog.domain.Article;
import com.zc.blog.helper.EsHelper;
import com.zc.blog.mapper.ArticleMapper;
import com.zc.blog.service.ArticleService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by zhangcong on 2017/5/11.
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        setMapper(articleMapper);
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> findOutLine() {
        return articleMapper.findOutLine();
    }

    @Override
    public String findArticleHtmlById(int id) {
        return articleMapper.findArticleHtmlById(id);
    }

    @Override
    public void indexEs(Article article) {
        Client client = null;
        try {
            client = EsHelper.getClient();
            IndexResponse response = client.prepareIndex(EsHelper.index, EsHelper.type).setSource(
                    jsonBuilder()
                            .startObject()
                            .field("id", article.getId())
                            .field("title", article.getTitle())
                            .field("tags", article.getTags())
                            .field("content", article.getMd())
                            .field("outline", article.getOutline())
                            .field("author", article.getAuthor())
                            .field("date", article.getDate())
                            .endObject()
            ).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }
}
