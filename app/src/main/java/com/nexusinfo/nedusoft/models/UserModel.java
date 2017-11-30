package com.nexusinfo.nedusoft.models;

import java.io.Serializable;

/**
 * Created by firdous on 11/28/2017.
 */

public class UserModel implements Serializable{

    private String userID, schoolCode, schoolDBName;

    public UserModel(String user, String schoolCode, String schoolDBName) {
        this.userID = user;
        this.schoolCode = schoolCode;
        this.schoolDBName = schoolDBName;
    }

    public UserModel() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
}
