package com.raj.ms.identity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.security.Timestamp;

@Entity
@Table(name = "trefresh_token")
public class RefreshToken {

  @Id
  @Column(name = "refresh_token_id")
  private long refreshTokenId;

  @Column(name = "token")
  private String token;

  @Column(name = "expiration_ts")
  private Timestamp expirationTimeStamp;

  public long getRefreshTokenId() {
    return refreshTokenId;
  }

  public void setRefreshTokenId(long refreshTokenId) {
    this.refreshTokenId = refreshTokenId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Timestamp getExpirationTimeStamp() {
    return expirationTimeStamp;
  }

  public void setExpirationTimeStamp(Timestamp expirationTimeStamp) {
    this.expirationTimeStamp = expirationTimeStamp;
  }
}
