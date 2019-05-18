package cn.thu.info.model;


public enum Amount {
    TEN_MI("千万", 10000000),
    MILLION("百万",1000000),
    TEN_TH("万", 10000),
    HUN_MI("亿", 100000000);
    public String str;
    public int unit;
    Amount(String str, int unit){
        this.str = str;
        this.unit = unit;
    }
}
