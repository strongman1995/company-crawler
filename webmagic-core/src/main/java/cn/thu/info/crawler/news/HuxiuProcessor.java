package cn.thu.info.crawler.news;

import cn.thu.info.utils.Util;
import cn.thu.info.pipeline.NewsMysqlPipeline;
import com.hankcs.hanlp.HanLP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class HuxiuProcessor extends NewsProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static String startUrl = "http://m.huxiu.com/maction/article_list?page=";
    public static String detailUrl = "http://m.huxiu.com/article/";
    public static String basicUrl = "http://m.huxiu.com";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static AtomicInteger totalSize = new AtomicInteger(2);
    public static int maxListSize = 500;
    @Override
    public void process(Page page) {
        if(page.getUrl().toString().startsWith(startUrl)){
            processListPage(page);
        }else if(page.getUrl().toString().startsWith(detailUrl)){
            processDetailPage(page);
        }else {
            logger.warn("Unknown page url " + page.getUrl());
        }
    }

    private void processDetailPage(Page page) {
        String title = page.getHtml().$("#article .title","text").toString();
        page.putField("title", title);

        String date = page.getHtml().$("#article .article-author-box span", "text")
                .nodes().get(2).toString();
        if(date.contains("小时前")){
            date = sdf.format(new Date());
        }else if(date.contains("天前")){
            String day = new Html(date).regex("\\d+").toString();
            Date now = new Date();
            Calendar rightNow  =  Calendar.getInstance();
            rightNow.setTime(now);
            rightNow.add(Calendar.DATE, -1 * Integer.parseInt(day));
            date = sdf.format(rightNow.getTime());
        }
        page.putField("date", date);

        String image = page.getHtml().$("#article_content img", "src").toString();
        page.putField("image", image);

        String content = Util.mkString(page.getHtml().$("#article_content .article-content p").nodes(), "p");
        page.putField("content", content);

        String subId = page.getUrl().regex("article/(.*)/").regex("\\d+").toString();
        page.putField("sub_id", subId);

        String url = page.getUrl().toString();
        page.putField("url", url);

        page.putField("source", getSource());

        List<String> sentenceList = HanLP.extractSummary(content, 2);
        page.putField("summary", Util.mkStringList(sentenceList, " "));

        List<String> keywordList = HanLP.extractKeyword(content, 3);
        page.putField("tag", Util.mkStringList(keywordList, "_"));

    }

    private void processListPage(Page page) {
        String data = page.getJson().jsonPath(".data").toString();
        Html dataHtml = new Html(data);
        List<Selectable> itemList = dataHtml.$("li").nodes();
        for (Selectable item : itemList){
            String url = item.links().get();
            page.addTargetRequest(basicUrl + url);
        }
        int size = totalSize.incrementAndGet();
        logger.error("Current size " + size);
        if(size < maxListSize)
            page.addTargetRequest(startUrl + size);
    }

    @Override
    public String getSource() {
        return "虎嗅新闻";
    }

    @Override
    public void work() {
        Spider.create(new HuxiuProcessor())
                .addUrl(startUrl + "2")
                .thread(5)
                .addPipeline(new ConsolePipeline()).addPipeline(new NewsMysqlPipeline())
                .run();
    }

    public static void main(String[] args) {
        new HuxiuProcessor().work();
    }
}
