package cn.thu.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SearchResult implements Serializable {
    public SearchResult(){
        list = new ArrayList<Company>();
    }
    public List<Company> getList() {
        return list;
    }

    public void setList(List<Company> list) {
        this.list = list;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    private List<Company> list;
    private int totalSize;

}
