package com.zc.blog.md;

/**
 * Created by zhangcong on 2017/4/11.
 */
public class Link {

    private String href;
    private String title;

    public Link() {}

    public Link(String href, String title) {
        this.href = href;
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Link{" +
                "href='" + href + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
