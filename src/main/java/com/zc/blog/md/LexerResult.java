package com.zc.blog.md;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangcong on 2017/4/11.
 */
public class LexerResult {

    List<Token> tokens = new ArrayList<>();
    Map<String, Link> links = new HashMap<>();

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "LexerResult{" +
                "tokens=" + tokens +
                ", links=" + links +
                '}';
    }
}
