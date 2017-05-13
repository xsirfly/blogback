package com.zc.blog.md;

/**
 * Created by zhangcong on 2017/4/16.
 */
public class Renderer {

    public String code(String code, String lang, boolean escaped){
        if(lang != null){
            StringBuilder sb = new StringBuilder();
            sb.append("<pre><code class=\"" + "lang-" + Utils.escape(lang, true) + "\">");
            if(escaped){
                sb.append(Utils.escape(code, true));
            } else {
                sb.append(code);
            }
            sb.append("\n</code></pre>\n");
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("<pre><code>");
            if(escaped){
                sb.append(code);
            } else {
                sb.append(Utils.escape(code, true));
            }
            sb.append("\n</code></pre>\n");
            return sb.toString();
        }
    }

    public String blockquote(String quote){
        return "<blockquote>\n" + quote + "</blockquote>\n";
    }

    public String html(String html){
        return html;
    }

    public String heading(String text, int level, String raw){
        return "<h" + level + " id=\""  +
                raw.toLowerCase().replaceAll("[^\\w]+", "-") + "\">" + text + "</h" + level + ">\n";
    }

    public String hr() {
        return "<hr>\n";
    }

    public String list(String body, boolean ordered){
        String listType;
        if(ordered){
            listType = "ol";
        } else {
            listType = "ul";
        }
        return "<" + listType + ">\n" + body + "</" + listType + ">\n";
    }

    public String listitem(String text){
        return "<li>" + text + "</li>\n";
    }

    public String paragraph(String text){
        return "<p>" + text + "</p>\n";
    }

    public String strong(String text){
        return "<strong>" + text + "</strong>";
    }

    public String em(String text){
        return "<em>" + text + "</em>";
    }

    public String codespan(String text){
        return "<code>" + text + "</code>";
    }

    public String br(){
        return "<br>";
    }

    public String del(String text){
        return "<del>" + text + "</del>";
    }

    public String link(String href, String title, String text){
        String titleAttr = "";
        if(title != null){
            titleAttr = " title=\"" + title + "\"";
        }
        return "<a href=\"" + href + "\"" + titleAttr + ">" + text + "</a>";
    }

    public String image(String href, String title, String text){
        String titleAttr = "";
        if(title != null){
            titleAttr = " title=\"" + title + "\"";
        }

        return "<img src=\"" + href + "\" alt=\"" + text + "\"" + titleAttr + ">";
    }

    public String nolink(String text){
        return Utils.escape(text, false);
    }

    public String text(String text){
        return text;
    }
}
