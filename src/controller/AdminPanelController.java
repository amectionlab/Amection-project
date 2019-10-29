package controller;

import static controller.Program.db;
import static controller.Program.session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import controller.utilities.FXUtil;
import javafx.scene.control.MenuButton;
import model.Administrator;
import model.Dermatologist;
import model.Patient;

public class AdminPanelController implements Initializable {
    
    //Contenido cbSearchType
    ObservableList<String> searchTypeList = (FXCollections.observableArrayList("Administrador", "Dermatólogo", "Paciente"));
    
    //PANEL DE PERFIL
    @FXML
    private Button profileButton, statisticsButton, logsButton, searchButton;
    @FXML
    private MenuItem adminsButton, dermsButton, patientsButton;
    @FXML
    private AnchorPane profilePane, adminsPane, dermsPane, patientsPane, StatisticsPane, logsPane, schGeneralPane, schNotFoundPane;
    @FXML
    private MenuButton welcomeLabel;
    @FXML
    private TextField fullnameField, genderField, rutField, dateField, addressField, phoneField, emailField, searchField;
    @FXML
    private PasswordField passwordField, rePasswordField;
    @FXML
    private ComboBox cbSearchType;
    @FXML
    private Label passwordErrorLabel;
    
    // PANEL DE BUSQUEDA
    @FXML
    private Label schLabel, schPasswordError, schRePasswordError;
    @FXML
    private TextField schFullnameField, schGenderField, schRutField, schDateField, schAddressField, schPhoneField, schEmailField;
    @FXML
    private PasswordField schPasswordField, schRePasswordField;
    
    //Organizador de paneles
    DisplayPanel dPanel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Ingreso de items a ComboBoxes
        cbSearchType.setItems(searchTypeList);
        
        //Cambio de anchorPanes
        dPanel = new DisplayPanel();
        dPanel.insertDisplay(profilePane);
        dPanel.insertDisplay(adminsPane);
        dPanel.insertDisplay(dermsPane);
        dPanel.insertDisplay(patientsPane);
        dPanel.insertDisplay(StatisticsPane);
        dPanel.insertDisplay(logsPane);
        dPanel.insertDisplay(schGeneralPane);
        dPanel.insertDisplay(schNotFoundPane);
        
        //Inicialización de labels
        //FXUtil.activateAlert(passwordErrorLabel, "Debe contener al menos:\n-Una minúscula\n-Una mayúscula\n-Un número\n-Largo mín. 8, máx 128 caracteres");
        //FXUtil.activateAlert(schPasswordError, "Debe contener al menos:\n-Una minúscula\n-Una mayúscula\n-Un número\n-Largo mín. 8, máx 128 caracteres");
        
        //Obtiene objeto usuario conectado actualmente
        Administrator user = (Administrator) session.getLoggedUser();
        
        //Información del usuario
        fullnameField.setText(user.getFirstname() + " " + user.getLastname());
        genderField.setText(user.getGender());
        rutField.setText(user.getRut());
        dateField.setText(user.getDate());
        addressField.setText(user.getAddress());
        phoneField.setText(user.getPhoneNumber());
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
    
    @FXML
    public void searchByRut(ActionEvent event) {
        
        String rut = searchField.getText();
        
        /* NOTA: Quitar etiqueta de comentario una vez 
                 se tenga una base de dato con rut reales
        
        //Valida que se ingrese rut válido
        if (Validator.checkRut(rut) != 1) {
            System.out.println(Validator.checkRut(rut));
            searchField.setText("");
            searchField.setPromptText("Ingrese un rut válido");
            return;
        } */
        
        //Comprueba que se haya seleccionado un item
        if (cbSearchType.getValue() != null) {
            
            searchField.setText("");
            
            //Transforma item seleccionado a una cadena
            String searchType = cbSearchType.getValue().toString();
        
            if (searchType.equals("Administrador")) {
                
                Administrator administrator = db.searchAdministrator(rut);
                
                if (administrator != null ) {
                    
                    schLabel.setText("Administrador encontrado");
                    
                    //Carga datos de usuario en el panel de busqueda
                    schFullnameField.setText(administrator.getFirstname() + " " + administrator.getLastname());
                    schGenderField.setText(administrator.getGender());
                    schRutField.setText(administrator.getRut());
                    schDateField.setText(administrator.getDate());
                    schAddressField.setText(administrator.getAddress());
                    schPhoneField.setText(administrator.getPhoneNumber());
                    schEmailField.setText(administrator.getEmail());
                    
                    //Activa panel de busqueda//Datos personales
                    dPanel.activateDisplay(6);
                }
                else {
                    //Panel de error: usuario no encontrado
                    dPanel.activateDisplay(7);
                }
            }
            else if (searchType.equals("Dermatólogo")) {
                
                Dermatologist dermatologist = db.searchDermatologist(rut);
                
                if (dermatologist != null) {
                    
                    schLabel.setText("Dermatólogo encontrado");
                    
                    //Carga datos de usuario en el panel de busqueda
                    schFullnameField.setText(dermatologist.getFirstname() + " " + dermatologist.getLastname());
                    schGenderField.setText(dermatologist.getGender());
                    schRutField.setText(dermatologist.getRut());
                    schDateField.setText(dermatologist.getDate());
                    schAddressField.setText(dermatologist.getAddress());
                    schPhoneField.setText(dermatologist.getPhoneNumber());
                    schEmailField.setText(dermatologist.getEmail());
                    
                    //Activa panel de busqueda
                    dPanel.activateDisplay(6);
                }
                else {
                    //Panel de error: usuario no encontrado
                    dPanel.activateDisplay(7);
                }
                
            }
            else if (searchType.equals("Paciente")) {
                
                
                Patient patient = db.searchPatient(rut);
                
                if (patient != null) {
                
                    //Carga datos de usuario en el panel de busqueda
                    
                    dPanel.activateDisplay(6);
                }
                else {
                    //Panel de error: usuario no encontrado
                    dPanel.activateDisplay(7);
                }
            }
        }
        else {
            searchField.setText("");
            searchField.setPromptText("Elija una opción de busqueda");
        }
    }
}

