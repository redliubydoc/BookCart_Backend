package com.raj.ms.identity.entity;

import jakarta.persistence.*;

import java.util.List;

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

  @Column(name = "role")
  private String role;

  @OneToMany
  @JoinColumn(name = "user_id")
  private List<RefreshToken> refreshTokens;

}