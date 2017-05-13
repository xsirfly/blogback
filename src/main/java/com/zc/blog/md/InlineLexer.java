package com.zc.blog.md;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangcong on 2017/4/16.
 */
public class InlineLexer {

    private Renderer renderer;
    private Map<String, Link> links;
    private boolean inLink = false;


    public InlineLexer(Map<String, Link> links, Renderer renderer) {
        this.renderer = renderer;
        this.links = links;
    }

    public String output(String src) {
        StringBuilder out = new StringBuilder();
        Matcher cap = null;
        while(src.length() > 0) {
            // escape
            cap = InlineRule.escape.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                out.append(cap.group(1));
                continue;
            }

            // autolink
            cap = InlineRule.autolink.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                String text, href;
                if ("@".equals(cap.group(2))) {
                    if (cap.group(1).startsWith("mailto:")) {
                        text = cap.group(1).substring(7);
                    } else {
                        text = cap.group(1);
                    }
                    href = "mailto:" + text;
                } else {
                    text = Utils.escape(cap.group(1));
                    href = text;
                }
                out.append(renderer.link(href, null, text));
                continue;
            }

            // tag
            cap = InlineRule.tag.matcher(src);
            if (cap.find()) {
                if (!inLink && Pattern.compile("(?i)^<a").matcher(cap.group(0)).find()) {
                    inLink = true;
                } else if (inLink && Pattern.compile("(?i)^</a>").matcher(cap.group(0)).find()) {
                    inLink = false;
                }

                src = src.substring(cap.group(0).length());
                out.append(Utils.escape(cap.group(0)));
                continue;
            }

            // link
            cap = InlineRule.link.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                inLink = true;
                out.append(outputLink(cap, new Link(cap.group(2), cap.group(3))));
                inLink = false;
                continue;
            }

            // reflink, nolink
            cap = InlineRule.reflink.matcher(src);
            boolean isfind = cap.find();
            if (!isfind) {
                cap = InlineRule.nolink.matcher(src);
                isfind = cap.find();
            }
            if (isfind) {
                src = src.substring(cap.group(0).length());
                String key;
                if (cap.groupCount() >= 2) {
                    key = cap.group(2).replaceAll("\\s+", "");
                } else {
                    key = cap.group(1).replaceAll("\\s+", "");
                }
                Link link = this.links.get(key.toLowerCase());
                if (link == null || Utils.isEmpty(link.getHref())) {
                    out.append(cap.group(0).charAt(0));
                    src = cap.group(0).substring(1) + src;
                    continue;
                }
                inLink = true;
                out.append(outputLink(cap, link));
                inLink = false;
                continue;
            }

            // strong
            cap = InlineRule.strong.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                out.append(renderer.em(output(Utils.or(cap.group(2), cap.group(1)))));
                continue;
            }

            // em
            cap = InlineRule.em.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                out.append(renderer.strong(output(Utils.or(cap.group(2), cap.group(1)))));
                continue;
            }

            // code
            cap = InlineRule.code.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                out.append(renderer.codespan(Utils.escape(cap.group(2), true)));
                continue;
            }

            // br
            cap = InlineRule.br.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                out.append(renderer.br());
                continue;
            }

            // text
            cap = InlineRule.text.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                out.append(Utils.escape(cap.group(0)));
                continue;
            }

            // TODO: 2017/4/19 error handle 

        }

        return out.toString();
    }

    private String outputLink(Matcher cap, Link link){
        String href = Utils.escape(link.getHref());
        if(cap.group(0).charAt(0) != '!'){
            return renderer.link(href, link.getTitle(), output(cap.group(1)));
        } else {
            return renderer.image(href, link.getTitle(), Utils.escape(cap.group(1)));
        }
    }
}
