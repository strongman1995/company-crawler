package cn.thu.info.model;

import java.io.Serializable;
import java.util.Date;

public class Reginfo implements Serializable {
    private Integer rId;

    private Integer cId;

    private String regId;

    private String lealPerson;

    private Date regDate;

    private String regCapital;

    private Integer regCapitalInt;

    private String regCapitalCurrency;

    private String companyName;

    private String regLocation;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId == null ? null : regId.trim();
    }

    public String getLealPerson() {
        return lealPerson;
    }

    public void setLealPerson(String lealPerson) {
        this.lealPerson = lealPerson == null ? null : lealPerson.trim();
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital == null ? null : regCapital.trim();
    }

    public Integer getRegCapitalInt() {
        return regCapitalInt;
    }

    public void setRegCapitalInt(Integer regCapitalInt) {
        this.regCapitalInt = regCapitalInt;
    }

    public String getRegCapitalCurrency() {
        return regCapitalCurrency;
    }

    public void setRegCapitalCurrency(String regCapitalCurrency) {
        this.regCapitalCurrency = regCapitalCurrency == null ? null : regCapitalCurrency.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getRegLocation() {
        return regLocation;
    }

    public void setRegLocation(String regLocation) {
        this.regLocation = regLocation == null ? null : regLocation.trim();
    }
}