package cn.thu.info;

import cn.thu.info.utils.DataQueryUtil;

import java.util.List;


public class NewsQueryExecutor {


    public void work(){
        List<String> queryList = CompanyQueryExecutor.getQueryList();
        for (String query : queryList){
            System.out.println(query);
            DataQueryUtil.query(query);
        }
    }
    public void work(String query){
        DataQueryUtil.query(query);
    }
    public static void main(String[] args) {
        new NewsQueryExecutor().work();
    }
}
