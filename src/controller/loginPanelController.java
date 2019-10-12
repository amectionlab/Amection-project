package controller;

import com.gluonhq.charm.glisten.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;

public class loginPanelController implements Initializable {
    
    @FXML
    private TextField loginRut;
    @FXML
    private PasswordField loginPassword;
    
    @FXML
    private void submit(ActionEvent e) {
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
}
