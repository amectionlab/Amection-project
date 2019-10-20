package model;

abstract class Person {
        
    private String firstname;
    private String lastname;
    private String date;
    private String rut;
    private String gender;
    private String mail;
    private String phoneNumber;
    private String address;
    
    public Person(String rut, String mail) {
        this.firstname = null;
        this.lastname = null;
        this.date = null;
        this.rut  = rut;
        this.gender = null;
        this.mail = mail;
        this.phoneNumber = null;
        this.address = null;
    }
    
    public Person(String firstname, String lastname, String date, String rut, String gender, String mail, String phoneNumber, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.date = date;
        this.rut = rut;
        this.gender = gender;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.address = address;
    } 
            
    public String getFirstname() {
        return firstname;
    }
    
    
    public String getLastname() {
        return lastname;
    }
    
    
    public String getDate() {
        return date;
    }
    
    public String getRut() {
        return rut;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getEmail() {
        return mail;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setFirstame(String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastame(String lastname) {
        this.lastname = lastname;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public void setRut(String rut) {
        this.rut = rut;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setEmail(String mail) {
        this.mail = mail;
    }
    
    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}