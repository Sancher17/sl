package com.cafe.dto.user;

import com.cafe.model.User;
import com.cafe.model.enums.UserType;
import com.cafe.utils.DateUtil;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private UserType type;
    private String login;
    private String password;
    private String dateBirthday;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.type = user.getUserType();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.dateBirthday = DateUtil.stringFromDate(user.getDateBirthday());
    }

    public User toModel() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDateBirthday(DateUtil.dateFromString(dateBirthday));
        user.setUserType(type);
        user.setPassword(password);
        user.setLogin(login);
        return user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public UserType getType() {
        return type;
    }
    public void setType(UserType type) {
        this.type = type;
    }
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
}
