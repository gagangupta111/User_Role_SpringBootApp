package com.omnirio.model;

import java.util.Objects;

public class User {

    private String userID;
    private String userName;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String roleName;
    private String branch;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userID, user.userID) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(dob, user.dob) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(roleName, user.roleName) &&
                Objects.equals(branch, user.branch);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userID, userName, dob, gender, phoneNumber, roleName, branch);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roleName='" + roleName + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
