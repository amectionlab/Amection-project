package controller;

import security.Authenticator;
import static controller.Main.session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import security.Validator;

public class LoginPanelController implements Initializable {
    
    /* Widgets asociados a tab de inicio de sesión*/
    @FXML
    private TextField loginRut;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginErrorText;
    @FXML
    private CheckBox loginIsAdmin;
    
    /* Widgets y atributos asociados a tab de creación de cuenta*/
    @FXML 
    private TextField registerFirstName, registerLastName, registerRut, registerEmail, registerYear;
    @FXML
    private PasswordField registerPassword, registerRePassword;
    @FXML
    private Label registerNameError, registerBirthdayError, registerRutError, registerEmailError, registerPasswordError, registerRePasswordError;
    @FXML
    private ComboBox<String> registerMonth;
    @FXML
    private ComboBox<Integer> registerDay;
    
    //Valida que registro de cuenta cumpla todos los requisitos
    private final boolean[] checker = new boolean[9];
    
    
    //Items para ComboBoxes
    ObservableList<Integer> days = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                                                                    17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
    
    ObservableList<String> months = FXCollections.observableArrayList("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                                                                      "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
    
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
        } 
        else {
            
            loginErrorText.setText("Rut y/o contraseña incorrecto/s.");
            loginErrorText.setVisible(true);
        }
    }
    
    //Acción de botón de registro de usuario
    @FXML
    private void registerSubmit(ActionEvent e) {
        
        //Inicializa checker, si todos los campos son true valida el registro.
        for (int i = 0; i < 9; i++){
            checker[i] = false;
        }
        
        //Comprueba nombres y apellidos
        if (registerFirstName.getText().isBlank() || registerLastName.getText().isBlank()) {
            
            activateAlert(registerNameError, "Campo/s vacío/s");
        }
        else {
            
            //Marca checker como "true" y vuelve invisible error
            disableAlert(registerNameError);
            checker[0] = true;
            checker[1] = true;
        }
        
        //Comprueba fecha
        if (registerYear.getText().isBlank()) {
            
            activateAlert(registerBirthdayError, "Fecha invalida");
        }
        else {
            disableAlert(registerBirthdayError);
            checker[2] = true;
            checker[3] = true;
            checker[4] = true;
            
        }
        
        //Comprueba rut
        /*int response = Validator.rut(registerRut.getText());
        
        if (response == 1){
            disableAlert(registerRutError);
            checker[5] = true;
        }
        else if (response == 0){
            //Marca checker como "true" y vuelve invisible el error
            activateAlert(registerRutError, "Rut invalido");
        }
        else if (response == -1) {
            activateAlert(registerRutError, "Formato incorrecto");
        }*/
        
        
        
        //Comprueba correo electrónico
        if (registerEmail.getText().isBlank()) {
            activateAlert(registerEmailError, "Campo vacío");
        }
        else {
            disableAlert(registerEmailError);
            checker[6] = true;
        }
        
        //Comprueba contraseña
        if (registerPassword.getText().isBlank()) {
            activateAlert(registerPasswordError, "Campo vacío");
        }
        else {
            
            disableAlert(registerPasswordError);
            checker[7] = true;
        }
            
        //Comprueba re-ingreso de contraseña
        if (registerRePassword.getText().isBlank()) {
            activateAlert(registerRePasswordError, "Campo vacío");
            
        }
        else {
            disableAlert(registerRePasswordError);
            checker[8] = true;
        }
        
        for (int i = 0; i < 9; i++) {
            if (!checker[i]) {
                return;
            }
        }
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicializa ComboBoxes con fechas
        registerMonth.setItems(months);
        registerDay.setItems(days);
        
        //Inicializa largo máximo de entradas de texto
        loginRut.setOnKeyTyped(event -> {
            
            int maxCharacters = 15;
            if (loginRut.getText().length() > maxCharacters) {
                loginRut.setText(loginRut.getText());
            }
        });
        
    }
    
    
    //Cambia texto de label y lo hace visible
    private void activateAlert(Label label, String alertText) {
        
        label.setText(alertText);
        
        if (!label.isVisible()) {
            label.setVisible(true);
        }
    }
    
    //Desactiva alerta si es visible
    private void disableAlert(Label label) {
        if (label.isVisible()) {
            label.setVisible(false);
        }
        
    }
    
}
