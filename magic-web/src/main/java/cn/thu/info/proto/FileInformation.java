package cn.thu.info.proto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;


public class FileInformation implements Serializable {
    private String name = null ;
    private byte[] content = null ;
    public String getName() {
        return name ;
    }
    public byte[] getContent() {
        return content;
    }
    public void setInformation(String name, byte[] content) {
        this.name = name ;
        this.content = content ;
    }

    public void saveToFile(String s) {
        File file = new File(s);
        try {
            FileOutputStream write  = new FileOutputStream(file);
            write.write(content);
            write.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
