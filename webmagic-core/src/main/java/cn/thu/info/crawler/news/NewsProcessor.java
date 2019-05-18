package cn.thu.info.crawler.news;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class NewsProcessor implements PageProcessor {
    private Site site = Site
            .me()
            .setSleepTime(10)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2)" +
                            " AppleWebKit/537.31 (KHTML, like Gecko) Chrome" +
                            "/26.0.1410.65 Safari/537.31");

    @Override
    public Site getSite() {
        return site;
    }

    public abstract String getSource();

    public abstract void work();
}
