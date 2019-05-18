package cn.thu.info.model;

import org.jsoup.select.Elements;

public class LinkItems implements Comparable<LinkItems>  {
    public Elements elements;
    public double rate;
    public int label;

    @Override
    public int compareTo(LinkItems o) {
        if(label == o.label)
            return o.rate < rate ? 1 : -1;
        else
            return o.label < label ? -1 : 1;
    }

    public void copy(LinkItems o){
        this.elements = o.elements;
        this.rate = o.rate;
        this.label = o.label;
    }

    public boolean isEmpty(){
        return elements == null;
    }
}
