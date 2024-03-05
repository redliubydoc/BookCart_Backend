package com.raj.ms.identity.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trole")
public class Role {

  @Id
  @Column(name = "role_id")
  private long roleId;

  @Column(name = "role_cd")
  private String roleCd;

  @Column(name = "role_nm")
  private String roleNm;

  @OneToMany
  @JoinColumn(name = "user_id")
  private List<RefreshToken> refreshTokens;

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public String getRoleCd() {
    return roleCd;
  }

  public void setRoleCd(String roleCd) {
    this.roleCd = roleCd;
  }

  public String getRoleNm() {
    return roleNm;
  }

  public void setRoleNm(String roleNm) {
    this.roleNm = roleNm;
  }

  public List<RefreshToken> getRefreshTokens() {
    return refreshTokens;
  }

  public void setRefreshTokens(List<RefreshToken> refreshTokens) {
    this.refreshTokens = refreshTokens;
  }

}
