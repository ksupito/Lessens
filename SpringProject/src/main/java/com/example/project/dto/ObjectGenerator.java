package com.example.project.dto;

import com.example.project.model.User;
import com.example.project.model.UserInfo;

import java.util.List;

public class ObjectGenerator {
    UserInfo userInfo;
    String page;
    List<User> listOfUser;

    public ObjectGenerator(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ObjectGenerator(String page) {
        this.page = page;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<User> getListOfUser() {
        return listOfUser;
    }

    public void setListOfUser(List<User> listOfUser) {
        this.listOfUser = listOfUser;
    }

    public ObjectGenerator(List<User> listOfUser) {
        this.listOfUser = listOfUser;
    }
}
