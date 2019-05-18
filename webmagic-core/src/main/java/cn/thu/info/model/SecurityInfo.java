package cn.thu.info.model;

import java.io.Serializable;


public class SecurityInfo implements Serializable{
    public String serverLocation;
    public String ipc;

    public String img;

    public SecurityInfo(){
        serverLocation = "未知";
        ipc = "未知";
    }
    public String getServerLocation() {
        return serverLocation;
    }

    public void setServerLocation(String serverLocation) {
        this.serverLocation = serverLocation;
    }

    public String getIpc() {
        return ipc;
    }

    public void setIpc(String ipc) {
        this.ipc = ipc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
