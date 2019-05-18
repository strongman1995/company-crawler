package cn.thu.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PageSummary implements Serializable {
    public List<String> summaries = new ArrayList<String>();
    public List<String> keywords = new ArrayList<String>();
    public String img;
    public String url;
    public String text;

    public List<String> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<String> summaries) {
        this.summaries = summaries;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
