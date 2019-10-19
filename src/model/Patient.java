package model;

public class Patient extends Person{
      
    private String healthCoverage;
    private String nameHealthCoverage;
    
    public Patient(String firstname, String lastname, String date, String rut, String gender, String mail, String phoneNumber, String address) {
        super(firstname, lastname, date, rut, gender, mail, phoneNumber, address);
    }
    
    public Patient(String firstname, String lastname, String date, String rut, String gender, String mail, String phoneNumber, String address, String healthCoverage, String nameHealthCoverage) {
        super(firstname, lastname, date, rut, gender, mail, phoneNumber, address);
        this.healthCoverage = healthCoverage;
        this.nameHealthCoverage = nameHealthCoverage;
    }

    public String getHealthCoverage() {
        return healthCoverage;
    }

    public String getNameHealthCoverage() {
        return nameHealthCoverage;
    }

    public void setHealthCoverage(String healthCoverage) {
        this.healthCoverage = healthCoverage;
    }
    
    public void setNameHealthCoverage(String nameHealthCoverage) {
        this.nameHealthCoverage = nameHealthCoverage;
    }
   
}
