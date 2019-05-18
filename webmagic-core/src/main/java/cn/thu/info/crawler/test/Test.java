package cn.thu.info.crawler.test;

import cn.thu.info.utils.IpUtil;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangbo on 2016/10/9.
 */
public class Test {
    public static void main(String[] args) {
        String timeStr = "京ICP证 100953号";
        Pattern pattern = Pattern.compile(".ICP证.*号");
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
        }
        System.out.println(IpUtil.getIpLocation("www.baidu.com"));
    }
}
