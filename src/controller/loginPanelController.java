package controller;

import security.Authenticator;
import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;

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
    private void submit(ActionEvent e) {
        
        if (Authenticator.validate(loginRut.getText(), loginPassword.getText(), loginIsAdmin.isSelected()) != null) {
            System.out.println("Existe");
        } else {
            loginErrorText.setText("Rut y/o contraseña incorrecto/s.");
            loginErrorText.setVisible(true);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
