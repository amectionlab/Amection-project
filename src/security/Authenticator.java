package security;

import static controller.Program.db;
import model.Administrator;
import model.Dermatologist;

public final class Authenticator {
    
    private Authenticator() { }
    
    public static Boolean findForLogin(String userID, String password, boolean isAdmin) {

        if (isAdmin) {
            
            Administrator user = db.searchAdministrator(userID);

            if (user != null) {
                
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        else {
        
            Dermatologist user = db.searchDermatologist(userID);
            
            if (user != null) {
                
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
}
