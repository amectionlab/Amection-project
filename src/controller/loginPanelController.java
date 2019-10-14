package controller;

import security.Authenticator;
import static controller.Main.session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginPanelController implements Initializable {
    
    @FXML
    private TextField loginRut;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginErrorText;
    @FXML
    private CheckBox loginIsAdmin;
    
    //Acción de botón de inicio de sesión
    @FXML
    private void loginSubmit(ActionEvent e) throws IOException {
        
        //Realiza una autenticacion para verificar si el usuario existe.
        if (Authenticator.validate(loginRut.getText(), loginPassword.getText(), loginIsAdmin.isSelected())) {
            
            //Crea la sesión con el usuario encontrado
            session.startSession(loginRut.getText(), loginIsAdmin.isSelected());
            
            //Designa a cual escena se cambiará
            if (session.getIsAdminSession()) {
                
                //Cambia a panel de administrador
            }
            else {
               
                //Cambia a panel de dermatologo
            }
        } else {
            loginErrorText.setText("Rut y/o contraseña incorrecto/s.");
            loginErrorText.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
