package com.zc.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhangcong on 2017/5/4.
 */
public class Tag {

    private int id;

    private String name;

    @JsonProperty("category_id")
    private int categoryId;

    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", link='" + link + '\'' +
                '}';
    }
}
