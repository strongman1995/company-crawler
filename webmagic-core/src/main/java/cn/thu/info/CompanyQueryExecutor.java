package cn.thu.info;

import cn.thu.info.crawler.company.CyzonProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class CompanyQueryExecutor {
    public static String queryPath = "query.data";

    public static List<String> getQueryList() {
        List<String> res = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(queryPath));
            String line = null;
            while ((line = in.readLine()) != null) {
                res.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void work() {
        List<String> queryList = getQueryList();
        CyzonProcessor processor = new CyzonProcessor();
        for (String query : queryList) {
            processor.work(1);
        }
    }

    public void work(String query) {
        CyzonProcessor processor = new CyzonProcessor();
        processor.work(1);
    }

    public static void main(String[] args) {
        new CompanyQueryExecutor().work();
    }
}
