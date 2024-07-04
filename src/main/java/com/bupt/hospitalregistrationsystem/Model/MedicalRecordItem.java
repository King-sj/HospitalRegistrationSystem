package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class MedicalRecordItem {
  private Date date;  // 日期
  private Doctor doctor;  // 主治医师
  private String complaint;  // 述求
  private String examination;  // 检查结果
  private String physicianOrders;  // 医嘱

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Doctor getDoctor() {
    return doctor;
  }

  public void setDoctor(Doctor doctor) {
    this.doctor = doctor;
  }

  public String getComplaint() {
    return complaint;
  }

  public void setComplaint(String complaint) {
    this.complaint = complaint;
  }

  public String getExamination() {
    return examination;
  }

  public void setExamination(String examination) {
    this.examination = examination;
  }

  public String getPhysicianOrders() {
    return physicianOrders;
  }

  public void setPhysicianOrders(String physicianOrders) {
    this.physicianOrders = physicianOrders;
  }
}
