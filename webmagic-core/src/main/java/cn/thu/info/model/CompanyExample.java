package cn.thu.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompanyExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CompanyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCIdIsNull() {
            addCriterion("c_id is null");
            return (Criteria) this;
        }

        public Criteria andCIdIsNotNull() {
            addCriterion("c_id is not null");
            return (Criteria) this;
        }

        public Criteria andCIdEqualTo(Integer value) {
            addCriterion("c_id =", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotEqualTo(Integer value) {
            addCriterion("c_id <>", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdGreaterThan(Integer value) {
            addCriterion("c_id >", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_id >=", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdLessThan(Integer value) {
            addCriterion("c_id <", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdLessThanOrEqualTo(Integer value) {
            addCriterion("c_id <=", value, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdIn(List<Integer> values) {
            addCriterion("c_id in", values, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotIn(List<Integer> values) {
            addCriterion("c_id not in", values, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdBetween(Integer value1, Integer value2) {
            addCriterion("c_id between", value1, value2, "cId");
            return (Criteria) this;
        }

        public Criteria andCIdNotBetween(Integer value1, Integer value2) {
            addCriterion("c_id not between", value1, value2, "cId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNull() {
            addCriterion("website is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteIsNotNull() {
            addCriterion("website is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteEqualTo(String value) {
            addCriterion("website =", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotEqualTo(String value) {
            addCriterion("website <>", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThan(String value) {
            addCriterion("website >", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("website >=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThan(String value) {
            addCriterion("website <", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLessThanOrEqualTo(String value) {
            addCriterion("website <=", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteLike(String value) {
            addCriterion("website like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotLike(String value) {
            addCriterion("website not like", value, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteIn(List<String> values) {
            addCriterion("website in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotIn(List<String> values) {
            addCriterion("website not in", values, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteBetween(String value1, String value2) {
            addCriterion("website between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andWebsiteNotBetween(String value1, String value2) {
            addCriterion("website not between", value1, value2, "website");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andSetupTimeIsNull() {
            addCriterion("setup_time is null");
            return (Criteria) this;
        }

        public Criteria andSetupTimeIsNotNull() {
            addCriterion("setup_time is not null");
            return (Criteria) this;
        }

        public Criteria andSetupTimeEqualTo(Date value) {
            addCriterion("setup_time =", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeNotEqualTo(Date value) {
            addCriterion("setup_time <>", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeGreaterThan(Date value) {
            addCriterion("setup_time >", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("setup_time >=", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeLessThan(Date value) {
            addCriterion("setup_time <", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeLessThanOrEqualTo(Date value) {
            addCriterion("setup_time <=", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeIn(List<Date> values) {
            addCriterion("setup_time in", values, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeNotIn(List<Date> values) {
            addCriterion("setup_time not in", values, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeBetween(Date value1, Date value2) {
            addCriterion("setup_time between", value1, value2, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeNotBetween(Date value1, Date value2) {
            addCriterion("setup_time not between", value1, value2, "setupTime");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andFinancingStepIsNull() {
            addCriterion("financing_step is null");
            return (Criteria) this;
        }

        public Criteria andFinancingStepIsNotNull() {
            addCriterion("financing_step is not null");
            return (Criteria) this;
        }

        public Criteria andFinancingStepEqualTo(String value) {
            addCriterion("financing_step =", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepNotEqualTo(String value) {
            addCriterion("financing_step <>", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepGreaterThan(String value) {
            addCriterion("financing_step >", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepGreaterThanOrEqualTo(String value) {
            addCriterion("financing_step >=", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepLessThan(String value) {
            addCriterion("financing_step <", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepLessThanOrEqualTo(String value) {
            addCriterion("financing_step <=", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepLike(String value) {
            addCriterion("financing_step like", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepNotLike(String value) {
            addCriterion("financing_step not like", value, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepIn(List<String> values) {
            addCriterion("financing_step in", values, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepNotIn(List<String> values) {
            addCriterion("financing_step not in", values, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepBetween(String value1, String value2) {
            addCriterion("financing_step between", value1, value2, "financingStep");
            return (Criteria) this;
        }

        public Criteria andFinancingStepNotBetween(String value1, String value2) {
            addCriterion("financing_step not between", value1, value2, "financingStep");
            return (Criteria) this;
        }

        public Criteria andField1IsNull() {
            addCriterion("field_1 is null");
            return (Criteria) this;
        }

        public Criteria andField1IsNotNull() {
            addCriterion("field_1 is not null");
            return (Criteria) this;
        }

        public Criteria andField1EqualTo(String value) {
            addCriterion("field_1 =", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotEqualTo(String value) {
            addCriterion("field_1 <>", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1GreaterThan(String value) {
            addCriterion("field_1 >", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1GreaterThanOrEqualTo(String value) {
            addCriterion("field_1 >=", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1LessThan(String value) {
            addCriterion("field_1 <", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1LessThanOrEqualTo(String value) {
            addCriterion("field_1 <=", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1Like(String value) {
            addCriterion("field_1 like", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotLike(String value) {
            addCriterion("field_1 not like", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1In(List<String> values) {
            addCriterion("field_1 in", values, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotIn(List<String> values) {
            addCriterion("field_1 not in", values, "field1");
            return (Criteria) this;
        }

        public Criteria andField1Between(String value1, String value2) {
            addCriterion("field_1 between", value1, value2, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotBetween(String value1, String value2) {
            addCriterion("field_1 not between", value1, value2, "field1");
            return (Criteria) this;
        }

        public Criteria andField2IsNull() {
            addCriterion("field_2 is null");
            return (Criteria) this;
        }

        public Criteria andField2IsNotNull() {
            addCriterion("field_2 is not null");
            return (Criteria) this;
        }

        public Criteria andField2EqualTo(String value) {
            addCriterion("field_2 =", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotEqualTo(String value) {
            addCriterion("field_2 <>", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2GreaterThan(String value) {
            addCriterion("field_2 >", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2GreaterThanOrEqualTo(String value) {
            addCriterion("field_2 >=", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2LessThan(String value) {
            addCriterion("field_2 <", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2LessThanOrEqualTo(String value) {
            addCriterion("field_2 <=", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2Like(String value) {
            addCriterion("field_2 like", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotLike(String value) {
            addCriterion("field_2 not like", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2In(List<String> values) {
            addCriterion("field_2 in", values, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotIn(List<String> values) {
            addCriterion("field_2 not in", values, "field2");
            return (Criteria) this;
        }

        public Criteria andField2Between(String value1, String value2) {
            addCriterion("field_2 between", value1, value2, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotBetween(String value1, String value2) {
            addCriterion("field_2 not between", value1, value2, "field2");
            return (Criteria) this;
        }

        public Criteria andField3IsNull() {
            addCriterion("field_3 is null");
            return (Criteria) this;
        }

        public Criteria andField3IsNotNull() {
            addCriterion("field_3 is not null");
            return (Criteria) this;
        }

        public Criteria andField3EqualTo(String value) {
            addCriterion("field_3 =", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotEqualTo(String value) {
            addCriterion("field_3 <>", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3GreaterThan(String value) {
            addCriterion("field_3 >", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3GreaterThanOrEqualTo(String value) {
            addCriterion("field_3 >=", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3LessThan(String value) {
            addCriterion("field_3 <", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3LessThanOrEqualTo(String value) {
            addCriterion("field_3 <=", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3Like(String value) {
            addCriterion("field_3 like", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotLike(String value) {
            addCriterion("field_3 not like", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3In(List<String> values) {
            addCriterion("field_3 in", values, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotIn(List<String> values) {
            addCriterion("field_3 not in", values, "field3");
            return (Criteria) this;
        }

        public Criteria andField3Between(String value1, String value2) {
            addCriterion("field_3 between", value1, value2, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotBetween(String value1, String value2) {
            addCriterion("field_3 not between", value1, value2, "field3");
            return (Criteria) this;
        }

        public Criteria andCompanyImgIsNull() {
            addCriterion("company_img is null");
            return (Criteria) this;
        }

        public Criteria andCompanyImgIsNotNull() {
            addCriterion("company_img is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyImgEqualTo(String value) {
            addCriterion("company_img =", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgNotEqualTo(String value) {
            addCriterion("company_img <>", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgGreaterThan(String value) {
            addCriterion("company_img >", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgGreaterThanOrEqualTo(String value) {
            addCriterion("company_img >=", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgLessThan(String value) {
            addCriterion("company_img <", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgLessThanOrEqualTo(String value) {
            addCriterion("company_img <=", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgLike(String value) {
            addCriterion("company_img like", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgNotLike(String value) {
            addCriterion("company_img not like", value, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgIn(List<String> values) {
            addCriterion("company_img in", values, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgNotIn(List<String> values) {
            addCriterion("company_img not in", values, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgBetween(String value1, String value2) {
            addCriterion("company_img between", value1, value2, "companyImg");
            return (Criteria) this;
        }

        public Criteria andCompanyImgNotBetween(String value1, String value2) {
            addCriterion("company_img not between", value1, value2, "companyImg");
            return (Criteria) this;
        }

        public Criteria andFounder1IsNull() {
            addCriterion("founder_1 is null");
            return (Criteria) this;
        }

        public Criteria andFounder1IsNotNull() {
            addCriterion("founder_1 is not null");
            return (Criteria) this;
        }

        public Criteria andFounder1EqualTo(String value) {
            addCriterion("founder_1 =", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1NotEqualTo(String value) {
            addCriterion("founder_1 <>", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1GreaterThan(String value) {
            addCriterion("founder_1 >", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1GreaterThanOrEqualTo(String value) {
            addCriterion("founder_1 >=", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1LessThan(String value) {
            addCriterion("founder_1 <", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1LessThanOrEqualTo(String value) {
            addCriterion("founder_1 <=", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1Like(String value) {
            addCriterion("founder_1 like", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1NotLike(String value) {
            addCriterion("founder_1 not like", value, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1In(List<String> values) {
            addCriterion("founder_1 in", values, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1NotIn(List<String> values) {
            addCriterion("founder_1 not in", values, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1Between(String value1, String value2) {
            addCriterion("founder_1 between", value1, value2, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder1NotBetween(String value1, String value2) {
            addCriterion("founder_1 not between", value1, value2, "founder1");
            return (Criteria) this;
        }

        public Criteria andFounder2IsNull() {
            addCriterion("founder_2 is null");
            return (Criteria) this;
        }

        public Criteria andFounder2IsNotNull() {
            addCriterion("founder_2 is not null");
            return (Criteria) this;
        }

        public Criteria andFounder2EqualTo(String value) {
            addCriterion("founder_2 =", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2NotEqualTo(String value) {
            addCriterion("founder_2 <>", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2GreaterThan(String value) {
            addCriterion("founder_2 >", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2GreaterThanOrEqualTo(String value) {
            addCriterion("founder_2 >=", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2LessThan(String value) {
            addCriterion("founder_2 <", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2LessThanOrEqualTo(String value) {
            addCriterion("founder_2 <=", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2Like(String value) {
            addCriterion("founder_2 like", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2NotLike(String value) {
            addCriterion("founder_2 not like", value, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2In(List<String> values) {
            addCriterion("founder_2 in", values, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2NotIn(List<String> values) {
            addCriterion("founder_2 not in", values, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2Between(String value1, String value2) {
            addCriterion("founder_2 between", value1, value2, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder2NotBetween(String value1, String value2) {
            addCriterion("founder_2 not between", value1, value2, "founder2");
            return (Criteria) this;
        }

        public Criteria andFounder3IsNull() {
            addCriterion("founder_3 is null");
            return (Criteria) this;
        }

        public Criteria andFounder3IsNotNull() {
            addCriterion("founder_3 is not null");
            return (Criteria) this;
        }

        public Criteria andFounder3EqualTo(String value) {
            addCriterion("founder_3 =", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3NotEqualTo(String value) {
            addCriterion("founder_3 <>", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3GreaterThan(String value) {
            addCriterion("founder_3 >", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3GreaterThanOrEqualTo(String value) {
            addCriterion("founder_3 >=", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3LessThan(String value) {
            addCriterion("founder_3 <", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3LessThanOrEqualTo(String value) {
            addCriterion("founder_3 <=", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3Like(String value) {
            addCriterion("founder_3 like", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3NotLike(String value) {
            addCriterion("founder_3 not like", value, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3In(List<String> values) {
            addCriterion("founder_3 in", values, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3NotIn(List<String> values) {
            addCriterion("founder_3 not in", values, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3Between(String value1, String value2) {
            addCriterion("founder_3 between", value1, value2, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder3NotBetween(String value1, String value2) {
            addCriterion("founder_3 not between", value1, value2, "founder3");
            return (Criteria) this;
        }

        public Criteria andFounder4IsNull() {
            addCriterion("founder_4 is null");
            return (Criteria) this;
        }

        public Criteria andFounder4IsNotNull() {
            addCriterion("founder_4 is not null");
            return (Criteria) this;
        }

        public Criteria andFounder4EqualTo(String value) {
            addCriterion("founder_4 =", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4NotEqualTo(String value) {
            addCriterion("founder_4 <>", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4GreaterThan(String value) {
            addCriterion("founder_4 >", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4GreaterThanOrEqualTo(String value) {
            addCriterion("founder_4 >=", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4LessThan(String value) {
            addCriterion("founder_4 <", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4LessThanOrEqualTo(String value) {
            addCriterion("founder_4 <=", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4Like(String value) {
            addCriterion("founder_4 like", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4NotLike(String value) {
            addCriterion("founder_4 not like", value, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4In(List<String> values) {
            addCriterion("founder_4 in", values, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4NotIn(List<String> values) {
            addCriterion("founder_4 not in", values, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4Between(String value1, String value2) {
            addCriterion("founder_4 between", value1, value2, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder4NotBetween(String value1, String value2) {
            addCriterion("founder_4 not between", value1, value2, "founder4");
            return (Criteria) this;
        }

        public Criteria andFounder5IsNull() {
            addCriterion("founder_5 is null");
            return (Criteria) this;
        }

        public Criteria andFounder5IsNotNull() {
            addCriterion("founder_5 is not null");
            return (Criteria) this;
        }

        public Criteria andFounder5EqualTo(String value) {
            addCriterion("founder_5 =", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5NotEqualTo(String value) {
            addCriterion("founder_5 <>", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5GreaterThan(String value) {
            addCriterion("founder_5 >", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5GreaterThanOrEqualTo(String value) {
            addCriterion("founder_5 >=", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5LessThan(String value) {
            addCriterion("founder_5 <", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5LessThanOrEqualTo(String value) {
            addCriterion("founder_5 <=", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5Like(String value) {
            addCriterion("founder_5 like", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5NotLike(String value) {
            addCriterion("founder_5 not like", value, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5In(List<String> values) {
            addCriterion("founder_5 in", values, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5NotIn(List<String> values) {
            addCriterion("founder_5 not in", values, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5Between(String value1, String value2) {
            addCriterion("founder_5 between", value1, value2, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder5NotBetween(String value1, String value2) {
            addCriterion("founder_5 not between", value1, value2, "founder5");
            return (Criteria) this;
        }

        public Criteria andFounder6IsNull() {
            addCriterion("founder_6 is null");
            return (Criteria) this;
        }

        public Criteria andFounder6IsNotNull() {
            addCriterion("founder_6 is not null");
            return (Criteria) this;
        }

        public Criteria andFounder6EqualTo(String value) {
            addCriterion("founder_6 =", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6NotEqualTo(String value) {
            addCriterion("founder_6 <>", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6GreaterThan(String value) {
            addCriterion("founder_6 >", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6GreaterThanOrEqualTo(String value) {
            addCriterion("founder_6 >=", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6LessThan(String value) {
            addCriterion("founder_6 <", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6LessThanOrEqualTo(String value) {
            addCriterion("founder_6 <=", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6Like(String value) {
            addCriterion("founder_6 like", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6NotLike(String value) {
            addCriterion("founder_6 not like", value, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6In(List<String> values) {
            addCriterion("founder_6 in", values, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6NotIn(List<String> values) {
            addCriterion("founder_6 not in", values, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6Between(String value1, String value2) {
            addCriterion("founder_6 between", value1, value2, "founder6");
            return (Criteria) this;
        }

        public Criteria andFounder6NotBetween(String value1, String value2) {
            addCriterion("founder_6 not between", value1, value2, "founder6");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Integer value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Integer value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Integer value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Integer value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Integer value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Integer> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Integer> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Integer value1, Integer value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion  implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        public Criterion() {
            super();
        }
        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}