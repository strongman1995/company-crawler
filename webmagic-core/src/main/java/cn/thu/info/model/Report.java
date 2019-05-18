package cn.thu.info.model;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Report implements Serializable {
    public String query;
    public String sessionId;
    public Company company;

    public Reginfo regInfo;
    public List<Financing> financingList;
    transient public List<SnapshotTask> snapshotTaskList;
    transient public Elements headElements;
    transient public Elements tailElements;
    public List<PageSummary> headPageSummary = new ArrayList<PageSummary>();
    public List<PageSummary> tailPageSummary = new ArrayList<PageSummary>();
    public SecurityInfo securityInfo = new SecurityInfo();
    transient public String maxDomain;
    transient public String homePath;
    public String url;
    transient public Document doc;
    public int totalTask;
    public int finishedTask;
    public Status status;

    public Report() {

    }

    public Report(String query) {
        this.query = query;
        this.snapshotTaskList = new ArrayList<SnapshotTask>();
        this.status = Status.NOT_FINISH;
    }


    public SecurityInfo getSecurityInfo() {
        return securityInfo;
    }

    public void setSecurityInfo(SecurityInfo securityInfo) {
        this.securityInfo = securityInfo;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Reginfo getRegInfo() {
        return regInfo;
    }

    public void setRegInfo(Reginfo regInfo) {
        this.regInfo = regInfo;
    }

    public List<Financing> getFinancingList() {
        return financingList;
    }

    public void setFinancingList(List<Financing> financingList) {
        this.financingList = financingList;
    }

    public Elements getHeadElements() {
        return headElements;
    }

    public void setHeadElements(Elements headElements) {
        this.headElements = headElements;
    }

    public Elements getTailElements() {
        return tailElements;
    }

    public void setTailElements(Elements tailElements) {
        this.tailElements = tailElements;
    }

    public List<PageSummary> getHeadPageSummary() {
        return headPageSummary;
    }

    public void setHeadPageSummary(List<PageSummary> headPageSummary) {
        this.headPageSummary = headPageSummary;
    }

    public List<PageSummary> getTailPageSummary() {
        return tailPageSummary;
    }

    public void setTailPageSummary(List<PageSummary> tailPageSummary) {
        this.tailPageSummary = tailPageSummary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(int totalTask) {
        this.totalTask = totalTask;
    }

    public int getFinishedTask() {
        return finishedTask;
    }

    public void setFinishedTask(int finishedTask) {
        this.finishedTask = finishedTask;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getHomePath() {
        if (homePath == null) {
            homePath = "latex/" + company.getcId();
        }
        return homePath;
    }

    public void copyLatexDirectory() {
        File file = new File(getHomePath());
        if (!file.exists()) {
            try {
                FileUtils.copyDirectory(new File("latex/template"), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyLogo() {
        try {
            FileUtils.copyFile(new File("img/" + company.getCompanyImg()), new File(getImagePath() + "logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTemplateFile() {
        return getHomePath() + "/report.temp";
    }

    public String getSubFile() {
        return getHomePath() + "/subPage.temp";
    }

    public String getImagePath() {
        return getHomePath() + "/figures/";
    }

    public String getLatexFile() {
        return getHomePath() + "/report.tex";
    }

    public String getCmdTEmpFile() {
        return getHomePath() + "/start.temp";
    }

    public String realPath() {
        return new File(getHomePath()).getAbsolutePath();
    }

    public String cmdFile() {
        return realPath() + "/start.bat";
    }

    public String getReportFile() {
        return getHomePath() + "/report.pdf";
    }
}
