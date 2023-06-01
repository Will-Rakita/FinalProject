package com.example.androidlabs;

import java.util.Date;

public class AdapterList {

    private String Explanation;
    private String pageDate;
    private String HdUrl;
    public AdapterList( String pageDate,String Explanation, String HdUrl){

        this.pageDate = pageDate;
        this.HdUrl = HdUrl;
        this.Explanation =  Explanation;
    }

    public String getExplanation() {
        return Explanation;
    }
    public String getDate() {
        return pageDate;
    }
    public void setExplanation(String Explanation) {
        this.Explanation = Explanation;
    }
    public String getHdUrl() {
        return HdUrl;
    }
    public void setLong(Long id) {
        this.HdUrl = HdUrl;
    }
    public void setDate(String pageDate) {
        this.pageDate = pageDate;
    }
}
