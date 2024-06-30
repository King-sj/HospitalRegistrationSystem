package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class MedicalRecordItem {
  private Date date;  // 日期
  private Doctor doctor;  // 主治医师
  private String Complaint;  // 述求
  private String Examination;  // 检查结果
  private String PhysicianOrders;  // 医嘱
}
