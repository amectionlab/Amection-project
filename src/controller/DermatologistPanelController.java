/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.Program.session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Dermatologist;

/**
 *
 * @author ecinai
 */
public class DermatologistPanelController implements Initializable{
    
    // Widgets asociados a VBox
    @FXML
    private Button profileButton;

    @FXML
    private Button patientsButton;

    @FXML
    private Button scheduleButton;

    @FXML
    private Button reportsButton;

    @FXML
    private AnchorPane patientsPane;

    @FXML
    private AnchorPane schedulePane;

    @FXML
    private AnchorPane reportsPane;

    @FXML
    private AnchorPane profilePane;
    
    DisplayPanel displayPanel; 
    
    
    // Widgets asociados a profilePane
    @FXML
    private GridPane gridPaneProfile;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldRut;

    @FXML
    private TextField textFieldDate;

    @FXML
    private TextField textFieldGender;

    @FXML
    private TextField textFieldMail;

    @FXML
    private TextField textFieldPhone;

    @FXML
    private TextField textFieldAddress;
    
    @FXML
    private MenuButton menuButton;

    


    @FXML
    private void buttonActivation(ActionEvent event) {
        if(event.getSource() == profileButton) {
            displayPanel.activateDisplay(0);
            
        }else  if(event.getSource() == patientsButton) {
            displayPanel.activateDisplay(1);
             
        }else  if(event.getSource() == scheduleButton) {
            displayPanel.activateDisplay(2);
        
        }else  if(event.getSource() == reportsButton) {
            displayPanel.activateDisplay(3);
        
        }
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayPanel = new DisplayPanel();
        displayPanel.insertDisplay(profilePane);
        displayPanel.insertDisplay(patientsPane);
        displayPanel.insertDisplay(schedulePane);
        displayPanel.insertDisplay(reportsPane);
        
        Dermatologist user = (Dermatologist) session.getLoggedUser();
        textFieldRut.setText(user.getRut());
        textFieldFirstName.setText(user.getFirstname());
        textFieldLastName.setText(user.getLastname());
        textFieldDate.setText(user.getDate());
        textFieldGender.setText(user.getGender());
        textFieldMail.setText(user.getEmail());
        textFieldPhone.setText(user.getPhoneNumber());
        textFieldAddress.setText(user.getAddress());
        menuButton.setText(user.getFirstname());
    }
    
}
