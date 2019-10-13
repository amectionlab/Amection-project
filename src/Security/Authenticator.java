
package Security;

import static controller.Main.db;
import model.Administrator;
import model.Dermatologist;

public final class Authenticator {
    
    private Authenticator() {}
    
    public static Object validate(String userID, String password, boolean isAdmin) {

        if (isAdmin) {
            
            Administrator user = db.searchAdministrator(userID);

            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return (Administrator) user;
                }
            }
        }
        else {
        
            Dermatologist user = db.searchDermatologist(userID);
            
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return (Dermatologist) user;
                }
            }
            
        }
        return null;
    }
}
