package com.cafe.dto.user;

import com.cafe.model.User;
import com.cafe.model.enums.UserType;
import com.cafe.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonView;


public class UserFullDataDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String dateBirthday;
    private UserType type;
    private String login;
    private String password;

    public UserFullDataDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateBirthday = DateUtil.stringFromDate(user.getDateBirthday());
        this.type = user.getUserType();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public UserFullDataDto() {
    }

    public User toModelFull(){
        User user = new User();
        user.setId(this.getId());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setUserType(this.getType());
        user.setDateBirthday(DateUtil.dateFromString(this.getDatBirthday()));
        user.setLogin(this.getLogin());
        user.setPassword(this.getPassword());
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

    public String getDatBirthday() {
        return dateBirthday;
    }

    public void setDatBirthday(String datBirthday) {
        this.dateBirthday = datBirthday;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserFullDataDto{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", dateBirthday='").append(dateBirthday).append('\'');
        sb.append(", type=").append(type);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
