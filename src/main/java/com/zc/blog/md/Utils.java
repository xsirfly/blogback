package com.zc.blog.md;

/**
 * Created by zhangcong on 2017/4/18.
 */
public class Utils {

    public static String escape(String html, boolean encode){
        if(!encode){
            html = html.replaceAll("&(?!#?\\w+;)", "&amp;");
        } else {
            html = html.replace("&", "&amp");
        }
        return html.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }

    public static String escape(String html) {
        return escape(html, false);
    }

    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    public static String or(String str1, String str2){
        if (!isEmpty(str1)) {
            return str1;
        } else {
            return str2;
        }
    }
}
