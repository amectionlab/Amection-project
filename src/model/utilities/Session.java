package model.utilities;

import static controller.Program.db;
import model.Administrator;
import model.Dermatologist;

public class Session {
    
    private Object loggedUser;
    private boolean isAdminSession; //Tipo de sesion
    
    public Object getLoggedUser() {
        
        if (this.isAdminSession) {
            return (Administrator) loggedUser;
        } 
        else if (!this.isAdminSession) {
            return (Dermatologist) loggedUser;
        }
        else {
            return null;
        }
    }
    
    public void startSession(String rut, boolean isAdmin) {
        
        if (isAdmin) {
            setIsAdminSession(isAdmin);
            loggedUser = db.searchAdministrator(rut);
        }
        else {
            setIsAdminSession(isAdmin);
            loggedUser = db.searchDermatologist(rut);
        }
    }
    
    public void setLoggedUser(Object user) {
            this.loggedUser = user;
    }
    
    //Retorna el tipo de sesión (administrador o dermatologo)
    public boolean isAdminSession() {
        return isAdminSession;
    }
    
    //Establece el tipo de sesión (administrador o dermatologo)
    public void setIsAdminSession(boolean isAdmin) {
        this.isAdminSession = isAdmin;
    }
    
}
