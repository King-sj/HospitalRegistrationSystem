package com.bupt.hospitalregistrationsystem.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class User {
  @Id
  private String id;
  private UserType type;
  private String username;
  private String password;
  private String identity;  // 身份证号
  private String name;  // 姓名
  private int age;  // 年龄
  private String email;  // 邮箱
  private String phone;  // 联系方式
  private String address;  // 住址
  private Gender gender;  // 性别

  @JsonCreator
  public User(
          @JsonProperty("id") String id,
          @JsonProperty("username") String username,
          @JsonProperty("password") String password
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.type = UserType.User;
    this.gender = Gender.MALE;
  }

  public UserType getType() {
    return type;
  }

  public void setType(UserType type) {
    this.type = type;
  }

  public String getIdentity() {
    return identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
