package com.zc.blog.md;

/**
 * Created by zhangcong on 2017/4/9.
 */
public class Token {

    private TokenTypeE type;
    private String text;
    private int depth;
    private String lang;
    private boolean pre;
    private boolean ordered;

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public TokenTypeE getType() {
        return type;
    }

    public void setType(TokenTypeE type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public boolean isPre() {
        return pre;
    }

    public void setPre(boolean pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", depth=" + depth +
                ", lang='" + lang + '\'' +
                ", pre='" + pre + '\'' +
                ", ordered=" + ordered +
                '}';
    }
}
