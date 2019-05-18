package cn.thu.info.model;


public enum Currency {
    RMB("人民币", "人民币"),
    USD("美元", "美元"),
    RMBY("人民币", "元");

    public String type;
    public String value;
    Currency(String type, String value){
        this.value = value;
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }
}
