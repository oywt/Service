package com.hc.oywt.model;

public class TimeMessage{
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

    @Override
    public String toString() {
        return "TimeMessage{" +
                "id=" + id +
                ", date='" + date + '\'' +
                '}';
    }
}
