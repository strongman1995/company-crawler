package cn.thu.info.model;

import cn.thu.info.utils.BeforeSnapshot;

import java.io.Serializable;


public class SnapshotTask implements Serializable {
    public String url;
    public BeforeSnapshot beforeSnapshot;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String fileName;
    public Boolean finished;
    public SnapshotTask(String url, String fileName){
        this.url = url;
        this.fileName = fileName;
        this.finished = false;
    }
    public SnapshotTask(String url, String fileName, BeforeSnapshot before){
        this(url, fileName);
        this.beforeSnapshot = before;
    }

}
