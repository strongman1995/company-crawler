package cn.thu.info.utils;

import cn.thu.info.model.Amount;
import cn.thu.info.model.Currency;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangbo on 2016/6/2.
 */
public class Util {
    private static Logger logger = LoggerFactory.getLogger(Util.class);
    public static String[] filterCode = {
            "display_content&quot;:&quot;",
            "display_content",
            "author&quot;\n",
            "&quot;",
            "/p",
            "\\u003cp",
            "\\u003e",
            "\\u003ci",
            "\\u003cb",
            "/b",
            "\\u003c"
            };
    public static Map<String, String> htmlTagMap = new HashMap<String, String>();
    static {
        htmlTagMap.put("&quot;","\"");
        htmlTagMap.put("&amp;", "&");
        htmlTagMap.put("&lt;", "<");
        htmlTagMap.put("&gt;", ">");
        htmlTagMap.put("&nbsp;", " ");
    }
    public static String convert(String str){
        for (Map.Entry<String, String> pair : htmlTagMap.entrySet()){
            str = str.replace(pair.getKey(), pair.getValue());
        }
        return str;
    }
    public static String filter(String str){
        str = convert(str);
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(i==str.length()-2){
                c = str.charAt(i);
            }else {
                if (str.charAt(i) == '\\' && str.charAt(i + 1) == 'u') {
                    StringBuffer current = new StringBuffer();
                    int limit = i + 5;
                    for (i = i + 2; i <= limit; i++) {
                        current.append(str.charAt(i));
                    }
                    i--;
                    int data = Integer.parseInt(current.toString(), 16);
                    c = (char)data;
                }
            }
            buffer.append(c);
        }
        str = removeHtmlTag(buffer.toString());
        return str;
    }

    private static String removeHtmlTag(String str) {
        //String
        return str;
    }

    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            int data = Integer.parseInt(hex[i], 16);

            string.append((char) data);
        }

        return string.toString();
    }
    public static String mkString(List<Selectable> list, String tag){
        StringBuffer buffer = new StringBuffer();
        for (Selectable s : list){
            Html h = new Html(s.toString());
            buffer.append(h.$(tag, "text"));
        }
        return buffer.toString();
    }
    public static String mkStringList(List<String> list, String split){
        StringBuffer buffer = new StringBuffer();
        for (String s : list){
            buffer.append(s);
            buffer.append(split);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(Util.underlineToCamel("set_f_date"));
    }

    public static int parseAmountToNumber(Object amount) {
        if(amount == null){
            return 0;
        }else if(StringUtils.isEmpty((String)amount)){
            return 0;
        }
        try {
            String str = (String) amount;
            str = str.trim();
            String oldStr = str;
            do {
                oldStr = str;
                str = str.replace(parseAmountToCurrencyStr(str), "").trim();
            } while (oldStr.length() != str.length());
            for (Amount amo : Amount.values()) {
                if (str.endsWith(amo.str)) {
                    String amoStr = str.replace(amo.str, "").trim();
                    Double douAmount;
                    if(amoStr.contains("数")){
                        douAmount = 5.0;
                    }else {
                        douAmount = Double.parseDouble(amoStr);
                    }

                    return (int) (douAmount * amo.unit);
                }
            }
        }catch (Exception e){
            logger.error("Unknown amount " + amount);
        }
        logger.warn("Unknown amount " + amount);
        return 0;
    }

    public static String parseAmountToCurrency(Object amount) {
        if(amount == null){
            return StringUtils.EMPTY;
        }
        String str = (String)amount;
        for (Currency cur : Currency.values()){
            if(str.endsWith(cur.value)){
                return cur.type;
            }
        }
        return Currency.RMB.type;
    }

    public static String parseAmountToCurrencyStr(Object amount) {
        if(amount == null){
            return StringUtils.EMPTY;
        }
        String str = (String)amount;
        for (Currency cur : Currency.values()){
            if(str.endsWith(cur.value)){
                return cur.value;
            }
        }
        return Currency.RMB.value;
    }
    public static Date parseDate(String date){
        //"yyyy-MM-dd HH:mm:ss"
        return parseDate(date, "yyyy-MM-dd");
    }
    public static String dateParseString(Date date){
        return dateParseString(date, "yyyy-MM-dd");
    }
    public static String dateParseString(Date date, String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        if(date != null) {
            return dateFormat.format(date);
        }else {
            return StringUtils.EMPTY;
        }
    }
    public static Date parseDate(String date, String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date resDate = dateFormat.parse(date);
            return resDate;
        }catch (Exception e){
            logger.error("Wrong date format. " + date + "  " + format);
        }
        return null;
    }

    public static boolean isObjEmpty(Object obj){
        if(obj == null) return true;
        if(obj.toString().length() == 0) return true;
        return false;
    }

    public static String ObjToString(Object obj){
        if(isObjEmpty(obj)) return "NONE";
        else return obj.toString();
    }
    public static final char UNDERLINE='_';
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if(i+2 < len && param.charAt(i+2)==UNDERLINE){
                    ++i;
                    sb.append(param.charAt(i));
                }else if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }
    public static String parseText(String name) {
        Document doc = Jsoup.parse(name);
        return doc.text();
    }
    public static String urlNormalizer(String url){
        return url.replace("_", "\\underline{ }");
    }
}
