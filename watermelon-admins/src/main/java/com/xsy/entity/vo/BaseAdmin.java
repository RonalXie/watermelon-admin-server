package com.xsy.entity.vo;

import java.util.Date;

public class BaseAdmin {

    private String username;
    /**
     * 用户头像地址
     */
    private String avatar;

    public BaseAdmin() {
    }

    public BaseAdmin(String username,  String avatar) {

        this.username = username;

        this.avatar = avatar;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
