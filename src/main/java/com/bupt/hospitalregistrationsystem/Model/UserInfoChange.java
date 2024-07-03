package com.bupt.hospitalregistrationsystem.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class UserInfoChange {
  @Id
  private String id;
  private String username;
  private User oldUserInfo;
  private User newUserInfo;
  private String date;  // Date
  private Admin checker;  // 审批人
  private Boolean checked;  // 管理是否审查过
  private Boolean allowed;  // 是否通过

  @JsonCreator
  public UserInfoChange() {
    this.id = UUID.randomUUID().toString();
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Admin getChecker() {
    return checker;
  }

  public void setChecker(Admin checker) {
    this.checker = checker;
  }

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
    return date;
  }

  public void setDate(String date) {
    this.date = date;
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
