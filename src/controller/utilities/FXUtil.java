/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.utilities;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author bkdestroy
 */
public final class FXUtil {
    
    public FXUtil() {}
    
    //Cambia texto de label y lo hace visible
    public static void activateAlert(Label label, String alertText) {
        
        label.setText(alertText);
        
        if (!label.isVisible()) {
            label.setVisible(true);
        }
    }
    
    //Desactiva alerta si es visible
    public static void disableAlert(Label label) {
        if (label.isVisible()) {
            label.setVisible(false);
        }
        
    }
    
    //Designa limite a entradas de texto
    public static void addTextLimiter(final TextField textField, final int maxLength) {
        
        textField.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
            
            if (textField.getText().length() > maxLength) {
                String s = textField.getText().substring(0, maxLength);
                textField.setText(s);
            }
        });
    }
     
}
