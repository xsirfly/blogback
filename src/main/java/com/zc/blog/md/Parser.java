package com.zc.blog.md;

import java.util.Stack;

/**
 * Created by zhangcong on 2017/4/16.
 */
public class Parser {
    private Renderer renderer;
    private Stack<Token> tokens;
    private InlineLexer inlineLexer;

    public Parser() {
        this.renderer = new Renderer();
    }

    public Parser(Renderer renderer) {
        this.renderer = renderer;
    }

    public String parse(LexerResult lexerResult) {
        if (lexerResult == null || (lexerResult.getTokens().isEmpty()
                && lexerResult.getLinks().isEmpty())) {
            return "";
        }
        this.inlineLexer = new InlineLexer(lexerResult.getLinks(), this.renderer);
        tokens = new Stack<>();
        StringBuilder out = new StringBuilder();
        for (int i = lexerResult.getTokens().size() - 1; i >= 0; i--) {
            tokens.push(lexerResult.getTokens().get(i));
        }
        Token token = this.nextToken();
        while(token != null) {
            out.append(this.tok(token));
            token = this.nextToken();
        }
        return out.toString();
    }

    private Token nextToken() {
        if (tokens.isEmpty()) {
            return null;
        } else {
            Token token = tokens.pop();
            return token;
        }
    }

    private Token peekLastToken() {
        return tokens.isEmpty() ? null : tokens.get(tokens.size() - 1);
    }

    private String parserText(Token token) {
        StringBuilder body = new StringBuilder(token.getText());
        Token last = this.peekLastToken();
        while (last != null && last.getType().equals(TokenTypeE.TEXT)) {
            body.append("\n" + this.nextToken().getText());
        }
        return this.inlineLexer.output(body.toString());
    }

    private String tok(Token token) {
        switch (token.getType()) {
            case SPACE: {
                return "";
            }
            case HR: {
                return renderer.hr();
            }
            case HEADING: {
                return renderer.heading(token.getText(), token.getDepth(), token.getText());
            }
            case CODE: {
                return renderer.code(token.getText(), token.getLang(), true);
            }
            case BLOCKQUOTE_START: {
                StringBuilder body = new StringBuilder();
                while(true) {
                    Token next = nextToken();
                    if (next == null || next.getType().equals(TokenTypeE.BLOCKQUOTE_END)) {
                        break;
                    }
                    body.append(tok(next));
                }
                return renderer.blockquote(body.toString());
            }
            case LIST_START: {
                StringBuilder out = new StringBuilder();
                while(true) {
                    Token next = nextToken();
                    if (next == null || next.getType().equals(TokenTypeE.LIST_END)) {
                        break;
                    }
                    out.append(tok(next));
                }
                return renderer.list(out.toString(), token.isOrdered());
            }
            case LIST_ITEM_START: {
                StringBuilder out = new StringBuilder();
                while(true) {
                    Token next = this.nextToken();
                    if (next == null || next.getType().equals(TokenTypeE.LIST_ITEM_END)) {
                        break;
                    }
                    if (next.getType().equals(TokenTypeE.TEXT)) {
                        out.append(parserText(next));
                    } else {
                        out.append(tok(next));
                    }
                }
                return renderer.listitem(out.toString());
            }
            case LOOSE_ITEM_START: {
                StringBuilder out = new StringBuilder();
                while(true) {
                    Token next = this.nextToken();
                    if (next == null || next.getType().equals(TokenTypeE.LIST_ITEM_END)) {
                        break;
                    }
                    out.append(this.tok(next));
                }
                return renderer.listitem(out.toString());
            }
            case HTML: {
                if (!token.isPre()) {
                    return renderer.html(this.inlineLexer.output(token.getText()));
                } else {
                    return renderer.html(token.getText());
                }
            }
            case PARAGRAPH: {
                return renderer.paragraph(this.inlineLexer.output(token.getText()));
            }
            case TEXT: {
                return renderer.paragraph(parserText(token));
            }
            default: {
                throw new RuntimeException("Unexpected token: " + token);
            }
        }

    }
}
