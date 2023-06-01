package com.example.androidlabs;

import java.util.Date;

public class AdapterList {
    private String Title;
    private String Explanation;
    private Date pageDate;
    private long id;
    public AdapterList(String Title, Date pageDate,String Explanation, long id){
        this.Title = Title;
        this.pageDate = pageDate;
        this.id = id;
        this.Explanation =  Explanation;
    }
    public String getTitle() {
        return Title;
    }
    public String getExplanation() {
        return Explanation;
    }
    public Date getDate() {
        return pageDate;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }
    public void setExplanation(String Explanation) {
        this.Explanation = Explanation;
    }
    public long getLong() {
        return id;
    }
    public void setLong(Long id) {
        this.id = id;
    }
    public void setDate(Date urgent) {
        this.pageDate = urgent;
    }
}
