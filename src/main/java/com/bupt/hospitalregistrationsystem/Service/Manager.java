package com.bupt.hospitalregistrationsystem.Service;

public class Manager
{
    private String ID;
    private String Name;
    private String Address;
    private String Phone;
    String getName(){return this.Name;}
    String getAddress(){return this.Address;}
    String getPhone(){return this.Phone;}
    String getID(){return this.ID;}
    void setID(String ID){this.ID=ID;}
    void setName(String Name){this.Name=Name;}
    void setAddress(String Address){this.Address=Address;}
    void setPhone(String Phone){this.Phone=Phone;}
}
