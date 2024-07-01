package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserInfoChange {
  private String username;
  private User oldUserInfo;
  private User newUserInfo;
  private String Date;
  private Boolean checked;  // 管理是否审查过
  private Boolean allowed;  // 是否通过

  public Boolean getAllowed() {
    return allowed;
  }

  public void setAllowed(Boolean allowed) {
    this.allowed = allowed;
  }

  public Boolean getChecked() {
    return checked;
  }

  public void setChecked(Boolean checked) {
    this.checked = checked;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getDate() {
    return Date;
  }

  public void setDate(String date) {
    Date = date;
  }

  public User getNewUserInfo() {
    return newUserInfo;
  }

  public void setNewUserInfo(User newUserInfo) {
    this.newUserInfo = newUserInfo;
  }

  public User getOldUserInfo() {
    return oldUserInfo;
  }

  public void setOldUserInfo(User oldUserInfo) {
    this.oldUserInfo = oldUserInfo;
  }
}
