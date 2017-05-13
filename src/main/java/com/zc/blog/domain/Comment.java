package com.zc.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by zhangcong on 2017/5/10.
 */
public class Comment {

    private int id;

    @JsonProperty("reply_id")
    private int replyId;

    @JsonProperty("article_id")
    private int articleId;

    private String content;

    private int level;

    private String username;

    private String email;

    private String time;

    private List<Comment> replys;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getReplys() {
        return replys;
    }

    public void setReplys(List<Comment> replys) {
        this.replys = replys;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", replyId=" + replyId +
                ", articleId=" + articleId +
                ", content='" + content + '\'' +
                ", level=" + level +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", time='" + time + '\'' +
                ", replys=" + replys +
                '}';
    }
}
