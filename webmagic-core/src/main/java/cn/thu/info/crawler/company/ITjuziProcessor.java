package cn.thu.info.crawler.company;

import cn.thu.info.mapper.CompanyMapper;
import cn.thu.info.model.CompanyExample;
import cn.thu.info.model.Company;
import cn.thu.info.model.Report;
import cn.thu.info.pipeline.CompanyPipleline;
import cn.thu.info.utils.SessionHelper;
import cn.thu.info.utils.Util;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ITjuziProcessor extends CompanyProcessor {
    private static String basicUrl = "http://www.itjuzi.com/company?page=";
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static int maxPageSize = 3247;
    private CompanyMapper companyOp = SessionHelper.getSession().getMapper(CompanyMapper.class);
    @Override
    public String getSource() {
        return "";
    }

    @Override
    public void work(int startId) {
        Spider.create(new ITjuziProcessor())
                .addUrl(basicUrl + startId )
                        //.addUrl("http://www.cyzone.cn/r/20150704/17459.html")
                .thread(1)
                .addPipeline(new ConsolePipeline()).addPipeline(new CompanyPipleline())
                .run();
    }

    @Override
    public void process(Page page) {
        if(page.getUrl().toString().startsWith(basicUrl)){
            processListPage(page);
        }else {
            processDetailPage(page);
        }

    }

    private void processDetailPage(Page page) {
        processCompanyInfo(page);
        processRegInfo(page);
        processFinancing(page);
    }

    private void processFinancing(Page page) {
        List<Map<String, Object>> financingList = new ArrayList<Map<String, Object>>();
        if(!StringUtils.isEmpty(page.getHtml().$(".list-round-v2").get())){
            Elements items = page.getDocument().getElementsByClass("list-round-v2").get(0).getElementsByTag("tr");
            for (Element item : items) {
                Map<String, Object> financingItem = new HashMap<String, Object>();
                Elements subItems = item.getElementsByTag("td");
                if (subItems.size() != 4) {
                    logger.warn("Wrong financing item");
                } else {
                    financingItem.put("f_date", Util.parseDate(subItems.get(0).getElementsByClass("date").text(), "yyyy.MM.dd"));
                    financingItem.put("round", subItems.get(1).text());
                    financingItem.put("amount", subItems.get(2).text());
                    financingItem.put("amount_currency", Util.parseAmountToCurrency(financingItem.get("amount")));
                    financingItem.put("amount_int", Util.parseAmountToNumber(financingItem.get("amount")));


                    String[] iNameArray = subItems.get(3).toString().split("<br>");
                    for(String name : iNameArray){
                        String nameValue = Util.parseText(name);
                        if(!StringUtils.isEmpty(nameValue)){
                            Map<String, Object> financing = new HashMap<String, Object>(financingItem);
                            financing.put("i_name", nameValue);
                            financingList.add(financing);
                        }
                    }
                }
            }
        }
        page.putField("financing", financingList);
    }
    private boolean exist(String name){
        CompanyExample likeEx = new CompanyExample();
        likeEx.createCriteria().andNameLike("%" + name + "%");
        List<Company> likeRes = companyOp.selectByExampleWithBLOBs(likeEx);
        if(likeRes.size() != 0) {
            return true;
        }else {
            return false;
        }
    }
    private void processRegInfo(Page page) {
        Map<String, Object> regMap = new HashMap<String, Object>();
        List<String> des = page.getHtml().$(".des-more span", "text").all();
        regMap.put("company_name", des.get(0).replace("公司全称：", ""));
        regMap.put("reg_date", Util.parseDate(des.get(1).replace("成立时间：", ""), "yyyy.MM"));

        page.putField("reg_info", regMap);
    }

    private void processCompanyInfo(Page page) {
        Map<String, Object> companyMap = new HashMap<String, Object>();
        String financingStep =  page.getHtml().$(".line-title .title .t-small", "text").get().trim();
        companyMap.put("financing_step", financingStep.replace("(", "").replace(")", ""));
        String title = page.getHtml().$(".line-title .title", "allText").get();
        companyMap.put("name", title.replace(financingStep.trim(), ""));
        String website = page.getHtml().$(".weblink", "text").get().replace("http://","").replace("https://", "").trim();
        if(website.endsWith("/")){
            website = website.substring(0, website.length()-1);
        }
        companyMap.put("website", website);

        List<String> des = page.getHtml().$(".des-more span", "text").all();
        companyMap.put("company_name", des.get(0).replace("公司全称：", ""));
        companyMap.put("setup_time", Util.parseDate(des.get(1).replace("成立时间：", ""), "yyyy.MM"));
        companyMap.put("city", Util.parseText(page.getHtml().$(".loca a").all().get(0)));
        companyMap.put("introduction", page.getHtml().$(".des", "text").get());
        companyMap.put("company_img", page.getHtml().$(".pic img", "src").get());

        if(!StringUtils.isEmpty(page.getHtml().$(".list-prodcase").get())){
            List<String> founderList = page.getHtml().$(".list-prodcase li a .c").all();
            for (int i=0; i<founderList.size() && i<6; i++){
                companyMap.put("founder_" + (i+1), Util.parseText(founderList.get(i)));
            }
        }

        List<String> fields = page.getHtml().$(".scope a").all();
        for (int i=0; i<fields.size() && i<3; i++){
            String key = "field_" + (i+1);
            companyMap.put(key, Util.parseText(fields.get(i)));
        }

        page.putField("company", companyMap);
    }

    private void processListPage(Page page) {
        /*List<String> detailUrl = page.getHtml().$(".list-main-icnset").links().all();
        List<String> companyName = page.getHtml().$(".list-main-icnset li span").all();
        for (int i=0; i<detailUrl.size(); i++){
            String subUrl = detailUrl.get(i);

            if(subUrl.startsWith("http://www.itjuzi.com/company/")) {
                String name = Util.parseText(companyName.get(i));
                page.addTargetRequest(subUrl);
            }
        }*/
        Elements li = page.getDocument().getElementsByClass("list-main-icnset").get(1).getElementsByTag("li");
        for (Element l : li){
            String subUrl = l.getElementsByTag("a").get(0).attr("href");
            String name = l.getElementsByClass("title").text();
            if(!exist(name)) {
                page.addTargetRequest(subUrl);
            }else {
                logger.info("Skip " + name);
            }
        }
        String idStr = page.getUrl().get().replace(basicUrl, "").replace("/", "");
        int id = Integer.parseInt(idStr);
        id ++;
        logger.info("Processing " + id);
        if(id <= maxPageSize){
            page.addTargetRequest(basicUrl + id);
        }

    }

    public static void main(String[] args) {
        int startId =
                args.length == 0 ? 1 : Integer.parseInt(args[0]);
        new ITjuziProcessor().work(startId);
    }
}
