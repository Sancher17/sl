package com.cafe.security.domain;


import java.net.URL;


public class MinimalProfile {
    private String username;
    private String token;
    private Name name;
    private URL thumbnail;

    public MinimalProfile(Profile profile) {
        name = profile.getName();
        username = profile.getLogin().getUsername();
        thumbnail = profile.getPicture().getThumbnail();
    }

    public MinimalProfile(String username) {
        this.username = username + "  - нет такого пользователя";
    }

    public String getUsername() {
        return username;
    }

    public Name getName() {
        return name;
    }

    public URL getThumbnail() {
        return thumbnail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setThumbnail(URL thumbnail) {
        this.thumbnail = thumbnail;
    }
}
