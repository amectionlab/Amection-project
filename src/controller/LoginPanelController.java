package controller;

import security.Authenticator;
import static controller.Main.session;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
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
    private TextField registerFirstName, registerLastName, registerRut, registerEmail;
    @FXML
    private PasswordField registerPassword, registerRePassword;
    @FXML
    private Label registerNameError, registerBirthdayError, registerRutError, registerEmailError, registerPasswordError, registerRePasswordError;
    @FXML
    private DatePicker registerBirthday;
    
    //Valida que registro de cuenta cumpla todos los requisitos
    private final int[] checker = new int[8];

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
        
        /*  checker[0] = firstname
            checker[1] = lastname
            checker[2] = birthday
            checker[3] = rut
            checker[4] = email
            checker[5] = password && rePassword
        */
        
        //Inicializa checker, si todos los campos son 1 valida el registro.
        for (int i = 0; i < 8; i++){
            checker[i] = 0;
        }
        
        //Comprueba nombres y apellidos
        if (registerFirstName.getText().isBlank() || registerLastName.getText().isBlank()) {
            
            activateAlert(registerNameError, "Campo/s vacío/s");
        }
        else {
            
            //Marca checker como "true" y vuelve invisible error
            disableAlert(registerNameError);
            checker[0] = 1;
            checker[1] = 1;
        }
        
        //Comprueba fecha
        if (registerBirthday.getValue() != null) {
               disableAlert(registerBirthdayError);
        }
        else {
            activateAlert(registerBirthdayError, "Fecha invalida");
        }
        
        //Comprueba rut
        checker[3] = 1;
        int response = Validator.rut(registerRut.getText());
        
        switch (response) {
            case 1:
                disableAlert(registerRutError);
                checker[3] = 1;
                break;
            case 0:
                //Marca checker como "true" y vuelve invisible el error
                activateAlert(registerRutError, "Rut invalido");
                break;
            case -1:
                activateAlert(registerRutError, "Formato incorrecto");
                break;
            default:
                break;
        }
        
        //Comprueba correo electrónico
        if (registerEmail.getText().isBlank()) {
            activateAlert(registerEmailError, "Campo obligatorio");
        }
        else {
            disableAlert(registerEmailError);
            checker[4] = 1;
        }
        
        //Comprueba contraseñas ingresadas
        checker[5] = Validator.passwordStrength(registerPassword.getText());
        
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
}
