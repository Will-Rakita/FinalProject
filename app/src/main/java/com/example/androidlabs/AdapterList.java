package com.example.androidlabs;

public class AdapterList {
    private String text;
    private boolean urgent;
    public AdapterList(String text, boolean urgent){
        this.text = text;
        this.urgent = urgent;
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
    public void setBoolean(boolean urgent) {
        this.urgent = urgent;
    }
}
