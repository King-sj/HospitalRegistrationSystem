package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin extends User{
  public Admin(String id, String username, String password) {
    super(id, username, password);
    this.setType(UserType.Administrator);
  }
}
