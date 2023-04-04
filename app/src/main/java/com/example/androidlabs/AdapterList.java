package com.example.androidlabs;

public class AdapterList {
    private String text;
    private boolean urgent;
    private long id;
    public AdapterList(String text, boolean urgent, long id){
        this.text = text;
        this.urgent = urgent;
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public boolean getBoolean() {
        return urgent;
    }
    public void setText(String text) {
        this.text = text;
    }
    public long getLong() {
        return id;
    }
    public void setLong(Long id) {
        this.id = id;
    }
    public void setBoolean(boolean urgent) {
        this.urgent = urgent;
    }
}
