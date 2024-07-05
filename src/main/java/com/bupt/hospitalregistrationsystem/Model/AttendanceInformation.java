package com.bupt.hospitalregistrationsystem.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 出诊信息
 */
@Document
public class AttendanceInformation {
  @Id
  private String id;
  private String doctorUsername;  // username 唯一， 暂时以email来填
  private String startTime;
  private String endTime;
  private int maxCnt;
  private int cnt;  // 出诊次数
  private ArrayList<User> patients;
  private boolean checked;
  private User checker;
  private boolean pass;
  private User doctor;

  @JsonCreator
  public AttendanceInformation() {
    this.id = UUID.randomUUID().toString();
    this.patients = new ArrayList<>();
  }

  public User getDoctor() {
    return doctor;
  }

  public void setDoctor(User doctor) {
    this.doctor = doctor;
  }

  public User getChecker() {
    return checker;
  }

  public void setChecker(User checker) {
    this.checker = checker;
  }

  public boolean isPass() {
    return pass;
  }

  public void setPass(boolean pass) {
    this.pass = pass;
  }

  public boolean isChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

  public void addPatient(User patient) {
    this.patients.add(patient);
  }
  public int getMaxCnt() {
    return maxCnt;
  }

  public void setMaxCnt(int maxCnt) {
    this.maxCnt = maxCnt;
  }

  public ArrayList<User> getPatients() {
    return patients;
  }

  public void setPatients(ArrayList<User> patients) {
    this.patients = patients;
  }

  public String getDoctorUsername() {
    return doctorUsername;
  }

  public void setDoctorUsername(String doctorUsername) {
    this.doctorUsername = doctorUsername;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
}
