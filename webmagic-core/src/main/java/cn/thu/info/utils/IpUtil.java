package cn.thu.info.utils;

import cn.thu.info.Config;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by zhangbo on 2016/10/8.
 */
public class IpUtil {
    private static Logger logger = LoggerFactory.getLogger(IpUtil.class);
    public static String getIpLocation(String url){
        String location = "获取位置信息异常";
        try{
            String jsonStr = Jsoup.connect(Config.IP_API + getIP(url)).ignoreContentType(true).execute().body();
            JSONObject ipJson = JSONObject.parseObject(jsonStr);
            location = ipJson.getString("country") + " " + ipJson.getString("province") + " " + ipJson.getString("city");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Get ip location exception!");
        }
        return location;
    }

    private static String getIP(String name){
        InetAddress address = null;
        try {
            if(!name.startsWith("http")){
                name = "http://" + name;
            }
            URL url = new URL(name);
            String href = url.getHost();
            address = InetAddress.getByName(href);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Get ip exception!");
        }
        return address.getHostAddress().toString();
    }

    public static void main(String[] args) {
        System.out.println(IpUtil.getIpLocation("http://www.renrendai.com"));
    }
}
