package com.raj.ms.identity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tuser")
public class User {

  @Id
  @Column(name = "user_id")
  private long userId;

  @Column(name = "username")
  private String username;

  @Column(name = "email_id")
  private String emailId;

  @Column(name = "phone_no")
  private String phoneNo;

  @Column(name = "password")
  private String password;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}