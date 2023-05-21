package com.knowledge.managmentimenpost.dataModule;

public class DataModuleUser {
    private String Name;
    private String DateCreate;
    private String DateUpdate;
    private String UserPhone;
    private String IndexUser;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }

    public String getDateUpdate() {
        return DateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        DateUpdate = dateUpdate;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getIndexUser() {
        return IndexUser;
    }

    public void setIndexUser(String indexUser) {
        IndexUser = indexUser;
    }
}
