package com.horan.dsmlmanager.entity;

import java.io.Serializable;

public class Project implements Serializable {
    private int projectID;
    private String projectName;

    public Project() {
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(String projectModel) {
        this.projectModel = projectModel;
    }

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    public String getProjectDec() {
        return projectDec;
    }

    public void setProjectDec(String projectDec) {
        this.projectDec = projectDec;
    }

    private String projectModel;
    private String projectTime;
    private String projectDec;
}
