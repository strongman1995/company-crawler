package cn.thu.info;


public class Config {
    public static final int SNAPSHOT_MAX_TIMEOUT = 90;
    public static final int SNAPSHOT_TIMEOUT = 10;
    public static final String IP = "localhost:1099";
    public static final String RESULT_PATH = "result/";
//    public static String CHROME_DRIVER = "C:\\chromedriver.exe";/Users/strongman/Downloads/chromedriver.exe
    public static String CHROME_DRIVER = "/Users/strongman/Downloads/chromedriver.exe";
    public static boolean SNAPSHOT_SWITCH = true;
    public static String[] STOPPING_CHAR = {"?", "？"};
    public static final String IP_API = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
    public static final int FAKE_ID_BASE = 1000000;
    public static boolean PDF_REPORT_SWICH = false;
}
