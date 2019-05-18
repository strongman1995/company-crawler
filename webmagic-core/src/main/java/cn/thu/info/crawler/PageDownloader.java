package cn.thu.info.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class PageDownloader {
    public static String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) ";
    private static int timeout = 8000;
    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            } }, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
    public static Document download(String url){
        Document doc = null;
        try {
            trustEveryone();
            Connection connection = Jsoup.connect(url);
            connection.userAgent(userAgent);
            connection.timeout(timeout);

            doc = connection.get();
            connection.response().statusCode();
        } catch (Exception e) {
            try {
                doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return doc;
    }
}
