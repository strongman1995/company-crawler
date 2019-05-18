package cn.thu.info.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbo on 2016/6/2.
 */
public class BaiduQueryBuilder {
    public static String queryUrl = "http://www.baidu.com/s?wd=site%3A";
    public static String[] candidates = {
            "newseed.pedaily.cn",
            "itjuzi.com",
            "www.cyzone.cn"
    };
    public static List<String> queryBuilder(String query){
        List<String> queries = new ArrayList<String>();
        for (String candidate : candidates){
            queries.add(queryUrl + candidate + "%20" + query);
        }
        return queries;
    }
    public static String getNextQuery(String current, String query){
        if(current == null){
            return queryBuilder(query).get(0);
        }
        boolean find = false;
        for (String cQuery : queryBuilder(query)){
            if(find){
                return cQuery;
            }
            if(cQuery.equals(current)){
                find = true;
            }
        }
        return null;
    }
}
