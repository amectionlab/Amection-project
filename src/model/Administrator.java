package model;

public class Administrator extends Person {

    private String password;
    
    public Administrator(String firstname, String lastname, String date, String rut, String gender, String mail, String phoneNumber, String address, String password) {
        super(firstname, lastname, date, rut, gender, mail, phoneNumber, address);
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
