package com.cafe.model;

public class Login {

   private String login;
   private String password;
    private String sha256;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Login{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", sha256='").append(sha256).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
