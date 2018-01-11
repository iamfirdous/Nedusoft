package com.nexusinfo.nedusoft.models;

import java.io.Serializable;

/**
 * Created by firdous on 11/28/2017.
 */

public class UserModel implements Serializable{

    private String userID, fatherMobile, schoolCode, schoolDBName, schoolEmail, schoolName, cmpId, brCode;
    private int studentID;

    public UserModel() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFatherMobile() {
        return fatherMobile;
    }

    public void setFatherMobile(String fatherMobile) {
        this.fatherMobile = fatherMobile;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolDBName() {
        return schoolDBName;
    }

    public void setSchoolDBName(String schoolDBName) {
        this.schoolDBName = schoolDBName;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCmpId() {
        return cmpId;
    }

    public void setCmpId(String cmpId) {
        this.cmpId = cmpId;
    }

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }
}
