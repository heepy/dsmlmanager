package com.horan.dsmlmanager.entity;

import java.io.Serializable;
import java.util.Date;

public class DataSet implements Serializable {
    private int id;
    private String src;//目录相对路径

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public DataSet() {
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    private int proId;
    private String dataSetName;
    private Date createTime;
    private int imageTotal;
    private int annotationTotal;
    private int status;

    public int getAnnotationTotal() {
        return annotationTotal;
    }

    public void setAnnotationTotal(int annotationTotal) {
        this.annotationTotal = annotationTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public void setDataSetName(String dataSetName) {
        this.dataSetName = dataSetName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getImageTotal() {
        return imageTotal;
    }

    public void setImageTotal(int imageTotal) {
        this.imageTotal = imageTotal;
    }



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
