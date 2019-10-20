/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.collections;

import java.util.Stack;
import model.DoctorVisit;

/**
 *
 * @author ecinai
 */
public class DoctorVisitCollection {
    
    Stack<DoctorVisit> doctorVisits;

    public DoctorVisitCollection(Stack<DoctorVisit> doctorVisit) {
        this.doctorVisits = new Stack<>();
    }
    
    public void addVisit(DoctorVisit doctorVisit){
        doctorVisits.push(doctorVisit);
    }
    
    public DoctorVisit getDoctorVisit(){
        return this.doctorVisits.peek();
    }
}
