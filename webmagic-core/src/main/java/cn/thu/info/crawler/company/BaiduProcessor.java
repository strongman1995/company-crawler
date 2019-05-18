package cn.thu.info.crawler.company;

import cn.thu.info.utils.BaiduQueryBuilder;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;


public class BaiduProcessor implements PageProcessor {
    private Site site = Site
            .me()
            .setSleepTime(10)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2)" +
                            " AppleWebKit/537.31 (KHTML, like Gecko) Chrome" +
                            "/26.0.1410.65 Safari/537.31");
    public String query;

    public String getSource() {
        return "Baidu搜索";
    }

    public void work() {
        Spider.create(new BaiduProcessor())
                .addUrl(BaiduQueryBuilder.getNextQuery(null, query))
                .thread(1)
                //.addPipeline(new ConsolePipeline()).addPipeline(new NewsMysqlPipeline())
                .run();

    }

    @Override
    public void process(Page page) {
        List<Selectable> searchResults = page.getHtml()
                .$("#content_left .result").nodes();
        for (Selectable item : searchResults){
            Html subHtml = new Html(item.toString());
            String url = subHtml.links().get();
            String title = subHtml.$("a", "text").get();

            for (CompanyProcessor processor : CompanyProcessorFactory.getAllProcessor()){
                /*if(processor.canApply(url, title)){
                    processor.process(page);
                }*/
            }
        }
        String next = BaiduQueryBuilder.getNextQuery(page.getUrl().toString(), query);
        if(next!=null){
            page.addTargetRequest(next);
        }
    }

    @Override
    public Site getSite() {
        return null;
    }

    public static void main(String[] args) {
        new BaiduProcessor().work();
    }
}
