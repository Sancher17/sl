package com.cafe.model;

import com.cafe.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends GenericEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_birthday")
    private LocalDateTime dateBirthday;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;


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

    public LocalDateTime getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(LocalDateTime dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
        String delimetre = String.valueOf('\'');
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(getId()).append(delimetre);
        sb.append(", firstName='").append(firstName).append(delimetre);
        sb.append(", lastName='").append(lastName).append(delimetre);
        sb.append(", dateBirthday=").append(dateBirthday).append(delimetre);
        sb.append(", userType='").append(userType).append(delimetre);
        sb.append(", login='").append(login).append(delimetre);
        sb.append(", password='").append(password).append(delimetre);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
