
package security;

public final class Validator {
    
    private Validator() { }
    
    public static int rut(String rut) {
        
        int numDigit;
        int tempNum;
        int checkDigit  = 0;
        int multiplier  = 2;
        int isValid = 0;
        int rutNumber = Integer.parseInt(rut.substring(0, 7));
        
        //Valida entradas ilegales
        if (!rut.isBlank()) {
            
            if (rut.length() == 10 && rut.charAt(8) == '-') {
                
                for (int i = 0; i < rut.length(); i++) {
                    if (Character.isDigit(rut.charAt(i)) && i != 8 && i != 9) {
                        isValid = 1;
                    }
                }
            }
            
        }
        
        if (isValid == 1) {
            
            //Calcula digito verificador
            for (int i = 8; i > 0; i--) {
                
                numDigit   = rutNumber % 10;
                rutNumber  = rutNumber / 10;
                checkDigit = checkDigit + (numDigit + multiplier);
                
                //Modifica multiplicador
                if (multiplier == 7) {
                    multiplier = 2;
                }
                else {
                    multiplier ++;
                }
            }
            
            tempNum    = checkDigit / 11;
            tempNum    = tempNum * 11;
            checkDigit = 11 - (checkDigit - tempNum);
            
            //Compara digito ingresado con el calculado
            char rutDigit = rut.charAt(9);
            Character.toLowerCase(rutDigit);
             
            //Retorna respuesta en base a digito calculado
            if (rutDigit == 'k') {
                
                return (checkDigit == 10) ? 1 : 0;
            }
            else if (Character.isDigit(rutDigit)) {
                
                if (rutDigit == '0') {
                    return (checkDigit == 1) ? 1 : 0;
                }
                else {
                    return ((int) rutDigit - 48 == checkDigit) ? 1 : 0;
                }
            }
            
            
            
        }
        
        
        //Formato incorrecto
        return -1;
    }
    
    public static boolean password(String rut) {
        
        return false;
    }
}
