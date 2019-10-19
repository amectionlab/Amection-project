package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class AdminPanelController implements Initializable {

    @FXML
    private Button profileButton, statisticsButcton, logsButton;
    @FXML
    private MenuItem adminsButton, dermsButton, patientsButton;
    @FXML
    private AnchorPane profilePane, adminsPane, dermsPane, patientsPane, StatisticsPane, logsPane;
    
    DisplayPanel dPanel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        dPanel = new DisplayPanel();
        dPanel.insertDisplay(profilePane);
        dPanel.insertDisplay(adminsPane);
        dPanel.insertDisplay(dermsPane);
        dPanel.insertDisplay(patientsPane);
        dPanel.insertDisplay(StatisticsPane);
        dPanel.insertDisplay(logsPane);
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        
        if (event.getSource() == profileButton) {
            dPanel.activateDisplay(0);
        }
        else if (event.getSource() == logsButton) {
            dPanel.activateDisplay(1);
        }
         
    }

}
