package com.zc.blog.domain;

/**
 * Created by zhangcong on 2017/5/11.
 */
public class Article {

    private int id;

    private String title;

    private String md;

    private String html;

    private int comments;

    private String author;

    private int pv;

    private String tags;

    private int category;

    private String outline;

    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", md='" + md + '\'' +
                ", html='" + html + '\'' +
                ", comments=" + comments +
                ", author='" + author + '\'' +
                ", pv=" + pv +
                ", tags='" + tags + '\'' +
                ", category=" + category +
                ", outline='" + outline + '\'' +
                ", date=" + date +
                '}';
    }
}
