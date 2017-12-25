package com.hc.oywt.service.model;

import java.io.Serializable;

public class TimeMessage implements Serializable{
    private int id ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public TimeMessage(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public TimeMessage(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
