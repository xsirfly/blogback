package com.zc.blog.md;

import java.util.regex.Pattern;

/**
 * Created by zhangcong on 2017/4/16.
 */
public class InlineRule {

    // only for convenience
    private static String _inside = "(?:\\[[^\\]]*\\]|[^\\[\\]]|\\](?=[^\\[]*\\]))*";
    private static String _href = "\\s*<?([\\s\\S]*?)>?(?:\\s+['\"]([\\s\\S]*?)['\"])?\\s*";

    // regex string
    private static String sescape = "^\\\\([\\\\`*{}\\[\\]()#+\\-.!_>])";
    private static String sautolink = "^<([^ >]+(@|:\\/)[^ >]+)>";
    private static String stag = "^<!--[\\s\\S]*?-->|^<\\/?\\w+(?:\"[^\"]*\"|'[^']*'|[^'\">])*?>";
    private static String snolink = "^!?\\[((?:\\[[^\\]]*\\]|[^\\[\\]])*)\\]";
    private static String sstrong = "^__([\\s\\S]+?)__(?!_)|^\\*\\*([\\s\\S]+?)\\*\\*(?!\\*)";
    private static String sem = "^\\b_((?:[^_]|__)+?)_\\b|^\\*((?:\\*\\*|[\\s\\S])+?)\\*(?!\\*)";
    private static String scode = "^(`+)\\s*([\\s\\S]*?[^`])\\s*\\1(?!`)";
    private static String sbr = "^ {2,}\\n(?!\\s*$)";
    private static String stext = "^[\\s\\S]+?(?=[\\\\<!\\[_*`]| {2,}\\n|$)";
    private static String slink = "^!?\\[(" + _inside + ")\\]\\(" + _href + "\\)";
    private static String sreflink = "^!?\\[(" + _inside + ")\\]\\s*\\[([^\\]]*)\\]";

    // pattern
    public static Pattern escape = Pattern.compile(sescape);
    public static Pattern autolink = Pattern.compile(sautolink);
    public static Pattern tag = Pattern.compile(stag);
    public static Pattern nolink = Pattern.compile(snolink);
    public static Pattern strong = Pattern.compile(sstrong);
    public static Pattern em = Pattern.compile(sem);
    public static Pattern code = Pattern.compile(scode);
    public static Pattern br = Pattern.compile(sbr);
    public static Pattern text = Pattern.compile(stext);
    public static Pattern link = Pattern.compile(slink);
    public static Pattern reflink = Pattern.compile(sreflink);

}
