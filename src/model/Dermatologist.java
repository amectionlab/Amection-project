package model;

import model.collections.PatientCollection;

public class Dermatologist extends Person{
    
    private String password;
    private final PatientCollection patients;

    public Dermatologist(String firstname, String lastname, String date, String rut, String gender, String mail, String phoneNumber, String address, String password) {
        
        super(firstname, lastname, date, rut, gender, mail, phoneNumber, address);
        this.password = password;
        this.patients = new PatientCollection();
    }
    
    /* Añade paciente a colección asociada a dermatologo
     * Param 1: Cadena con el rut del paciente como key para el mapa
     * Param 2: Objeto paciente que será añadido a la colección
     * return: true si la operación se realiza con exito
     *         false si la operacion falla
    */
    public boolean addPatient(String rut, Patient patient) {
        return patients.addToCollection(rut, patient);
    }
    
    /* Añade paciente a colección asociada a dermatologo
     * Param 1: Cadena con el rut del paciente como key para el mapa
     * Param 2: Objeto paciente que será eliminado de la colección
     * return: true si la operación se realiza con exito
     *         false si el paciente no existe o ocurre un error.
    */
    public boolean deletePatient(String rut) {
        return patients.removeFromCollection(rut);
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
