package com.example.tute05;

public class Student {
    private String ID ;
    private String Name ;
    private String Address;
    private int Contact_No;

    public Student( ) {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getContact_No() {
        return Contact_No;
    }

    public void setContact_No(int contact_No) {
        Contact_No = contact_No;
    }
}
