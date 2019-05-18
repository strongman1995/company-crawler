package cn.thu.info.utils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhangbo on 2016/6/15.
 */
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) ";
    private static int timeout = 8000;
    private static int retryTime = 3;
    public static String imgPath = "img/";
    public static String download(String path) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        String succ = path.substring(path.lastIndexOf("."), path.length());
        String fileName = UUIDGenerator.getUUID() + succ;
        /*int bytesum = 0;
        int byteread = 0;
        try {
            URL url = new URL(path);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(imgPath + fileName);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //Open a URL Stream
        int rTime = 0;
        while (rTime < retryTime) {
            rTime ++;
            Connection.Response resultImageResponse = null;
            try {
                resultImageResponse = Jsoup.connect(path).userAgent(userAgent).timeout(timeout).ignoreContentType(true).execute();
                // output here
                FileOutputStream out = (new FileOutputStream(new java.io.File(imgPath + fileName)));
                out.write(resultImageResponse.bodyAsBytes());
                // resultImageResponse.body() is where the image's contents are.
                out.close();
                rTime = retryTime;
                logger.info("Download image to " + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    public static void main(String[] args) {
        HttpUtil.download("http://www.itjuzi.com/images/be137dc375a0b1a66c6b462b05d8b22c.png");
    }
}
