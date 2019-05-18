package cn.thu.info.crawler.sitemap;

import cn.thu.info.algorithm.ClassifierHelper;
import cn.thu.info.model.*;
import cn.thu.info.pipeline.ReportPipeline;
import cn.thu.info.utils.SnapshotUtil;
import com.hankcs.hanlp.HanLP;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;


public class SitemapProcessor implements PageProcessor {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public Report report;
    public String userAgent = "\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2)\" +\n" +
            "\" AppleWebKit/537.31 (KHTML, like Gecko) Chrome\" +\n" +
            "\"/26.0.1410.65 Safari/537.31\"";
    private Site site = Site
            .me()
            .setSleepTime(10)
            .setRetryTimes(3)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2)" +
                            " AppleWebKit/537.31 (KHTML, like Gecko) Chrome" +
                            "/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {

    }

    private void getHeadAndTailBlocks(Document doc, String baseUrl, Map<String, Elements> candidateElements,
                                     LinkItems headBlocks, LinkItems tailBlocks){
        Element body = doc.getElementsByTag("body").first();
        String bodyStr = getBodyString(body);
        List<LinkItems> links = new ArrayList<LinkItems>();

        for (Map.Entry<String, Elements> pair : candidateElements.entrySet()){
            List<Double> featureList = new ArrayList<Double>();
            // System.out.println(body.toString());
            Elements linkTuple = pair.getValue();
            //System.out.println(linkTuple.toString());
            LinkItems linkItem = new LinkItems();
            linkItem.elements = pair.getValue();

            int idxF =bodyStr.indexOf(linkTuple.first().toString());
            int wholeLength = linkTuple.first().toString().length();
            while (idxF == -1){
                idxF = bodyStr.indexOf(linkTuple.first().toString().substring(0, wholeLength -2));
                wholeLength-=2;
            }
            int idxL = bodyStr.indexOf(linkTuple.last().toString());

            int wholeLengthL = linkTuple.last().toString().length();
            while (idxL == -1) {
                idxL = bodyStr.indexOf(linkTuple.last().toString().substring(wholeLengthL - 2));
                wholeLengthL -= 2;
            }
            idxL += linkTuple.last().toString().length();
            //Pos.
            double rateF = 1.0 *  idxF / bodyStr.length();
            linkItem.rate = rateF;
            double rateL = 1.0 *  idxL / bodyStr.length();
            double relRate = Math.min(rateF, 1.0 - rateL);
            featureList.add(relRate);

            //Link length.
            int length = pair.getValue().size();
            featureList.add(length * 1.0);

            //Avg item length.
            int totalWords = 0;
            for (Element link : linkTuple){
                totalWords += link.text().length();
            }
            double avgItemLen = 1.0 * totalWords / linkTuple.size();
            featureList.add(avgItemLen);

            //Domain rate
            Map<String, Integer> domainCnt = new HashMap<String, Integer>();
            int maxSameNum = 0;
            for (Element link : linkTuple){
                String href = link.attr("href");
                if(href.startsWith("/") || href.startsWith("#")){
                    href = baseUrl + href;
                }
                if(StringUtils.isEmpty(href)){
                    continue;
                }
                try {
                    URL url = new URL(href);
                    String hostName = url.getHost();
                    String urlItems[] = hostName.split("\\.");

                    String domain = urlItems[urlItems.length - 2];
                    if(domainCnt.containsKey(domain)){
                        domainCnt.put(domain, domainCnt.get(domain) + 1);
                    }else {
                        domainCnt.put(domain, 1);
                    }
                    if(domainCnt.get(domain) > maxSameNum){
                        maxSameNum = domainCnt.get(domain);
                        report.maxDomain = domain;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            double domainRate = maxSameNum * 1.0 / linkTuple.size();
            featureList.add(domainRate);

            String[] keywords = {"首页", "我们", "关于"};
            boolean hasKeywords = false;
            for (Element link : linkTuple){
                String text = link.text();
                for (String word : keywords){
                    if(text.contains(word)){
                        hasKeywords = true;
                    }
                }
            }
            featureList.add(hasKeywords ? 1.0 : 0.0);

            boolean hasImgTag = false;
            for (Element link : linkTuple){
                Elements img = link.getElementsByTag("img");
                if(img.size()>0){
                    hasImgTag = true;
                }
            }
            featureList.add(hasImgTag ? 1.0 : 0.0);

            int result = ClassifierHelper.classify(featureList);
            linkItem.label = result;

            links.add(linkItem);
        }

        Collections.sort(links);
        List<LinkItems> targetLinks = new ArrayList<LinkItems>();
        List<LinkItems> noneLinks = new ArrayList<LinkItems>();
        for (LinkItems items : links){
            if(items.label==1){
                targetLinks.add(items);
            }else {
                noneLinks.add(items);
            }
        }
        if(targetLinks.size() >=2) {
            if (targetLinks.get(0).rate <= 0.4) {
                double d = targetLinks.get(1).rate - targetLinks.get(0).rate;

                if(targetLinks.get(1).rate - targetLinks.get(0).rate <= 0.1 && targetLinks.get(1).elements.size() >
                        targetLinks.get(0).elements.size()) {
                    headBlocks.copy(targetLinks.get(1));
                } else {
                    headBlocks.copy(targetLinks.get(0));
                }
            }
            if (targetLinks.get(targetLinks.size() - 1).rate >= 0.6) {
                tailBlocks.copy(targetLinks.get(targetLinks.size() - 1));
            }
        }else if(targetLinks.size() == 1){
            if(targetLinks.get(0).rate<=0.4){
                headBlocks.copy(targetLinks.get(0));
            }else if(targetLinks.get(0).rate>=0.6){
                tailBlocks.copy(targetLinks.get(0));
            }
        }else {
            logger.warn("No target blocks!");
            if(noneLinks.size()>0)
                headBlocks.copy(noneLinks.get(0));
        }
    }

    private String getBodyString(Element body) {
        String bodyString = body.toString();
        Elements scripts = body.getElementsByTag("script");
        for (Element script : scripts){
            bodyString = bodyString.replace(script.toString(), "");
        }
        Elements styles = body.getElementsByTag("style");
        for (Element style : styles){
            bodyString = bodyString.replace(style.toString(), "");
        }
        return bodyString;
    }

    public void processBlocks(Elements blocks, Map<String, Elements> candidateElements){
        for (Element ele : blocks){
            //System.out.println(ele.toString());
            Elements as = ele.getElementsByTag("a");
            if(as.size()>=3 && as.size()<=8){
                boolean allow = true;
                Set<String> textSet = new HashSet<String>();
                for (Element a : as){
                    String str = a.text();
                    if(textSet.contains(str)){
                        allow = false;
                        break;
                    }
                    textSet.add(str);
                    if(str.length() > 12 || str.length() == 0) {
                        allow = false;
                        break;
                    }
                }

                if(allow && !candidateElements.containsKey(as.toString())){
                    candidateElements.put(as.toString(), as);
                }
            }
        }
    }

    public void work() {
        Spider.create(new SitemapProcessor())
                .addUrl("http://www.zhenrongbao.com/")
                .addUrl("http://www.diantudaikuan.com/")
                .addUrl("http://www.cashbus.com/")
                .addUrl("https://www.licaimofang.com/")
                .addUrl("https://www.99lemon.com/")
                .addUrl("http://www.rong360.com/")
                .addUrl("http://www.ncfgroup.com/")
                .addUrl("http://www.qufenqi.com/")
                .thread(1)
                .run();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //new SitemapProcessor().work();
        SitemapProcessor processor = new SitemapProcessor();
        Report report = new Report();
        report.company = new Company();
        report.company.setWebsite("www.zhenrongbao.com/");
        report.company.setCompanyName("真融宝");
        report.company.setcId(110);
        processor.process(report);
    }

    public void process(Report report) {
        this.report = report;
        processMainPage(report.company.getWebsite());
    }

    private void processMainPage(String website) {
        String url = "http://" + website;
        try {
            Document doc = Jsoup.connect(url).userAgent(userAgent).get();
            Map<String, Elements> candidateElements = new HashMap<String, Elements>();
            Elements divElements = doc.getElementsByTag("div");
            processBlocks(divElements, candidateElements);
            Elements ulElements = doc.getElementsByTag("ul");
            processBlocks(ulElements, candidateElements);
            Elements navElements = doc.getElementsByTag("nav");
            processBlocks(navElements, candidateElements);
            //SnapshotUtil.snapshot(url, report.getImagePath() + "abstract.png");

            LinkItems headBlocks = new LinkItems();
            LinkItems tailBlocks = new LinkItems();
            getHeadAndTailBlocks(doc, url, candidateElements, headBlocks, tailBlocks);
            report.headElements = headBlocks.elements;
            report.tailElements = tailBlocks.elements;
            report.copyLogo();


            if (!headBlocks.isEmpty()) {
                for(Element subPage : headBlocks.elements){
                    PageSummary summary = processSubPage(subPage, url);
                    if(summary!=null){
                        report.headPageSummary.add(summary);
                    }
                }
            }
            if (!tailBlocks.isEmpty()) {
                for(Element subPage : tailBlocks.elements){
                    PageSummary summary = processSubPage(subPage, url);
                    if(summary!=null){
                        report.tailPageSummary.add(summary);
                    }
                }
            }



            ReportPipeline reportGen = new ReportPipeline();
            reportGen.generate(report);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private PageSummary processSubPage(Element subPage, String baseUrl) {
        String href = subPage.attr("href");
        if(href.startsWith("/") || href.startsWith("#")){
            href = baseUrl + href;
        }
        if(StringUtils.isEmpty(href)){
            return null;
        }
        try {
            URL url = new URL(href);
            String hostName = url.getHost();
            String urlItems[] = hostName.split("\\.");

            String domain = urlItems[urlItems.length - 2];
            if(!domain.equals(report.maxDomain)){
                return null;
            }

            PageSummary summary = new PageSummary();
            //SnapshotUtil.snapshot(href, report.getImagePath() + url.hashCode() + ".png");
            summary.img = url.hashCode() + ".png";

            Document doc = Jsoup.connect(href).userAgent(userAgent).get();
            String content = doc.text();
            summary.summaries = HanLP.extractSummary(content, 3);
            summary.keywords = HanLP.extractKeyword(content, 3);
            summary.url = href;
            summary.text = subPage.text();
            return summary;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
