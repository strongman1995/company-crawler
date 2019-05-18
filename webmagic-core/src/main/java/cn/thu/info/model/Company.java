package cn.thu.info.model;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable{
    private Integer cId;

    private String name;

    private String website;

    private String companyName;

    private Date setupTime;

    private String city;

    private String financingStep;

    private String field1;

    private String field2;

    private String field3;

    private String companyImg;

    private String founder1;

    private String founder2;

    private String founder3;

    private String founder4;

    private String founder5;

    private String founder6;

    private Integer source;

    private String introduction;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Date getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(Date setupTime) {
        this.setupTime = setupTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getFinancingStep() {
        return financingStep;
    }

    public void setFinancingStep(String financingStep) {
        this.financingStep = financingStep == null ? null : financingStep.trim();
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1 == null ? null : field1.trim();
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2 == null ? null : field2.trim();
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3 == null ? null : field3.trim();
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg == null ? null : companyImg.trim();
    }

    public String getFounder1() {
        return founder1;
    }

    public void setFounder1(String founder1) {
        this.founder1 = founder1 == null ? null : founder1.trim();
    }

    public String getFounder2() {
        return founder2;
    }

    public void setFounder2(String founder2) {
        this.founder2 = founder2 == null ? null : founder2.trim();
    }

    public String getFounder3() {
        return founder3;
    }

    public void setFounder3(String founder3) {
        this.founder3 = founder3 == null ? null : founder3.trim();
    }

    public String getFounder4() {
        return founder4;
    }

    public void setFounder4(String founder4) {
        this.founder4 = founder4 == null ? null : founder4.trim();
    }

    public String getFounder5() {
        return founder5;
    }

    public void setFounder5(String founder5) {
        this.founder5 = founder5 == null ? null : founder5.trim();
    }

    public String getFounder6() {
        return founder6;
    }

    public void setFounder6(String founder6) {
        this.founder6 = founder6 == null ? null : founder6.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}