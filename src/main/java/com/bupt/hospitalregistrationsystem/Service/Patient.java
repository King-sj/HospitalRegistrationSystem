package com.bupt.hospitalregistrationsystem.Service;

import java.util.ArrayList;
import java.util.List;

public class Patient
{
    private String name;
    private String gender;
    private int age;
    private String address;
    private String ID;
    private String phone;
    private MedicalRecord medicalRecord;
    Patient(String name, String gender, int age, String address, String ID, String phone, MedicalRecord medicalRecord)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.ID = ID;
        this.phone = phone;
        this.medicalRecord = medicalRecord;
    }
    String getName(){return name;}
    String getGender(){return gender;}
    int getAge(){return age;}
    String getAddress(){return address;}
    String getID(){return ID;}
    String getPhone(){return phone;}
    MedicalRecord getMedicalRecord(){return medicalRecord;}
    void setName(String name){this.name = name;}
    void setGender(String gender){this.gender = gender;}
    void setAge(int age){this.age = age;}
    void setAddress(String address){this.address = address;}
    void setID(String ID){this.ID = ID;}
    void setPhone(String phone){this.phone = phone;}
    void setMedicalRecord(MedicalRecord medicalRecord){this.medicalRecord = medicalRecord;}
}

class MedicalRecord
{
    private List<Record> records;
    private Patient patient;
    List<Record> getRecords() {return records;}
    void setRecords(List<Record> records) {this.records = records;}
    MedicalRecord() {records = new ArrayList<Record>();}
    MedicalRecord(Patient p){patient=p;records = new ArrayList<>();}
    Patient getPatient() {return patient;}
    void setPatient(Patient patient) {this.patient = patient;}
}

class Record
{
    private String time;
    private Patient patient;
    private Doctor doctor;
    private String result;
    private String illness;
    Record(String time, Patient patient, Doctor doctor, String result, String illness)
    {
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
        this.result = result;
        this.illness = illness;
    }
    String getTime() {return time;}
    void setTime(String time) {this.time = time;}
    Patient getPatient() {return patient;}
    void setPatient(Patient patient) {this.patient = patient;}
    Doctor getDoctor() {return doctor;}
    void setDoctor(Doctor doctor) {this.doctor = doctor;}
    String getResult() {return result;}
    void setResult(String result) {this.result = result;}
    String getIllness() {return illness;}
    void setIllness(String illness) {this.illness = illness;}
}


