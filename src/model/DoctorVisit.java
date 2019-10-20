/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @
 */
public class DoctorVisit {
    
    private String date;
    private String diagnosis;
    private String localization;
    // falta formato de la imágen

    public DoctorVisit(String date, String diagnosis, String localization) {
        this.date = date;
        this.diagnosis = diagnosis;
        this.localization = localization;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }
    
}
