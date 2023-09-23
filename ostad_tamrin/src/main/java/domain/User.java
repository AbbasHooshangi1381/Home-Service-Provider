package domain;

import base.domain.Entity;
import enumoration.UserType;

import java.time.ZonedDateTime;
@SuppressWarnings("unused")
//Entity
public class User extends Entity {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Integer age;
    private String mobileNumber;
    private long createDateMillis;
    //transient
    private ZonedDateTime createDate;
    private UserType userType;
    private Role[] roles;

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getCreateDateMillis() {
        return createDateMillis;
    }

    public void setCreateDateMillis(long createDateMillis) {
        this.createDateMillis = createDateMillis;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
