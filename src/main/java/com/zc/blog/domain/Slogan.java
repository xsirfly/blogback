package com.zc.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangcong on 2017/5/3.
 * 博客首页标语
 */
public class Slogan {

    private int id;

    @JsonProperty("text_prefix")
    private String textPrefix;

    @JsonProperty("text_suffix")
    private String textSuffix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextPrefix() {
        return textPrefix;
    }

    public void setTextPrefix(String textPrefix) {
        this.textPrefix = textPrefix;
    }

    public String getTextSuffix() {
        return textSuffix;
    }

    public void setTextSuffix(String textSuffix) {
        this.textSuffix = textSuffix;
    }

    @Override
    public String toString() {
        return "Slogan{" +
                "id=" + id +
                ", textPrefix='" + textPrefix + '\'' +
                ", textSuffix='" + textSuffix + '\'' +
                '}';
    }
}
