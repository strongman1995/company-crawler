package cn.thu.info.crawler.sitemap;

import cn.thu.info.utils.BeforeSnapshot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.Serializable;

public class PageTailAction implements BeforeSnapshot,Serializable {
    @Override
    public void doBefore(WebDriver driver) {
        try {
            String setscroll = "javascript:document.getElementsByTagName('BODY')[0].scrollTop" +
                    "=document.getElementsByTagName('BODY')[0].scrollHeight;";
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript(setscroll);
        } catch (Exception e) {
            System.out.println("Fail to set the scroll.");
        }
    }
}
