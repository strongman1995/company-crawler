package cn.thu.info.crawler.sitemap;

import cn.thu.info.Config;
import cn.thu.info.algorithm.ClassifierHelper;
import cn.thu.info.algorithm.NLPAlgorithm;
import cn.thu.info.crawler.PageDownloader;
import cn.thu.info.mapper.CompanyMapper;
import cn.thu.info.mapper.FinancingMapper;
import cn.thu.info.mapper.ReginfoMapper;
import cn.thu.info.model.*;
import cn.thu.info.pipeline.ReportPipeline;
import cn.thu.info.utils.IpUtil;
import cn.thu.info.utils.SessionHelper;
import cn.thu.info.utils.SnapshotUtil;
import com.hankcs.hanlp.HanLP;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SiteReportGenerator extends Thread{
    private static Logger logger = LoggerFactory.getLogger(SiteReportGenerator.class);

    private static int retryTime = 3;
    public SourceType sourceType = SourceType.QUERY;
    public Report report;
    private int curRetryTime;
    private Set<String> summarySet;
    private ReportCallBack callback;
    private ChromeDriver driver;
    private boolean snapshotSwitch = true;
    public SiteReportGenerator(String query){
        report = new Report(query);
        curRetryTime = 0;
        summarySet = new HashSet<String>();

    }
    public void setSnapshotSwitch(boolean snapshotSwitch){
        this.snapshotSwitch = snapshotSwitch;
    }
    public void setCallBack(ReportCallBack callback){
        this.callback = callback;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true){
//            String query = in.nextLine();
            String query = "维泽数据";
            SiteReportGenerator generator = new SiteReportGenerator(query);

            generator.start();
            while(generator.getCurProcess() < 1.0){
                logger.info("Process " + (generator.getCurProcess() * 100.0) + "%");
                try {
                    Thread.sleep( 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            logger.info(String.format("Query %s finished. With status %s.", query, generator.getStatus()));

            if(generator.getStatus() == Status.SUCC) {
                Desktop desktop = Desktop.getDesktop();
                File f = new File(generator.report.getReportFile());
                try {
                    desktop.open(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    /***
     * Crawl and generate report.
     */
    public void process(){
        report.status = Status.NOT_FINISH;
        while (curRetryTime <= retryTime && report.status != Status.SUCC) {
            curRetryTime ++;
            try {
                this.report.finishedTask = 0;

                //Query company information from database.
                Company company = generateCompanyInfo(report.query);
                report.company = company;
                if (report.company.getcId() == null){
                    report.status = Status.NOT_FOUND;
                    break;
                }
                if (report.company.getcId() != null && report.company.getcId() < Config.FAKE_ID_BASE) {
                    report.regInfo = generateRegInfo(report.company.getcId());
                    report.financingList = generateFinancingList(report.company.getcId());
                }

                //Generate target url.
                report.url = generateUrl(report.company.getWebsite());

                //Copy latex files.
                report.copyLatexDirectory();

                //Copy logo files.
                report.copyLogo();

                //Crawl main page and Generate candidate elements.
                Map<String, Elements> candidateElements = processMainPage(report.url);

                //Generate head and tail elements.
                getHeadAndTailBlocks(report.doc, report.url, candidateElements);

                //Crawl head pages.
                if (report.headElements != null) {
                    for (Element subPage : report.headElements) {
                        PageSummary summary = processSubPage(subPage, report.url);
                        if (summary != null) {
                            report.headPageSummary.add(summary);
                        }
                    }
                }

                //Crawl tail pages.
                if (report.tailElements != null) {
                    for (Element subPage : report.tailElements) {
                        PageSummary summary = processSubPage(subPage, report.url);
                        if (summary != null) {
                            report.tailPageSummary.add(summary);
                        }
                    }
                }

                //Generate security information.
                report.securityInfo = generateSecurityInfo(report);

                //Generate snapshot.
                report.totalTask = report.snapshotTaskList.size() + 1;
                report.status = Status.CONTENT_FINISHED;
                driver = SnapshotUtil.getNewInstance();
                if(snapshotSwitch) {
                    for (SnapshotTask task : report.snapshotTaskList) {

                        SnapshotUtil.snapshot(driver,task);
                        task.finished = true;
                        report.finishedTask++;
                    }
                }


                //Generate report.
                if(Config.PDF_REPORT_SWICH) {
                    ReportPipeline reportGen = new ReportPipeline();
                    reportGen.generate(report);
                }
                /*File file = new File(report.getReportFile());
                if(file.exists()){
                    report.status = Status.SUCC;
                }*/
                report.status = Status.SUCC;
                report.finishedTask ++;
                logger.info("Query " + this.report.query + " finished.");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        }
        if(curRetryTime > retryTime){
            report.status = Status.FAIL;
        }

        if(callback!=null){
            callback.onFinish(this);
        }

    }

    /***
     * Generate securityInfo.
     * @param report
     * @return
     */
    private SecurityInfo generateSecurityInfo(Report report) {
        SecurityInfo securityInfo = new SecurityInfo();
        try {
            if (report.url == null || report.doc == null) {
                return securityInfo;
            } else {
                securityInfo.img = report.getImagePath() + "securityInfo.png";
                report.snapshotTaskList.add(new SnapshotTask(report.url, securityInfo.img, new PageTailAction()));
                securityInfo.serverLocation = IpUtil.getIpLocation(report.url);
                Pattern pattern = Pattern.compile(".ICP.*号");
                Matcher matcher = pattern.matcher(report.doc.toString());
                if (matcher.find()) {
                    securityInfo.ipc = matcher.group(0);
                }
            }
        }catch (Exception e){
            logger.error("Get security information error!");
        }
        return securityInfo;
    }

    /***
     * Crawl subpage and generate summary.
     * @param subPage
     * @param baseUrl
     * @return
     */
    private PageSummary processSubPage(Element subPage, String baseUrl) {
        String href = subPage.attr("href");
        href = normalizeUrl(href, baseUrl);
        if(StringUtils.isEmpty(href)){
            return null;
        }
        try {
            URL url = new URL(href);
            String hostName = url.getHost();
            String urlItems[] = hostName.split("\\.");

            String domain = urlItems[urlItems.length - 2];
            /*if(!domain.equals(report.maxDomain)){
                return null;
            }*/

            PageSummary summary = new PageSummary();
            report.snapshotTaskList.add(new SnapshotTask(href, report.getImagePath() + url.hashCode() + ".png"));
            summary.img = url.hashCode() + ".png";

            Document doc = PageDownloader.download(href);
            ExtendElement extendDoc = new ExtendElement(doc);
            String content = extendDoc.text();
            //logger.info(url.toString());
            //logger.info(content);
            List<String> summaries = NLPAlgorithm.getTopSentenceList(content, 20);
            summary.summaries = deDuplicate(summaries);
            summary.keywords = HanLP.extractKeyword(content, 3);
            summary.url = href;
            summary.text = subPage.text();
            return summary;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String removeStoppingChar(String str){
        for (String c : Config.STOPPING_CHAR){
            str = str.replace(c,"");
        }
        return str.trim();
    }
    /***
     * De-duplicate summaries.
     * @param summaries
     * @return
     */
    private List<String> deDuplicate(List<String> summaries) {
        List<String> sentences = new ArrayList<String>();
        int cnt = 0;
        for (String sentence : summaries){
            if(!summarySet.contains(sentence)){
                sentences.add(removeStoppingChar(sentence));
                cnt ++;
                summarySet.add(sentence);
            }
            if(cnt == 3){
                break;
            }
        }
        return sentences;
    }

    /***
     * Get target url.
     * @param website
     * @return
     */
    private String generateUrl(String website) {
        String httpTag = "http";
        if(website == null){
            logger.error("Null value : website.");
        }
        String url;
        if(!website.startsWith(httpTag)){
            url = httpTag + "://" + website;
        }else {
            url = website;
        }
        return url;
    }

    /***
     * Get financing information list.
     * @param cid
     * @return
     */
    private List<Financing> generateFinancingList(Integer cid) {
        FinancingMapper operationFinancing = SessionHelper.getSession().getMapper(FinancingMapper.class);
        FinancingExample fEx = new FinancingExample();
        fEx.createCriteria().andCIdEqualTo(cid);
        List<Financing> fList = operationFinancing.selectByExample(fEx);
        return fList;
    }

    /***
     * Get company register information.
     * @param cid
     * @return
     */
    private Reginfo generateRegInfo(Integer cid) {
        ReginfoMapper operationReg = SessionHelper.getSession().getMapper(ReginfoMapper.class);
        ReginfoExample regEx = new ReginfoExample();
        regEx.createCriteria().andCIdEqualTo(cid);
        List<Reginfo> regList = operationReg.selectByExampleWithBLOBs(regEx);
        if(regList.size()>0){
            return regList.get(0);
        }else {
            return null;
        }
    }

    public List<Company> getCompanyInfoList(String query){
        List<Company> companyList = new ArrayList<Company>();
        Set<Integer> cIdSet = new HashSet<Integer>();
        CompanyMapper operation = SessionHelper.getSession().getMapper(CompanyMapper.class);
        //Strict equal.
        CompanyExample equalEx = new CompanyExample();
        equalEx.createCriteria().andNameEqualTo(query);
        List<Company> resList = operation.selectByExampleWithBLOBs(equalEx);
        companyList.addAll(resList);
        for(Company c : companyList){
            cIdSet.add(c.getcId());
        }

        //Like
        CompanyExample likeEx = new CompanyExample();
        likeEx.createCriteria().andNameLike("%" + query + "%");
        likeEx.setOrderByClause("c_id limit 0,30");
        List<Company> likeRes = operation.selectByExampleWithBLOBs(likeEx);
        for(Company c : likeRes){
            if(!cIdSet.contains(c.getcId())){
                companyList.add(c);
                cIdSet.add(c.getcId());
            }
        }

        //CompanyName
        CompanyExample companyNameEx = new CompanyExample();
        companyNameEx.createCriteria().andCompanyNameLike("%" + query + "%");
        companyNameEx.setOrderByClause("c_id limit 0,10");
        List<Company> companyNameRes = operation.selectByExampleWithBLOBs(companyNameEx);
        for(Company c : companyNameRes){
            if(!cIdSet.contains(c.getcId())){
                companyList.add(c);
                cIdSet.add(c.getcId());
            }
        }
        return companyList;
    }
    /***
     * Generate company information by user query.
     * @param query
     * @return
     */
    private Company generateCompanyInfo(String query) {
        CompanyMapper operation = SessionHelper.getSession().getMapper(CompanyMapper.class);
        try {
            if (sourceType == SourceType.QUERY) {

                //Strict equal.
                CompanyExample equalEx = new CompanyExample();
                equalEx.createCriteria().andNameEqualTo(query);
                List<Company> resList = operation.selectByExampleWithBLOBs(equalEx);
                if (resList.size() != 0) {
                    return resList.get(0);
                }

                //Like
                CompanyExample likeEx = new CompanyExample();
                likeEx.createCriteria().andNameLike("%" + query + "%");
                List<Company> likeRes = operation.selectByExampleWithBLOBs(likeEx);
                if (likeRes.size() != 0) {
                    return likeRes.get(0);
                }
            } else if (sourceType == SourceType.ID) {
                return operation.selectByPrimaryKey(Integer.parseInt(query));
            }else if(sourceType == SourceType.URL){
                Company fakeCompany = new Company();
                fakeCompany.setcId(new Random().nextInt(10000) + Config.FAKE_ID_BASE);
                fakeCompany.setWebsite(query);
                return fakeCompany;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.warn("No company named " + query + " found, please use other strategy.");
        return new Company();
    }

    /***
     * Generate main page and head/tail elements.
     * @param url
     */
    private Map<String, Elements> processMainPage(String url) {
        Map<String, Elements> candidateElements = new HashMap<String, Elements>();
        try {

            Document doc =  PageDownloader.download(url);
            report.doc = doc;
            if(report.company.getName()==null){
                String title = doc.getElementsByTag("title").get(0).text();
                report.company.setName(title);
                report.company.setCompanyName(title);
            }
            Elements divElements = doc.getElementsByTag("div");
            processBlocks(divElements, candidateElements);
            Elements ulElements = doc.getElementsByTag("ul");
            processBlocks(ulElements, candidateElements);
            Elements navElements = doc.getElementsByTag("nav");
            processBlocks(navElements, candidateElements);
            Elements dlElements = doc.getElementsByTag("dl");
            processBlocks(dlElements, candidateElements);
            report.snapshotTaskList.add(new SnapshotTask(url, report.getImagePath() + "abstract.png"));

            return candidateElements;

        }catch (Exception e){
            e.printStackTrace();

        }
        return candidateElements;
    }

    /***
     * Coarse selection.
     * @param blocks
     * @param candidateElements
     */
    public void processBlocks(Elements blocks, Map<String, Elements> candidateElements) {
        for (Element ele : blocks) {
            Elements as = ele.getElementsByTag("a");
            if (as.size() >= 3 && as.size() <= 10) {
                boolean allow = true;
                Set<String> textSet = new HashSet<String>();
                for (Element a : as) {
                    String str = a.text();
                    if (textSet.contains(str)) {
                        allow = false;
                        break;
                    }
                    textSet.add(str);
                    if (str.length() > 12 || str.length() == 0) {
                        allow = false;
                        break;
                    }
                }
                if (allow && !candidateElements.containsKey(as.toString())) {
                    candidateElements.put(as.toString(), as);
                }
            }
        }
    }

    /***
     * Fine selection and classification.
     * @param doc
     * @param baseUrl
     * @param candidateElements
     */
    private void getHeadAndTailBlocks(Document doc, String baseUrl, Map<String, Elements> candidateElements){
        LinkItems headBlocks = new LinkItems();
        LinkItems tailBlocks = new LinkItems();
        Element body = doc.getElementsByTag("body").first();
        String bodyStr = getBodyString(body);
        List<LinkItems> links = new ArrayList<LinkItems>();

        for (Map.Entry<String, Elements> pair : candidateElements.entrySet()){
            List<Double> featureList = new ArrayList<Double>();
            // System.out.println(body.toString());
            Elements linkTuple = pair.getValue();
            // System.out.println(linkTuple.toString());
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
                href = normalizeUrl(href, baseUrl);
                if(StringUtils.isEmpty(href)){
                    continue;
                }
                try {
                    URL url = new URL(href);
                    String hostName = url.getHost();
                    String urlItems[] = hostName.split("\\.");

                    String domain = "domain";
                    if(urlItems.length >= 2) {
                        domain = urlItems[urlItems.length - 2];
                    }
                    if(domainCnt.containsKey(domain)){
                        domainCnt.put(domain, domainCnt.get(domain) + 1);
                    }else {
                        domainCnt.put(domain, 1);
                    }
                    if(domainCnt.get(domain) > maxSameNum){
                        maxSameNum = domainCnt.get(domain);
                        //report.maxDomain = domain;
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
            boolean finished = false;
            for (LinkItems item : targetLinks){
                if(item.elements.text().contains("首页")){
                    headBlocks.copy(item);
                    finished = true;
                }
            }
            if (!finished) {
                if (targetLinks.get(0).rate <= 0.4) {
                    if (targetLinks.get(1).rate - targetLinks.get(0).rate <= 0.1) {
                        if (targetLinks.get(1).elements.size() >
                                targetLinks.get(0).elements.size()) {
                            headBlocks.copy(targetLinks.get(1));
                        } else {
                            headBlocks.copy(targetLinks.get(0));
                        }
                    } else {
                        headBlocks.copy(targetLinks.get(0));
                    }
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

        report.headElements = headBlocks.elements;
        report.tailElements = tailBlocks.elements;
    }

    private  String normalizeUrl(String href, String baseUrl){
        if(href.startsWith("//")){
            return "http:" + href;
        }
        if(href.startsWith("/") || href.startsWith("#") || (!href.startsWith("http:") && !href.startsWith("https:"))){
            if(!href.startsWith("/")){
                href = "/" + href;
            }
            href = baseUrl + href;

        }
        return href;
    }
    /***
     * Get body string(except javascript and css code.)
     * @param body
     * @return
     */
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

    /***
     * Get process rate.
     * @return
     */
    public double getCurProcess(){
        if(report.status != Status.NOT_FINISH && report.status != Status.CONTENT_FINISHED){
            return 1.0;
        }
        if(report.finishedTask == 0){
            return  0.0;
        }else{
            return 1.0 * report.finishedTask / report.totalTask;
        }
    }

    /***
     * Get final result.
     * @return
     */
    public Status getStatus(){
        return report.status;
    }
    /***
     * Thread run.
     */
    @Override
    public void run() {
        this.process();
    }

}
