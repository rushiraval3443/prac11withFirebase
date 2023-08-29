package com.example.matrimonialapplication.model;

public class UserModel {
    String userId, userImage, userName, userHeight, userReligion, userAddress, userAge, userWeight, userComplexion, userDob;
    String userEmail;
    String latestEducation;
    String University;
    String fatherName;
    String fatherOccupation;
    String motherName;

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    String motherOccupation;
    String noOfSiblings;
    String income;

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    String religion;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getNoOfSiblings() {
        return noOfSiblings;
    }

    public void setNoOfSiblings(String noOfSiblings) {
        this.noOfSiblings = noOfSiblings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String address;

    public String getUserPhone() {
        return userPhone;
    }

    public String getUniversity() {
        return University;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public String getLatestEducation() {
        return latestEducation;
    }

    public void setLatestEducation(String latestEducation) {
        this.latestEducation = latestEducation;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    String userPhone;

    public String getUserId() {
        return userId;
    }

    public String getUserComplexion() {
        return userComplexion;
    }

    public void setUserComplexion(String userComplexion) {
        this.userComplexion = userComplexion;
    }

    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(String userWeight) {
        this.userWeight = userWeight;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserModel(String userId, String userImage, String userName, String userHeight, String userReligion, String userAddress, String userAge) {
        this.userId = userId;
        this.userImage = userImage;
        this.userName = userName;
        this.userHeight = userHeight;
        this.userReligion = userReligion;
        this.userAddress = userAddress;
        this.userAge = userAge;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(String userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserReligion() {
        return userReligion;
    }

    public void setUserReligion(String userReligion) {
        this.userReligion = userReligion;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public UserModel() {
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
