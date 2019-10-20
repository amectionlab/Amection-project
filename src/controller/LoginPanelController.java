package controller;

import security.Authenticator;
import static controller.Program.db;
import static controller.Program.session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private TextField registerFirstName, registerLastName, registerRut, registerEmail;
    @FXML
    private PasswordField registerPassword, registerRePassword;
    @FXML
    private Label registerNameError, registerBirthdayError, registerRutError, registerEmailError, registerPasswordError, registerRePasswordError;
    @FXML
    private DatePicker registerBirthday;
    @FXML
    private Button loginButton;
    
    //Valida que registro de cuenta cumpla todos los requisitos
    private final int[] checker = new int[6];

    //Acción de botón de inicio de sesión
    @FXML
    private void loginSubmit(ActionEvent e) throws IOException {
        
        //int rutChecker  = Validator.checkRut(loginRut.getText());
        
        //Si el rut es valido realiza autenticación
        //if (rutChecker == 1) {
           
            //Realiza una autenticacion para verificar si el usuario existe.
            if (Authenticator.findForLogin(loginRut.getText(), loginPassword.getText(), loginIsAdmin.isSelected())) {

                //Crea la sesión con el usuario encontrado
                session.startSession(loginRut.getText(), loginIsAdmin.isSelected());

                //Designa a cual escena se cambiará
                if (session.isAdminSession()) {
                    
                    //Cambia a panel de administrador
                    Stage currentStage = (Stage) loginButton.getScene().getWindow();
                    newScene(currentStage, "../view/adminPanel.fxml");
                }
                else {
                }
            }
            else {
                activateAlert(loginErrorText, "Rut y/o contraseña incorrecto/s.");
            }
        //}
        //activateAlert(loginErrorText, "Ingrese un rut válido");

    }
    
    //Acción de botón de registro de usuario
    @FXML
    private void registerSubmit(ActionEvent e) {

        //Inicializa checker, si todos los campos son 1 valida el registro.
        for (int i = 0; i < 6; i++){
            checker[i] = 0;
        }
        
        //Comprobación de nombres/apellidos y manipulación de errores
        checker[0] = Validator.checkNamesField(registerFirstName.getText());
        checker[1] = Validator.checkNamesField(registerLastName.getText());
        
        if (checker[0] == 1 && checker[1] == 1) {
            disableAlert(registerNameError);
        }
        else if (checker[0] == 0 || checker[1] == 0) {
            activateAlert(registerNameError, "Solo puede ingresar caracteres alfabéticos");
        }
        else if (checker[0] == -1 || checker[1] == -1) {
            activateAlert(registerNameError, "Uno de los campos no cumple el largo mínimo");
        }
        else if (checker[0] == -2 || checker[1] == -2) {
            activateAlert(registerNameError, "Uno de los campos está vacío");
        }
        
        //Comprobación de fecha y manipulación de errores
        if (registerBirthday.getValue() != null) {
            disableAlert(registerBirthdayError);
            checker[2] = 1;
        }
        else {
            activateAlert(registerBirthdayError, "Fecha invalida");
        }
        
        //Comprobación de rut y manipulación de errores
        checker[3] = Validator.checkRut(registerRut.getText());
        
        switch (checker[3]) {
            case 1:
                disableAlert(registerRutError);
                break;
            case 0:
                activateAlert(registerRutError, "Rut invalido");
                break;
            case -1:
                activateAlert(registerRutError, "Formato incorrecto");
                break;
            default:
                break;
        }
        
        //Comprueba correo electrónico
        checker[4] = Validator.checkEmail(registerEmail.getText());
        
        switch (checker[4]) {
            case 1:
                disableAlert(registerEmailError);
                break;
            case 0:
            case -1:
                activateAlert(registerEmailError, "Correo inválido");
                break;
            case -2:
                activateAlert(registerEmailError, "Campo obligatorio");
                break;
            default:
                break;
        }
        
        //Comprueba contraseñas ingresadas
        checker[5] = Validator.checkPasswordStrength(registerPassword.getText());
        
        switch (checker[5]) {
            case 1: //Contraseña admitida

                disableAlert(registerPasswordError);
                
                //Comprueba que la contraseña re-ingresada no esté en blanco
                if (Validator.checkBlank(registerRePassword.getText())) {
                    activateAlert(registerRePasswordError, "Campo obligatorio");
                    checker[5] = 0;
                }
                //Comprueba que ambas contraseñas sean iguales
                else {
                    if (Validator.checkEqualPasswords(registerPassword.getText(), registerRePassword.getText())) {
                        checker[5] = 1;
                    }
                    else { //Contraseñas distintas
                        activateAlert(registerRePasswordError, "Las contraseñas no coinciden");
                        checker[5] = 0;
                    }
                }
                break;
            case 0: //Contraseña debil
                activateAlert(registerPasswordError, "Debe incluir al menos una mayúscula,\nuna minúscula y un número.");
                break;
            case -1: //Contraseña corta
                activateAlert(registerPasswordError, "Debe contener mínimo 8 caracteres");
                break;
            case -2: //Campo vacío
                activateAlert(registerPasswordError, "Campo obligatorio");
                break;
            default:
                break;
        }
        
        //Comprueba que todas las validaciones e ingreso de datos fue exitoso
        for (int i = 0; i < 8; i++) {
            
            if (checker[i] != 1) {
                return;
            }
        }
        
        //Accion de registro acá
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    //Crea nueva escena con archivo FXML
    private void newScene(Stage currentStage, String path) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource(path));
        
        Scene scene = new Scene(root);
        
        currentStage.setScene(scene);
        currentStage.setResizable(true);
        currentStage.sizeToScene();
        currentStage.centerOnScreen();
        currentStage.show();
        currentStage.setResizable(false);
    }
}