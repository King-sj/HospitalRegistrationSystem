package com.bupt.hospitalregistrationsystem.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Patient extends User {
  private final ArrayList<MedicalRecordItem> medicalRecord;
  public Patient(String id, String username, String password) {
    super(id, username, password);
    this.setType(UserType.Patient);
    this.medicalRecord = new ArrayList<>();
  }

  public ArrayList<MedicalRecordItem> getMedicalRecord() {
    return medicalRecord;
  }

  public void addMedicalRecord(MedicalRecordItem medicalRecordItem) {
    this.medicalRecord.add(medicalRecordItem);
  }
}
