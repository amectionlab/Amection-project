package controller;

import com.gluonhq.charm.glisten.control.TextField;
import static controller.Main.db;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import model.Dermatologist;

public class loginPanelController implements Initializable {
    
    @FXML
    private TextField loginRut;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginErrorText;
    
    
    //Acción de botón de inicio de sesión
    @FXML
    private void submit(ActionEvent e) {
        
        
        Dermatologist user = db.searchDermatologist(loginRut.getText());
        
        if (user != null) {
            
            if (loginErrorText.isVisible()) {
                loginErrorText.setVisible(false);
            }
            
            //Se logea usuario
        }
        else {
            loginErrorText.setText("Rut y/o contraseña incorrectos.");
            loginErrorText.setVisible(true);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
}
