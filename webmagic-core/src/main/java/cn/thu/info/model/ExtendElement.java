package cn.thu.info.model;

import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;


public class ExtendElement{

    public ExtendElement(Element element){
        this.element = element;
    }
    public Element element;

    public String text() {
        final StringBuilder accum = new StringBuilder();
        new NodeTraversor(new NodeVisitor() {
            public void head(Node node, int depth) {
                if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    appendNormalisedText(accum, textNode);
                } else if (node instanceof Element) {
                    Element element = (Element) node;

                    if (accum.length() > 0 &&
                            (element.isBlock() || element.tagName().equals("br")) &&
                            !lastCharIsWhitespace(accum))
                        accum.append("。");
                }
            }

            public void tail(Node node, int depth) {
            }
        }).traverse(element);
        return accum.toString().trim();
    }

    public boolean lastCharIsWhitespace(StringBuilder sb) {
        return sb.length() != 0 && sb.charAt(sb.length() - 1) == ' ';
    }

    static boolean preserveWhitespace(Node node) {
        // looks only at this element and one level up, to prevent recursion & needless stack searches
        if (node != null && node instanceof Element) {
            Element element = (Element) node;
            return element.tag().preserveWhitespace() ||
                    element.parent() != null && element.parent().tag().preserveWhitespace();
        }
        return false;
    }


    private double calcChineseRate(String text){
        int len = 0;
        for (char c : text.toCharArray()){
            if((c >= 0x4e00)&&(c <= 0x9fbb)) {
                len ++;
            }
        }
        if(text.length()>0){
            return len * 1.0 / text.length();
        }else {
            return 0;
        }
    }
    private void appendNormalisedText(StringBuilder accum, TextNode textNode) {
        String text = textNode.getWholeText();
        text.replace(".", "。");
        if(!text.endsWith("。")){
            text += "。";
        }
        double rate = calcChineseRate(text);
        if(rate < 0.5){
            return;
        }
        if(text.length() < 8){
            return ;
        }
        if (preserveWhitespace(textNode.parentNode())){
            accum.append(text);
        }
        else {
            StringUtil.appendNormalisedWhitespace(accum, text, lastCharIsWhitespace(accum));
        }
    }
}
