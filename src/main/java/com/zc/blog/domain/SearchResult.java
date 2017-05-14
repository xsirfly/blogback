package com.zc.blog.domain;

import java.util.Comparator;

/**
 * Created by zhangcong on 2017/5/14.
 */
public class SearchResult implements Comparable{

    private int articleId;

    private double score;

    private String outline;

    private String author;

    private String date;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(Object o) {
        SearchResult other = (SearchResult) o;
        return Double.compare(this.getScore(), other.getScore());
    }
}
