package com.horan.dsmlmanager.entity;

import java.io.Serializable;

public class Image implements Serializable {
    private int id;

    public Image(String imgName) {
        this.imgName = imgName;
    }

    private int dataSetId;
    private String imgName;
    private String src;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(int dataSetId) {
        this.dataSetId = dataSetId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public Image() {

    }

    private int sign;


}
