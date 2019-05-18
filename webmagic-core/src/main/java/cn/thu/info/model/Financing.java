package cn.thu.info.model;

import java.io.Serializable;
import java.util.Date;

public class Financing implements Serializable {
    private Integer fId;

    private Integer cId;

    private String round;

    private String amount;

    private Integer amountInt;

    private String amountCurrency;

    private Integer iId;

    private String iName;

    private String iUcode;

    private Date fDate;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round == null ? null : round.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public Integer getAmountInt() {
        return amountInt;
    }

    public void setAmountInt(Integer amountInt) {
        this.amountInt = amountInt;
    }

    public String getAmountCurrency() {
        return amountCurrency;
    }

    public void setAmountCurrency(String amountCurrency) {
        this.amountCurrency = amountCurrency == null ? null : amountCurrency.trim();
    }

    public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName == null ? null : iName.trim();
    }

    public String getiUcode() {
        return iUcode;
    }

    public void setiUcode(String iUcode) {
        this.iUcode = iUcode == null ? null : iUcode.trim();
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }
}