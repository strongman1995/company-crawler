package cn.thu.info.service;

import cn.thu.info.proto.Config;
import cn.thu.info.proto.FileInformation;
import cn.thu.info.proto.ISitemapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class SitemapServiceHelper {
    private static Logger logger = LoggerFactory.getLogger(SitemapServiceHelper.class);
    public static ISitemapService createInstance() {
        try{
            ISitemapService service =(ISitemapService) Naming.lookup("rmi://127.0.0.1:6600/SitemapService");
            return  service;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ISitemapService service = SitemapServiceHelper.createInstance();
        String query = "人人贷";
        try {
            String id = service.addTask(query);
            while (!service.isFinished(id)){
                logger.info("Process " + service.getProcess(id));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int res = service.getStatus(id);
            if(res !=0 ){
                logger.info("Query failed!");
            }else {
                FileInformation file = service.download(id);
                file.saveToFile(Config.RESULT_PATH + query + ".pdf");

                Desktop desktop = Desktop.getDesktop();
                File f = new File(Config.RESULT_PATH + query + ".pdf");
                try {
                    desktop.open(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
