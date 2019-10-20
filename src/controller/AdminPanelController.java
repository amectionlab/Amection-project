package controller;

import static controller.Program.session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Administrator;

public class AdminPanelController implements Initializable {

    @FXML
    private Button profileButton, statisticsButcton, logsButton;
    @FXML
    private MenuItem adminsButton, dermsButton, patientsButton;
    @FXML
    private AnchorPane profilePane, adminsPane, dermsPane, patientsPane, StatisticsPane, logsPane;
    @FXML
    private SplitMenuButton welcomeLabel;
    @FXML
    private TextField fullnameField, genderField, rutField, dateField, addressField, phoneField, emailField;
    @FXML
    private PasswordField passwordField, rePasswordField;
            
    DisplayPanel dPanel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Cambio de anchorPanes
        dPanel = new DisplayPanel();
        dPanel.insertDisplay(profilePane);
        dPanel.insertDisplay(adminsPane);
        dPanel.insertDisplay(dermsPane);
        dPanel.insertDisplay(patientsPane);
        dPanel.insertDisplay(StatisticsPane);
        dPanel.insertDisplay(logsPane);
        
        //Obtiene objeto usuario conectado actualmente
        Administrator user = (Administrator) session.getLoggedUser();
        
        //Datos personales
        fullnameField.setText(user.getFirstname() + " " + user.getLastname());
        genderField.setText(user.getGender());
        rutField.setText(user.getRut());
        dateField.setText(user.getDate());
        addressField.setText(user.getAddress());
        phoneField.setText(user.getPhoneNumber());
        
        //Información de cuenta
        emailField.setText(user.getEmail());
    }
    
    @FXML
    public void changeViewHandle(ActionEvent event) {
        
        if (event.getSource() == profileButton) {
            dPanel.activateDisplay(0);
        }
        else if (event.getSource() == adminsButton) {
            dPanel.activateDisplay(1);
        }
        else if (event.getSource() == dermsButton) {
            dPanel.activateDisplay(2);
        }
        else if (event.getSource() == patientsButton) {
            dPanel.activateDisplay(3);
        }
    }
}
