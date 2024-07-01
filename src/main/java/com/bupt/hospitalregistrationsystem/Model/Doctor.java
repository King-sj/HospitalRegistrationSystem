package com.bupt.hospitalregistrationsystem.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Doctor extends User{
  @JsonCreator
  public Doctor(
          @JsonProperty("id") String id,
          @JsonProperty("username") String username,
          @JsonProperty("password") String password
  ) {
    super(id, username, password);
    this.setType(UserType.Doctor);
  }
}
