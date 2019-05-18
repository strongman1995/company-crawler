package cn.thu.info.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FinancingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinancingExample() {
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

        public Criteria andFIdIsNull() {
            addCriterion("f_id is null");
            return (Criteria) this;
        }

        public Criteria andFIdIsNotNull() {
            addCriterion("f_id is not null");
            return (Criteria) this;
        }

        public Criteria andFIdEqualTo(Integer value) {
            addCriterion("f_id =", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdNotEqualTo(Integer value) {
            addCriterion("f_id <>", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdGreaterThan(Integer value) {
            addCriterion("f_id >", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("f_id >=", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdLessThan(Integer value) {
            addCriterion("f_id <", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdLessThanOrEqualTo(Integer value) {
            addCriterion("f_id <=", value, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdIn(List<Integer> values) {
            addCriterion("f_id in", values, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdNotIn(List<Integer> values) {
            addCriterion("f_id not in", values, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdBetween(Integer value1, Integer value2) {
            addCriterion("f_id between", value1, value2, "fId");
            return (Criteria) this;
        }

        public Criteria andFIdNotBetween(Integer value1, Integer value2) {
            addCriterion("f_id not between", value1, value2, "fId");
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

        public Criteria andRoundIsNull() {
            addCriterion("round is null");
            return (Criteria) this;
        }

        public Criteria andRoundIsNotNull() {
            addCriterion("round is not null");
            return (Criteria) this;
        }

        public Criteria andRoundEqualTo(String value) {
            addCriterion("round =", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotEqualTo(String value) {
            addCriterion("round <>", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundGreaterThan(String value) {
            addCriterion("round >", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundGreaterThanOrEqualTo(String value) {
            addCriterion("round >=", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundLessThan(String value) {
            addCriterion("round <", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundLessThanOrEqualTo(String value) {
            addCriterion("round <=", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundLike(String value) {
            addCriterion("round like", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotLike(String value) {
            addCriterion("round not like", value, "round");
            return (Criteria) this;
        }

        public Criteria andRoundIn(List<String> values) {
            addCriterion("round in", values, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotIn(List<String> values) {
            addCriterion("round not in", values, "round");
            return (Criteria) this;
        }

        public Criteria andRoundBetween(String value1, String value2) {
            addCriterion("round between", value1, value2, "round");
            return (Criteria) this;
        }

        public Criteria andRoundNotBetween(String value1, String value2) {
            addCriterion("round not between", value1, value2, "round");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(String value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(String value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(String value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(String value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(String value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(String value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(String value) {
            addCriterion("amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(String value) {
            addCriterion("amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<String> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<String> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(String value1, String value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(String value1, String value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIntIsNull() {
            addCriterion("amount_int is null");
            return (Criteria) this;
        }

        public Criteria andAmountIntIsNotNull() {
            addCriterion("amount_int is not null");
            return (Criteria) this;
        }

        public Criteria andAmountIntEqualTo(Integer value) {
            addCriterion("amount_int =", value, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntNotEqualTo(Integer value) {
            addCriterion("amount_int <>", value, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntGreaterThan(Integer value) {
            addCriterion("amount_int >", value, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount_int >=", value, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntLessThan(Integer value) {
            addCriterion("amount_int <", value, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntLessThanOrEqualTo(Integer value) {
            addCriterion("amount_int <=", value, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntIn(List<Integer> values) {
            addCriterion("amount_int in", values, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntNotIn(List<Integer> values) {
            addCriterion("amount_int not in", values, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntBetween(Integer value1, Integer value2) {
            addCriterion("amount_int between", value1, value2, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountIntNotBetween(Integer value1, Integer value2) {
            addCriterion("amount_int not between", value1, value2, "amountInt");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyIsNull() {
            addCriterion("amount_currency is null");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyIsNotNull() {
            addCriterion("amount_currency is not null");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyEqualTo(String value) {
            addCriterion("amount_currency =", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyNotEqualTo(String value) {
            addCriterion("amount_currency <>", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyGreaterThan(String value) {
            addCriterion("amount_currency >", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("amount_currency >=", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyLessThan(String value) {
            addCriterion("amount_currency <", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyLessThanOrEqualTo(String value) {
            addCriterion("amount_currency <=", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyLike(String value) {
            addCriterion("amount_currency like", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyNotLike(String value) {
            addCriterion("amount_currency not like", value, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyIn(List<String> values) {
            addCriterion("amount_currency in", values, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyNotIn(List<String> values) {
            addCriterion("amount_currency not in", values, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyBetween(String value1, String value2) {
            addCriterion("amount_currency between", value1, value2, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andAmountCurrencyNotBetween(String value1, String value2) {
            addCriterion("amount_currency not between", value1, value2, "amountCurrency");
            return (Criteria) this;
        }

        public Criteria andIIdIsNull() {
            addCriterion("i_id is null");
            return (Criteria) this;
        }

        public Criteria andIIdIsNotNull() {
            addCriterion("i_id is not null");
            return (Criteria) this;
        }

        public Criteria andIIdEqualTo(Integer value) {
            addCriterion("i_id =", value, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdNotEqualTo(Integer value) {
            addCriterion("i_id <>", value, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdGreaterThan(Integer value) {
            addCriterion("i_id >", value, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("i_id >=", value, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdLessThan(Integer value) {
            addCriterion("i_id <", value, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdLessThanOrEqualTo(Integer value) {
            addCriterion("i_id <=", value, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdIn(List<Integer> values) {
            addCriterion("i_id in", values, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdNotIn(List<Integer> values) {
            addCriterion("i_id not in", values, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdBetween(Integer value1, Integer value2) {
            addCriterion("i_id between", value1, value2, "iId");
            return (Criteria) this;
        }

        public Criteria andIIdNotBetween(Integer value1, Integer value2) {
            addCriterion("i_id not between", value1, value2, "iId");
            return (Criteria) this;
        }

        public Criteria andINameIsNull() {
            addCriterion("i_name is null");
            return (Criteria) this;
        }

        public Criteria andINameIsNotNull() {
            addCriterion("i_name is not null");
            return (Criteria) this;
        }

        public Criteria andINameEqualTo(String value) {
            addCriterion("i_name =", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameNotEqualTo(String value) {
            addCriterion("i_name <>", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameGreaterThan(String value) {
            addCriterion("i_name >", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameGreaterThanOrEqualTo(String value) {
            addCriterion("i_name >=", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameLessThan(String value) {
            addCriterion("i_name <", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameLessThanOrEqualTo(String value) {
            addCriterion("i_name <=", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameLike(String value) {
            addCriterion("i_name like", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameNotLike(String value) {
            addCriterion("i_name not like", value, "iName");
            return (Criteria) this;
        }

        public Criteria andINameIn(List<String> values) {
            addCriterion("i_name in", values, "iName");
            return (Criteria) this;
        }

        public Criteria andINameNotIn(List<String> values) {
            addCriterion("i_name not in", values, "iName");
            return (Criteria) this;
        }

        public Criteria andINameBetween(String value1, String value2) {
            addCriterion("i_name between", value1, value2, "iName");
            return (Criteria) this;
        }

        public Criteria andINameNotBetween(String value1, String value2) {
            addCriterion("i_name not between", value1, value2, "iName");
            return (Criteria) this;
        }

        public Criteria andIUcodeIsNull() {
            addCriterion("i_ucode is null");
            return (Criteria) this;
        }

        public Criteria andIUcodeIsNotNull() {
            addCriterion("i_ucode is not null");
            return (Criteria) this;
        }

        public Criteria andIUcodeEqualTo(String value) {
            addCriterion("i_ucode =", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeNotEqualTo(String value) {
            addCriterion("i_ucode <>", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeGreaterThan(String value) {
            addCriterion("i_ucode >", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeGreaterThanOrEqualTo(String value) {
            addCriterion("i_ucode >=", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeLessThan(String value) {
            addCriterion("i_ucode <", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeLessThanOrEqualTo(String value) {
            addCriterion("i_ucode <=", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeLike(String value) {
            addCriterion("i_ucode like", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeNotLike(String value) {
            addCriterion("i_ucode not like", value, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeIn(List<String> values) {
            addCriterion("i_ucode in", values, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeNotIn(List<String> values) {
            addCriterion("i_ucode not in", values, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeBetween(String value1, String value2) {
            addCriterion("i_ucode between", value1, value2, "iUcode");
            return (Criteria) this;
        }

        public Criteria andIUcodeNotBetween(String value1, String value2) {
            addCriterion("i_ucode not between", value1, value2, "iUcode");
            return (Criteria) this;
        }

        public Criteria andFDateIsNull() {
            addCriterion("f_date is null");
            return (Criteria) this;
        }

        public Criteria andFDateIsNotNull() {
            addCriterion("f_date is not null");
            return (Criteria) this;
        }

        public Criteria andFDateEqualTo(Date value) {
            addCriterion("f_date =", value, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateNotEqualTo(Date value) {
            addCriterion("f_date <>", value, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateGreaterThan(Date value) {
            addCriterion("f_date >", value, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateGreaterThanOrEqualTo(Date value) {
            addCriterion("f_date >=", value, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateLessThan(Date value) {
            addCriterion("f_date <", value, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateLessThanOrEqualTo(Date value) {
            addCriterion("f_date <=", value, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateIn(List<Date> values) {
            addCriterion("f_date in", values, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateNotIn(List<Date> values) {
            addCriterion("f_date not in", values, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateBetween(Date value1, Date value2) {
            addCriterion("f_date between", value1, value2, "fDate");
            return (Criteria) this;
        }

        public Criteria andFDateNotBetween(Date value1, Date value2) {
            addCriterion("f_date not between", value1, value2, "fDate");
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