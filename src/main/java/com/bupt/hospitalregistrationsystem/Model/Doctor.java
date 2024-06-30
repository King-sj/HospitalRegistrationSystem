package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Doctor extends User{
  public String Hospital, department, title, specialty;  // 所在医院、科室、职称、专长
  public Doctor(String id, String username, String password) {
    super(id, username, password);
    this.setType(UserType.Doctor);
  }

  public String getHospital() {
    return Hospital;
  }

  public void setHospital(String hospital) {
    Hospital = hospital;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSpecialty() {
    return specialty;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }
}
