package com.cafe.security.domain;


public class DetailedProfile {

    private final Picture picture;
    private final Name name;
    private final String email;
    private final String username;

    public DetailedProfile(Profile profile) {
        name = profile.getName();
        email = profile.getEmail();
        picture = profile.getPicture();
        username = profile.getLogin().getUsername();
    }

    public Picture getPicture() {
        return picture;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
