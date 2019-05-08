package com.horan.dsmlmanager.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.Serializable;


public class User implements Serializable {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private String name;
}
