package cn.thu.info.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReginfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReginfoExample() {
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

    protected abstract static class GeneratedCriteria {
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

        public Criteria andRIdIsNull() {
            addCriterion("r_id is null");
            return (Criteria) this;
        }

        public Criteria andRIdIsNotNull() {
            addCriterion("r_id is not null");
            return (Criteria) this;
        }

        public Criteria andRIdEqualTo(Integer value) {
            addCriterion("r_id =", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotEqualTo(Integer value) {
            addCriterion("r_id <>", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdGreaterThan(Integer value) {
            addCriterion("r_id >", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("r_id >=", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdLessThan(Integer value) {
            addCriterion("r_id <", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdLessThanOrEqualTo(Integer value) {
            addCriterion("r_id <=", value, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdIn(List<Integer> values) {
            addCriterion("r_id in", values, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotIn(List<Integer> values) {
            addCriterion("r_id not in", values, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdBetween(Integer value1, Integer value2) {
            addCriterion("r_id between", value1, value2, "rId");
            return (Criteria) this;
        }

        public Criteria andRIdNotBetween(Integer value1, Integer value2) {
            addCriterion("r_id not between", value1, value2, "rId");
            return (Criteria) this;
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

        public Criteria andRegIdIsNull() {
            addCriterion("reg_id is null");
            return (Criteria) this;
        }

        public Criteria andRegIdIsNotNull() {
            addCriterion("reg_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegIdEqualTo(String value) {
            addCriterion("reg_id =", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotEqualTo(String value) {
            addCriterion("reg_id <>", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdGreaterThan(String value) {
            addCriterion("reg_id >", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdGreaterThanOrEqualTo(String value) {
            addCriterion("reg_id >=", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLessThan(String value) {
            addCriterion("reg_id <", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLessThanOrEqualTo(String value) {
            addCriterion("reg_id <=", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdLike(String value) {
            addCriterion("reg_id like", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotLike(String value) {
            addCriterion("reg_id not like", value, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdIn(List<String> values) {
            addCriterion("reg_id in", values, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotIn(List<String> values) {
            addCriterion("reg_id not in", values, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdBetween(String value1, String value2) {
            addCriterion("reg_id between", value1, value2, "regId");
            return (Criteria) this;
        }

        public Criteria andRegIdNotBetween(String value1, String value2) {
            addCriterion("reg_id not between", value1, value2, "regId");
            return (Criteria) this;
        }

        public Criteria andLealPersonIsNull() {
            addCriterion("leal_person is null");
            return (Criteria) this;
        }

        public Criteria andLealPersonIsNotNull() {
            addCriterion("leal_person is not null");
            return (Criteria) this;
        }

        public Criteria andLealPersonEqualTo(String value) {
            addCriterion("leal_person =", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonNotEqualTo(String value) {
            addCriterion("leal_person <>", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonGreaterThan(String value) {
            addCriterion("leal_person >", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonGreaterThanOrEqualTo(String value) {
            addCriterion("leal_person >=", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonLessThan(String value) {
            addCriterion("leal_person <", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonLessThanOrEqualTo(String value) {
            addCriterion("leal_person <=", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonLike(String value) {
            addCriterion("leal_person like", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonNotLike(String value) {
            addCriterion("leal_person not like", value, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonIn(List<String> values) {
            addCriterion("leal_person in", values, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonNotIn(List<String> values) {
            addCriterion("leal_person not in", values, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonBetween(String value1, String value2) {
            addCriterion("leal_person between", value1, value2, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andLealPersonNotBetween(String value1, String value2) {
            addCriterion("leal_person not between", value1, value2, "lealPerson");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNull() {
            addCriterion("reg_date is null");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNotNull() {
            addCriterion("reg_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegDateEqualTo(Date value) {
            addCriterion("reg_date =", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotEqualTo(Date value) {
            addCriterion("reg_date <>", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThan(Date value) {
            addCriterion("reg_date >", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThanOrEqualTo(Date value) {
            addCriterion("reg_date >=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThan(Date value) {
            addCriterion("reg_date <", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThanOrEqualTo(Date value) {
            addCriterion("reg_date <=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateIn(List<Date> values) {
            addCriterion("reg_date in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotIn(List<Date> values) {
            addCriterion("reg_date not in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateBetween(Date value1, Date value2) {
            addCriterion("reg_date between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotBetween(Date value1, Date value2) {
            addCriterion("reg_date not between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIsNull() {
            addCriterion("reg_capital is null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIsNotNull() {
            addCriterion("reg_capital is not null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalEqualTo(String value) {
            addCriterion("reg_capital =", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotEqualTo(String value) {
            addCriterion("reg_capital <>", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalGreaterThan(String value) {
            addCriterion("reg_capital >", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalGreaterThanOrEqualTo(String value) {
            addCriterion("reg_capital >=", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLessThan(String value) {
            addCriterion("reg_capital <", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLessThanOrEqualTo(String value) {
            addCriterion("reg_capital <=", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalLike(String value) {
            addCriterion("reg_capital like", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotLike(String value) {
            addCriterion("reg_capital not like", value, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIn(List<String> values) {
            addCriterion("reg_capital in", values, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotIn(List<String> values) {
            addCriterion("reg_capital not in", values, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalBetween(String value1, String value2) {
            addCriterion("reg_capital between", value1, value2, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalNotBetween(String value1, String value2) {
            addCriterion("reg_capital not between", value1, value2, "regCapital");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntIsNull() {
            addCriterion("reg_capital_int is null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntIsNotNull() {
            addCriterion("reg_capital_int is not null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntEqualTo(Integer value) {
            addCriterion("reg_capital_int =", value, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntNotEqualTo(Integer value) {
            addCriterion("reg_capital_int <>", value, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntGreaterThan(Integer value) {
            addCriterion("reg_capital_int >", value, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntGreaterThanOrEqualTo(Integer value) {
            addCriterion("reg_capital_int >=", value, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntLessThan(Integer value) {
            addCriterion("reg_capital_int <", value, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntLessThanOrEqualTo(Integer value) {
            addCriterion("reg_capital_int <=", value, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntIn(List<Integer> values) {
            addCriterion("reg_capital_int in", values, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntNotIn(List<Integer> values) {
            addCriterion("reg_capital_int not in", values, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntBetween(Integer value1, Integer value2) {
            addCriterion("reg_capital_int between", value1, value2, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalIntNotBetween(Integer value1, Integer value2) {
            addCriterion("reg_capital_int not between", value1, value2, "regCapitalInt");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyIsNull() {
            addCriterion("reg_capital_currency is null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyIsNotNull() {
            addCriterion("reg_capital_currency is not null");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyEqualTo(String value) {
            addCriterion("reg_capital_currency =", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyNotEqualTo(String value) {
            addCriterion("reg_capital_currency <>", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyGreaterThan(String value) {
            addCriterion("reg_capital_currency >", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("reg_capital_currency >=", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyLessThan(String value) {
            addCriterion("reg_capital_currency <", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyLessThanOrEqualTo(String value) {
            addCriterion("reg_capital_currency <=", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyLike(String value) {
            addCriterion("reg_capital_currency like", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyNotLike(String value) {
            addCriterion("reg_capital_currency not like", value, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyIn(List<String> values) {
            addCriterion("reg_capital_currency in", values, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyNotIn(List<String> values) {
            addCriterion("reg_capital_currency not in", values, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyBetween(String value1, String value2) {
            addCriterion("reg_capital_currency between", value1, value2, "regCapitalCurrency");
            return (Criteria) this;
        }

        public Criteria andRegCapitalCurrencyNotBetween(String value1, String value2) {
            addCriterion("reg_capital_currency not between", value1, value2, "regCapitalCurrency");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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