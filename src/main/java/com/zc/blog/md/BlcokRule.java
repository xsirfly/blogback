package com.zc.blog.md;

import java.util.regex.Pattern;

/**
 * Created by zhangcong on 2017/4/9.
 */
public class BlcokRule {

    // only for convenience
    private static String _bullet = "(?:[*+-]|\\d+\\.)";
    private static String _tag = "(?!(?:a|em|strong|small|s|cite|q|dfn|abbr|data|time|code|var|samp|kbd|sub|sup|i|b|u|mark|ruby|rt|rp|bdi|bdo|span|br|wbr|ins|del|img)\\b)\\w+(?!:/|[^\\w\\s@]*@)\\b";
    private static String _comment = "<!--[\\s\\S]*?-->";
    private static String _closed = "<(" + _tag + ")[\\s\\S]+?<\\/\\1>";
    private static String _closing = "<" + _tag + "(?:\"[^\"]*\"|'[^']*'|[^'\">])*?>";

    // regex string
    private static String snewLine = "^\n+";
    private static String scode = "^( {4}[^\n]+\n*)+";
    private static String sheading = "^ *(#{1,6}) *([^\\n]+?) *#* *(?:\\n+|$)";
    private static String slheading = "^([^\\n]+)\\n *(=|-){2,} *(?:\\n+|$)";
    private static String stext = "^[^\n]+";
    private static String shr = "^( *[-*_]){3,} *(?:\\n+|$)";
    private static String sdef = "^ *\\[([^\\]]+)\\]: *<?([^\\s>]+)>?(?: +[\"(]([^\\n]+)[\")])? *(?:\\n+|$)";
    private static String sitem = "^( *)(" + _bullet + ") [^\\n]*(?:\\n(?!\\1" + _bullet + " )[^\\n]*)*";
    private static String slist = "^( *)(" + _bullet + ") [\\s\\S]+?(?:" + "\\n+(?=\\1?(?:[-*_] *){3,}(?:\\n+|$))" + "|\\n+(?=" + removeLineStart(sdef) + ")|\\n{2,}(?! )(?!\\1" + _bullet + " )\\n*|\\s*$)";
    private static String sblockQuote = "^( *>[^\\n]+(\\n(?!"+ removeLineStart(sdef) + ")[^\\n]+)*\\n*)+";
    private static String shtml = "^ *(?:" + _comment + " *(?:\\n|\\s*$)|" + _closed + " *(?:\\n{2,}|\\s*$)|" + _closing + " *(?:\\n{2,}|\\s*$))";
    private static String sfence = "^ *(`{3,}|~{3,})[ \\.]*(\\S+)? *\\n([\\s\\S]*?)\\s*\\1 *(?:\\n+|$)";
    private static String sparagraph = "^((?:[^\\n]+\\n?(?!" + removeLineStart(shr) + "|" + removeLineStart(sheading) + "|" + removeLineStart(slheading) + "|" + removeLineStart(sblockQuote) + "|<" + _tag + "|" + removeLineStart(sdef) + "))+)\\n*";

    // pattern
    public static Pattern newLine = Pattern.compile(snewLine);
    public static Pattern code = Pattern.compile(scode);
    public static Pattern heading = Pattern.compile(sheading);
    public static Pattern lheading = Pattern.compile(slheading);
    public static Pattern text = Pattern.compile(stext);
    public static Pattern hr = Pattern.compile(shr);
    public static Pattern def = Pattern.compile(sdef);
    public static Pattern item = Pattern.compile(sitem, Pattern.MULTILINE);
    public static Pattern list = Pattern.compile(slist);
    public static Pattern blockQuote = Pattern.compile(sblockQuote);
    public static Pattern html = Pattern.compile(shtml);
    public static Pattern fence = Pattern.compile(sfence);
    public static Pattern paragraph = Pattern.compile(sparagraph.replace("(?!", "(?!" + removeLineStart(sfence).replace("\\1", "\\2") + "|" + removeLineStart(slist).replace("\\1", "\\3") + "|"));


    private static String removeLineStart(String regex){
        return regex.replaceAll("(^|[^\\[])\\^", "$1");
    }
}
