package controller;

import static controller.Program.db;
import security.Authenticator;
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
import controller.utilities.FXUtils;
import javafx.scene.paint.Color;

public class LoginPanelController implements Initializable {
    
    /* Widgets asociados a tab de inicio de sesión */
    @FXML
    private TextField loginRut;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private Label loginErrorText;
    @FXML
    private CheckBox loginIsAdmin;
    
    /* Widgets asociados a tab de creación de cuenta */
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Designa cantidad maxima de caracteres
        FXUtils.addTextLimiter(loginRut, 10);
        FXUtils.addTextLimiter(loginPassword, 128);
        FXUtils.addTextLimiter(registerFirstName, 45);
        FXUtils.addTextLimiter(registerLastName, 45);
        FXUtils.addTextLimiter(registerRut, 10);
        FXUtils.addTextLimiter(registerEmail, 255);
        FXUtils.addTextLimiter(registerPassword, 128);
        FXUtils.addTextLimiter(registerRePassword, 128);
        
        //Activa etiqueta con politica de contraseña
        FXUtils.activateAlert(registerPasswordError, "Debe contener al menos:\n-Una minúscula\n-Una mayúscula\n-Un número\n-Largo mín. 8, máx 128 caracteres");
    }
    
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
                
                //
                Stage currentStage = (Stage) loginButton.getScene().getWindow();
                
                //Designa a cual escena se cambiará
                if (session.isAdminSession()) {
                    
                    //Cambia a panel de administrador
                    newScene(currentStage, "../view/adminPanel.fxml");
                }
                else {
                    
                    //Cambia a panel de dermatologo
                    newScene(currentStage, "../view/DermatologistPanel.fxml");
                }
            }
            else {
                FXUtils.activateAlert(loginErrorText, "Rut y/o contraseña incorrecto/s.");
            }
        //}
        //activateAlert(loginErrorText, "Ingrese un rut válido");

    }
    
    //Acción de botón de registro de usuario
    @FXML
    private void registerSubmit(ActionEvent e) throws IOException {

        //Inicializa checker, si todos los campos son 1 valida el registro.
        for (int i = 0; i < 6; i++){
            checker[i] = 0;
        }
        
        //Comprobación de nombres/apellidos y manipulación de errores
        checker[0] = Validator.checkNamesField(registerFirstName.getText());
        checker[1] = Validator.checkNamesField(registerLastName.getText());
        
        if (checker[0] == 1 && checker[1] == 1) {
            FXUtils.disableAlert(registerNameError);
        }
        else if (checker[0] == 0 || checker[1] == 0) {
            FXUtils.activateAlert(registerNameError, "Solo puede ingresar caracteres alfabéticos");
        }
        else if (checker[0] == -1 || checker[1] == -1) {
            FXUtils.activateAlert(registerNameError, "Uno de los campos no cumple el largo mínimo");
        }
        else if (checker[0] == -2 || checker[1] == -2) {
            FXUtils.activateAlert(registerNameError, "Uno de los campos está vacío");
        }
        
        //Comprobación de fecha y manipulación de errores
        if (registerBirthday.getValue() != null) {
            FXUtils.disableAlert(registerBirthdayError);
            checker[2] = 1;
        }
        else {
            FXUtils.activateAlert(registerBirthdayError, "Fecha invalida");
        }
        
        //Comprobación de rut y manipulación de errores
        checker[3] = Validator.checkRut(registerRut.getText());
        
        switch (checker[3]) {
            case 1:
                if (db.searchDermatologist(registerRut.getText()) != null) {
                    FXUtils.activateAlert(registerRutError, "Ya existe una cuenta con este rut");
                    checker[3] = -2; //Para invalidarlo
                }
                else {
                    FXUtils.disableAlert(registerRutError);
                }
                break;
            case 0:
                FXUtils.activateAlert(registerRutError, "Rut invalido");
                break;
            case -1:
                FXUtils.activateAlert(registerRutError, "Formato incorrecto");
                break;
            default:
                break;
        }
        
        //Comprueba correo electrónico
        checker[4] = Validator.checkEmail(registerEmail.getText());
        
        switch (checker[4]) {
            case 1:
                FXUtils.disableAlert(registerEmailError);
                break;
            case 0:
            case -1:
                FXUtils.activateAlert(registerEmailError, "Correo inválido");
                break;
            case -2:
                FXUtils.activateAlert(registerEmailError, "Campo obligatorio");
                break;
            default:
                break;
        }
        
        //Comprueba contraseñas ingresadas
        checker[5] = Validator.checkPasswordStrength(registerPassword.getText());
        
        switch (checker[5]) {
            case 1: //Contraseña admitida

                FXUtils.activateAlert(registerPasswordError, ""); //Se usa para ajuste de nodos (Responsive)
                
                //Comprueba que la contraseña re-ingresada no esté en blanco
                if (Validator.checkBlank(registerRePassword.getText())) {
                    
                    //Comprueba que ambas contraseñas sean iguales
                    if (Validator.checkEqualPasswords(registerPassword.getText(), registerRePassword.getText())) {
                        FXUtils.activateAlert(registerPasswordError, "");   //Se usa para ajuste de nodos (Responsive)
                        FXUtils.activateAlert(registerRePasswordError, ""); //Se usa para ajuste de nodos (Responsive)
                        checker[5] = 1;
                    }
                    else { //Contraseñas distintas
                        FXUtils.activateAlert(registerRePasswordError, "Las contraseñas no coinciden");
                        checker[5] = 0;
                    }
                }
                else {
                    FXUtils.activateAlert(registerRePasswordError, "Campo obligatorio");
                    checker[5] = 0;
                }
                
                break;
            case 0: //Contraseña debil
                FXUtils.activateAlert(registerPasswordError, "Debe contener al menos:\n-Una minúscula\n-Una mayúscula\n-Un número\n-Largo mín. 8, máx 128 caracteres");
                break;
            case -1: //Contraseña corta
                FXUtils.activateAlert(registerPasswordError, "Debe contener mínimo 8 caracteres");
                break;
            case -2: //Campo vacío
                FXUtils.activateAlert(registerPasswordError, "Campo obligatorio");
                break;
            default:
                break;
        }
        
        registerPasswordError.setTextFill(Color.web("#ff0000", 1.0));
        
        //Comprueba que todas las validaciones e ingreso de datos fue exitoso
        for (int i = 0; i < 6; i++) {
            
            if (checker[i] != 1) {
                return;
            }
        }
        
        //Crea nuevo usuario, genero, telefono y direccion en null por defecto (puede añadirlo en su perfil), actualiza bd
        db.addNew(1, registerFirstName.getText(), registerLastName.getText(), 
                     registerBirthday.getValue().toString(), registerRut.getText(), 
                     null, registerEmail.getText(), 
                     null, null, registerPassword.getText());
        
        //Retorna a panel de inicio de sesión
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        newScene(currentStage, "../view/loginPanel.fxml");
    }
    
    //Crea nueva escena con archivo FXML
    private void newScene(Stage currentStage, String path) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource(path));
        
        Scene scene = new Scene(root);
        
        currentStage.setScene(scene);
        //currentStage.sizeToScene();
        currentStage.setHeight(1200);
        currentStage.setHeight(800);
        currentStage.centerOnScreen();
        currentStage.show();
        currentStage.setResizable(true);
    }
}