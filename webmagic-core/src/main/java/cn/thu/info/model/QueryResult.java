package cn.thu.info.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class QueryResult implements Serializable{

    public QueryResult(){
        this.companies = new ArrayList<Company>();
        status = Status.FAIL;
    }
    public List<Company> companies;
    public Status status;
    public String message;

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
