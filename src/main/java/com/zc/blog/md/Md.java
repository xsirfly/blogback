package com.zc.blog.md;

/**
 * Created by zhangcong on 2017/5/10.
 */
public class Md {
    public String parser(String mdStr) {
        Lexer lexer = new Lexer();
        LexerResult lexerResult = lexer.lexer(mdStr);
        Parser parser = new Parser();
        String html = parser.parse(lexerResult);
        return html;
    }
}
