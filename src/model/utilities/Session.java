package model.utilities;

import model.Administrator;
import model.Dermatologist;

public class Session {
    
    private Object loggedUser;
    private int sessionType;
    
    public Object getLoggedUser() {
        
        if (this.sessionType == 0) {
            return (Dermatologist) loggedUser;
        } 
        else if (this.sessionType == 1) {
            return (Administrator) loggedUser;
        }
        else {
            return null;
        }
        
    }
    
    public void setLoggedUser(Object user, int sessionType) {
            this.loggedUser = user;
            this.sessionType = sessionType;
    }
    
    //Retorna el tipo de sesión (administrador o dermatologo)
    public int getSessionType() {
        return sessionType;
    }
    
    //Establece el tipo de sesión (administrador o dermatologo)
    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }
    
}
