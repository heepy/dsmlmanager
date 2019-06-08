package com.horan.dsmlmanager.entity;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    private int id;
    private String proName;
    private String src; //项目相对路径

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProModel() {
        return proModel;
    }

    public void setProModel(String proModel) {
        this.proModel = proModel;
    }

    public Date getProTime() {
        return proTime;
    }

    public void setProTime(Date proTime) {
        this.proTime = proTime;
    }

    public String getProDec() {
        return proDec;
    }

    public void setProDec(String proDec) {
        this.proDec = proDec;
    }

    private int userId;
    public Project() {
    }




    private String proModel;
    private Date proTime;
    private String proDec;
}
