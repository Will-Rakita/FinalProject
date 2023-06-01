package com.example.androidlabs;

import java.util.Date;

public class AdapterList {


    private String pageDate;

    private long id;

    /**
     * The default constructor
     * @param pageDate
     * @param id
     */
    public AdapterList( String pageDate, long id){

        this.pageDate = pageDate;
        this.id = id;
    }


    public long getId() {
        return id;
    }
    public String getDate() {
        return pageDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String pageDate) {
        this.pageDate = pageDate;
    }
}
