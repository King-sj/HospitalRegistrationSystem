package com.bupt.hospitalregistrationsystem.Service;

public class Doctor
{
    private String name;
    private String ID;
    private String address;
    private int age;
    private String gender;
    private String phone;
    private String hospitalName;
    private String departmentName;
    private String speciality;
    private String job;
    String getName(){return this.name;}
    String getID(){return this.ID;}
    String getAddress(){return this.address;}
    int getAge(){return this.age;}
    String getGender(){return this.gender;}
    String getPhone(){return this.phone;}
    String getHospitalName(){return this.hospitalName;}
    String getDepartmentName(){return this.departmentName;}
    String getSpeciality(){return this.speciality;}
    String getJob(){return this.job;}
    void setName(String name){this.name=name;}
    void setID(String ID){this.ID=ID;}
    void setAddress(String address){this.address=address;}
    void setAge(int age){this.age=age;}
    void setGender(String gender){this.gender=gender;}
    void setPhone(String phone){this.phone=phone;}
    void setHospitalName(String hospitalName){this.hospitalName=hospitalName;}
    void setDepartmentName(String departmentName){this.departmentName=departmentName;}
    void setSpeciality(String speciality){this.speciality=speciality;}
    void setJob(String job){this.job=job;}
    public Doctor(String name,String ID,String address,int age,String gender,String phone,String hospitalName,String departmentName,String speciality,String job)
    {
        this.name=name;
        this.ID=ID;
        this.address=address;
        this.gender = gender;
        this.phone=phone;
        this.hospitalName=hospitalName;
        this.departmentName=departmentName;
        this.speciality=speciality;
        this.job=job;
        this.age = age;
    }
}
