package com.hc.oywt.service.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String name) {

        this.id = id;
        this.name = name;
    }
}
