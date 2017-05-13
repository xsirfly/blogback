package com.zc.blog.md;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Created by zhangcong on 2017/4/9.
 */
public class Lexer {

    public LexerResult lexer(String src){
        src = preproccess(src);
        LexerResult result = new LexerResult();
        token(src, true, false, result);
        return result;
    }

    public void token(String src, boolean top, boolean bq, LexerResult result) {
        Matcher cap = null;
        List<Token> tokens = result.getTokens();
        Map<String, Link> links = result.getLinks();
        Token token = new Token();
        Link link = new Link();
        while (src.length() > 0) {
            // newLine
            cap = BlcokRule.newLine.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                if (cap.group(0).length() > 1) {
                    token = new Token();
                    token.setType(TokenTypeE.SPACE);
                    tokens.add(token);
                }
            }

            // code
            cap = BlcokRule.code.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                String code = cap.group(0).replaceAll("^ {4}", "").replaceAll("\\n+$", "");
                token = new Token();
                token.setType(TokenTypeE.CODE);
                token.setText(code);
                tokens.add(token);
                continue;
            }

            // fences
            cap = BlcokRule.fence.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                token = new Token();
                token.setType(TokenTypeE.CODE);
                token.setLang(cap.group(2));
                token.setText(cap.group(3));
                tokens.add(token);
                continue;
            }

            // heading
            cap = BlcokRule.heading.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                token = new Token();
                token.setType(TokenTypeE.HEADING);
                token.setDepth(cap.group(1).length());
                token.setText(cap.group(2));
                tokens.add(token);
                continue;
            }

            // lheading
            cap = BlcokRule.lheading.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                token = new Token();
                token.setType(TokenTypeE.HEADING);
                token.setDepth(cap.group(2).equals("=") ? 1: 2);
                token.setText(cap.group(1));
                tokens.add(token);
                continue;
            }

            // hr
            cap = BlcokRule.hr.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                token = new Token();
                token.setType(TokenTypeE.HR);
                tokens.add(token);
                continue;
            }

            // blockquote
            cap = BlcokRule.blockQuote.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                token = new Token();
                token.setType(TokenTypeE.BLOCKQUOTE_START);
                tokens.add(token);
                token(cap.group(0).replaceAll("(?m)^ *> ?", ""), top, true, result);
                token = new Token();
                token.setType(TokenTypeE.BLOCKQUOTE_END);
                tokens.add(token);
                continue;
            }

            // list
            cap = BlcokRule.list.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                String bull = cap.group(2);
                token = new Token();
                token.setType(TokenTypeE.LIST_START);
                token.setOrdered(bull.length() > 1);
                tokens.add(token);

                cap = BlcokRule.item.matcher(cap.group(0));
                List<String> items = new ArrayList<>();
                while (cap.find()) {
                    items.add(cap.group(0));
                }
                boolean next = false;
                if (!items.isEmpty()) {
                    for (int i= 0; i<items.size(); i++) {
                        String item = items.get(i);

                        int space = item.length();
                        item = item.replaceAll("^ *([*+-]|\\d+\\.) +", "");

                        if (item.indexOf("\n ") > 0) {
                            space = space - item.length();
                            item = item.replaceAll("(?m)^ {1,4}", "");
                        }

                        boolean loose = next || item.matches("\\n\\n(?!\\s*$)");
                        if (i != items.size() - 1) {
                            next = !item.isEmpty() && item.charAt(item.length() - 1) == '\n';
                            if (!loose) {
                                loose = next;
                            }
                        }

                        token = new Token();
                        if (loose) {
                            token.setType(TokenTypeE.LOOSE_ITEM_START);
                        } else {
                            token.setType(TokenTypeE.LIST_ITEM_START);
                        }
                        tokens.add(token);

                        token(item, false, bq, result);

                        token = new Token();
                        token.setType(TokenTypeE.LIST_ITEM_END);
                        tokens.add(token);
                    }
                }
                token = new Token();
                token.setType(TokenTypeE.LIST_END);
                tokens.add(token);
                continue;
            }

            // html
            cap = BlcokRule.html.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                token = new Token();
                token.setType(TokenTypeE.HTML);
                token.setPre(cap.group(1).equals("pre") || cap.group(1).equals("script") || cap.group(1).equals("style"));
                token.setText(cap.group(0));
                tokens.add(token);
                continue;
            }

            // def
            if (!bq && top) {
                cap = BlcokRule.def.matcher(src);
                if (cap.find()) {
                    src = src.substring(cap.group(0).length());
                    link = new Link();
                    link.setHref(cap.group(2));
                    link.setTitle(cap.group(3));
                    links.put(cap.group(1).toLowerCase(), link);
                    continue;
                }
            }

            // top-level paragraph
            if (top) {
                cap = BlcokRule.paragraph.matcher(src);
                if (cap.find()) {
                    src = src.substring(cap.group(0).length());
                    token = new Token();
                    token.setType(TokenTypeE.PARAGRAPH);
                    if (cap.group(1).charAt(cap.group(1).length() - 1) == '\n') {
                        token.setText(cap.group(1).substring(0, cap.group(1).length() - 1));
                    } else {
                        token.setText(cap.group(1));
                    }
                    tokens.add(token);
                    continue;
                }
            }

            // text
            cap = BlcokRule.text.matcher(src);
            if (cap.find()) {
                src = src.substring(cap.group(0).length());
                token = new Token();
                token.setType(TokenTypeE.TEXT);
                token.setText(cap.group(0));
                tokens.add(token);
                continue;
            }

            if (src.length() > 0) {
                // TODO: 2017/4/16
            }
        }
    }

    public String preproccess(String src){
        return src.replaceAll("\\r\\n|\\r","\\n")
                .replaceAll("\\t", "    ")
                .replaceAll("\u00a0", " ")
                .replaceAll("\u2424", "\\n");
    }
}
