package cn.thu.info.crawler.company;

import java.util.ArrayList;
import java.util.List;


public class CompanyProcessorFactory {
    public static List<CompanyProcessor> getAllProcessor(){
        List res = new ArrayList<CompanyProcessor>();
        res.add(new CyzonProcessor());
        return res;
    }
}
