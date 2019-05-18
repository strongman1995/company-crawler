package cn.thu.info.utils;

import cn.thu.info.Config;
import cn.thu.info.model.SnapshotTask;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangbo on 2016/7/10.
 */
public class SnapshotUtil extends Thread {
    private static Logger logger = LoggerFactory.getLogger(SnapshotUtil.class);
    private String url;
    private String filename;
    public WebDriver driver;
    public BeforeSnapshot beforeSnapshot;
    public boolean finished;

    public SnapshotUtil(ChromeDriver driver, String url, String filename) {
        this.url = url;
        this.filename = filename;
        this.finished = false;
        this.driver = driver;
    }

    public SnapshotUtil(ChromeDriver driver, SnapshotTask task) {
        this.url = task.url;
        this.filename = task.fileName;
        this.finished = false;
        this.driver = driver;
        this.beforeSnapshot = task.beforeSnapshot;
    }

    public static ChromeDriver getNewInstance() {
        ChromeDriver driver = null;
        try {
            System.setProperty("webdriver.chrome.driver", Config.CHROME_DRIVER);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Config.SNAPSHOT_TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("Init webdriver error.");
        }
        return driver;
    }

    @Override
    public void run() {
        super.run();

        try {
            driver.get(url);
        } catch (Exception e) {
            logger.error("get url " + url + " exception.");
            logger.error("error:  "+ e.toString());
        } finally {
            try {
                Thread.sleep(1000);
                if (beforeSnapshot != null) {
                    beforeSnapshot.doBefore(driver);
                    Thread.sleep(1000);
                }
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(filename));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        finished = true;
    }

    public static void snapshot(ChromeDriver driver, String url, String filename) {
        try {
            if (Config.SNAPSHOT_SWITCH) {
                SnapshotUtil snapshot = new SnapshotUtil(driver, url, filename);
                snapshot.start();
                int sleepTime = 0;
                while (snapshot.finished != true) {
                    sleepTime++;
                    Thread.sleep(100);
                    if (sleepTime > Config.SNAPSHOT_MAX_TIMEOUT * 10) {
                        logger.error(String.format("Snapshot %s timeout.", url));
                        //snapshot.driver.close();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(String.format("Snapshot %s error.", url));
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Config.CHROME_DRIVER);
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
        driver.manage().window().maximize();

        //driver.close();

        driver.get("http://www.qq.com");

        //driver.close();
        driver.quit();
    }

    public static void snapshot(ChromeDriver driver, SnapshotTask task) {
        try {
            if (Config.SNAPSHOT_SWITCH) {
                SnapshotUtil snapshot = new SnapshotUtil(driver, task);
                snapshot.start();
                int sleepTime = 0;
                while (snapshot.finished != true) {
                    sleepTime++;
                    Thread.sleep(100);
                    if (sleepTime > Config.SNAPSHOT_MAX_TIMEOUT * 10) {
                        logger.error(String.format("Snapshot %s timeout.", task.url));
                        //snapshot.driver.close();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(String.format("Snapshot %s error.", task.url));
            e.printStackTrace();
        }
    }
}