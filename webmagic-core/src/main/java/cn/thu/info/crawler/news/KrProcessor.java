package cn.thu.info.crawler.news;

import cn.thu.info.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class KrProcessor extends NewsProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static String startUrl = "http://36kr.com/news";
    public static String listUrl = "http://36kr.com/asynces/posts/info_flow_post_more.json?b_url_code=";
    public static String detailUrl = "http://36kr.com/p/";
    public static AtomicInteger totalSize = new AtomicInteger(0);
    public static int maxListSize = 1;
    public static Map<String, Map<String,Object>> globalCache = new HashMap<String, Map<String, Object>>();
    @Override
    public void process(Page page) {
        if(page.getUrl().toString().startsWith(startUrl)){
            processStartPage(page);
        }else if(page.getUrl().toString().startsWith(listUrl)){
            processListPage(page);
        }else if(page.getUrl().toString().startsWith(detailUrl)){
            processDetailPage(page);
        }else {
            logger.warn("Unknown page url " + page.getUrl());
        }

    }

    private void processDetailPage(Page page) {
        String pageContent = page.getHtml().$(".js-react-on-rails-component")
                .regex("display_content&quot;(.*)author&quot;").toString();
        String content = Util.filter(pageContent);
        String urlCode = page.getUrl().regex("p/(.*)").regex("\\d+").toString();
        Map<String, Object> infoMap = globalCache.get(urlCode);
        for (Map.Entry<String, Object> pair : infoMap.entrySet()){
            page.putField(pair.getKey(), pair.getValue());
        }
        page.putField("content", content);
        page.putField("sub_id", urlCode);
        page.putField("source", getSource());
        page.putField("url", page.getUrl().toString());
        globalCache.remove(urlCode);
    }

    private void processListPage(Page page) {
        Json listData = page.getJson();
        logger.info("Get list " + page.getUrl());
        List<Selectable> data = listData.jsonPath(".data.feed_posts").nodes();
        String lastUrlCode = "";
        for(Selectable item : data){
            Json itemJ = new Json(item.toString());
            Map<String, Object> infoMap = new HashMap<String, Object>();
            infoMap.put("summary", itemJ.jsonPath(".summary"));
            infoMap.put("title", itemJ.jsonPath(".title"));
            infoMap.put("date", itemJ.jsonPath(".created_at"));
            infoMap.put("image", itemJ.jsonPath(".cover"));
            StringBuffer tagBuffer = new StringBuffer();
            List<Selectable> tagList = itemJ.jsonPath(".tag_list").nodes();
            for (Selectable tag : tagList){
                tagBuffer.append(tag.toString() + "_");
            }
            infoMap.put("tag", tagBuffer.toString());
            String urlCode = itemJ.jsonPath(".url_code").toString();
            globalCache.put(urlCode, infoMap);
            lastUrlCode = urlCode;
            page.addTargetRequest(detailUrl + urlCode);
        }
        if(lastUrlCode.length() > 0){
            int size = totalSize.incrementAndGet();
            logger.error("Current size " + size + " last url code = "+ lastUrlCode);
            if(size < maxListSize)
                page.addTargetRequest(listUrl + lastUrlCode);
        }
    }

    private void processStartPage(Page page) {
        String firstIdStr = "null";
        page.addTargetRequest(listUrl + firstIdStr);
    }

    @Override
    public String getSource() {
        return "36kr新闻";
    }

    @Override
    public void work() {
        Spider.create(new KrProcessor())
                .addUrl(startUrl)
                .thread(1)
                //.addPipeline(new ConsolePipeline()).addPipeline(new NewsMysqlPipeline())
                .run();
    }

    public static void main(String[] args) {
        new KrProcessor().work();
    }
}
