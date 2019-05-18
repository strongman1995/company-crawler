package cn.thu.info.crawler.company;

import cn.thu.info.pipeline.CompanyMysqlPipeline;
import cn.thu.info.pipeline.CompanyPipleline;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CyzonProcessor extends CompanyProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public static String basicUrl = "http://www.cyzone.cn/vcompany/list-0-0-";
    public static int maxPageSize = 1969;
    @Override
    public String getSource() {
        return "";
    }

    @Override
    public void work(int startID) {
        Spider.create(new CyzonProcessor())
                .addUrl(basicUrl + startID + "/")
                //.addUrl("http://www.cyzone.cn/r/20150704/17459.html")
                .thread(5)
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
        if(!StringUtils.isEmpty(page.getHtml().$(".live").get())){
            Elements items = page.getDocument().getElementsByClass("live").get(0).getElementsByTag("tr");
            for (Element item : items) {
                if (item.getElementsByClass("live-title").size() > 0)
                    continue;
                else {
                    Map<String, Object> financingItem = new HashMap<String, Object>();
                    Elements subItems = item.getElementsByTag("td");
                    if (subItems.size() != 4) {
                        logger.warn("Wrong financing item");
                    } else {
                        financingItem.put("round", subItems.get(0).text());
                        financingItem.put("amount", subItems.get(1).text());
                        financingItem.put("amount_currency", Util.parseAmountToCurrency(financingItem.get("amount")));
                        financingItem.put("amount_int", Util.parseAmountToNumber(financingItem.get("amount")));
                        financingItem.put("f_date", Util.parseDate(subItems.get(3).text()));

                        String[] iNameArray = subItems.get(2).toString().split("<br>");
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
        }
        page.putField("financing", financingList);
    }

    private void processRegInfo(Page page) {
        Map<String, Object> regMap = new HashMap<String, Object>();

        regMap.put("company_name", page.getHtml().$(".ti-left .name h1", "text").get());
        Elements regInfoElements = page.getDocument().getElementById("qcc").getElementsByTag("p");
        Map<String, String> regItemMap = new HashMap<String, String>();
        for (Element regInfo : regInfoElements){
            String key = regInfo.getElementsByClass("name").get(0).text();
            String value = regInfo.text().replace(key, "");
            regItemMap.put(key, value);
        }
        regMap.put("reg_id", regItemMap.get("注册号:"));
        regMap.put("leal_person", regItemMap.get("法定代表:"));
        regMap.put("reg_date", Util.parseDate(regItemMap.get("成立日期:")));
        regMap.put("reg_capital", regItemMap.get("注册资本:"));
        regMap.put("reg_capital_int", Util.parseAmountToNumber(regMap.get(("reg_capital"))));
        regMap.put("reg_capital_currency", Util.parseAmountToCurrency(regMap.get(("reg_capital"))));
        regMap.put("reg_location", regItemMap.get("住所:"));

        page.putField("reg_info", regMap);
    }

    private void processCompanyInfo(Page page) {
        Map<String, Object> companyMap = new HashMap<String, Object>();

        companyMap.put("name", page.getHtml().$(".ti-left .name h1", "text").get());
        companyMap.put("website", page.getHtml().$(".ti-left .add", "allText").regex("(?<=：).*").get());
        companyMap.put("company_name", page.getHtml().$(".ti-left .time", "text").regex("(?<=：).*").get());
        Elements items = page.getDocument().getElementsByClass("info-tag").get(0).getElementsByTag("li");
        Map<String, String> liMap = new HashMap<String, String>();
        for (Element item : items){
            String classTag = item.getElementsByTag("i").get(0).attr("class");
            String strValue = item.getElementsByTag("span").get(0).text();
            liMap.put(classTag, strValue);
        }
        String[] fields = liMap.get("i6") == null ? new String[0] : liMap.get("i6").split(",");
        for (int i=0; i<fields.length && i<3; i++){
            String key = "field_" + (i+1);
            companyMap.put(key, fields[i]);
        }

        companyMap.put("setup_time", Util.parseDate(liMap.get("i1")));
        companyMap.put("city", liMap.get("i2"));
        companyMap.put("financing_step", liMap.get("i3"));
        companyMap.put("introduction", page.getHtml().$(".info-box p", "allText").get());
        companyMap.put("company_img", page.getHtml().$(".tl-img-bar img", "src").get());

        if(!StringUtils.isEmpty(page.getHtml().$(".team").get())){
            List<String> founderList = page.getHtml().$(".team .team-info .name").all();
            for (int i=0; i<founderList.size() && i<6; i++){
                companyMap.put("founder_" + (i+1), Util.parseText(founderList.get(i)));
            }
        }
        page.putField("company", companyMap);
    }

    private void processListPage(Page page) {
        List<String> detailUrl = page.getHtml().$(".list-table .table-plate .table-company").links().all();
        for (String subUrl : detailUrl){
            page.addTargetRequest(subUrl);
        }
        String idStr = page.getUrl().get().replace(basicUrl, "").replace("/", "");
        int id = Integer.parseInt(idStr);
        id ++;
        if(id <= maxPageSize){
            page.addTargetRequest(basicUrl + id + "/");
        }

    }

    public static void main(String[] args) {
        int startId =
                args.length == 0 ? 1 : Integer.parseInt(args[0]);
        new CyzonProcessor().work(startId);
    }
}
