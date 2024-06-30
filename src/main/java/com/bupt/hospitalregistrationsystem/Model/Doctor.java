package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Doctor extends User{
  public Doctor(String id, String username, String password) {
    super(id, username, password);
    this.setType(UserType.Doctor);
  }
}
