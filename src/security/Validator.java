
package security;

public final class Validator {
    
    private Validator() { }
    
    //En construcción
    public static int rut(String rut) {
        
        int numDigit;
        int tempNum;
        int checkDigit  = 0;
        int multiplier  = 2;
        boolean isValid = false;
        int rutNumber;
        
        //Valida entradas ilegales
        if (!rut.isBlank()) {
            
            //Para rut menor a los 10.xxx.xxx
            if (rut.length() == 9) {
                rut = "0"+rut;
            }
            
            //Valida largo de rut y formato
            if (rut.length() == 10 && rut.charAt(8) == '-') {
                
                for (int i = 0; i < rut.length(); i++) {
                    if (Character.isDigit(rut.charAt(i)) && i != 8 && i != 9) {
                        isValid = true;
                    }
                }
            }
        }
        
        if (isValid) {
            
            //Obtiene digitos antes del guión
            rutNumber = Integer.parseInt(rut.split("-")[0]);
            
            //Calcula digito verificador
            for (int i = 8; i > 0; i--) {
                
                numDigit   = rutNumber % 10;
                rutNumber  = rutNumber / 10;
                checkDigit = checkDigit + (numDigit * multiplier);
                
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
            char rutDigit = Character.toLowerCase(rut.charAt(9));
             
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
    
    //Valida 
    public static boolean checkEqualPasswords(String password, String rePassword) {
        return password.equals(rePassword);
    }
    
    /* Valida que las contraseñas sean seguras
        return  1: contraseña admitida
        return  0: contraseña debil
        return -1: largo minimo no cumplido
        return -2: contraseña vacía
    */
    public static int passwordStrength(String password) {
        
        //Crea e inicializa arreglo booleano
        boolean[] checker = new boolean[4];
        for (int i = 0; i < checker.length; i++) {
            checker[i] = false;
        }
        
        //Si no está vacía
        if (!password.isBlank()) {
            
            //Si no cumple el largo mínimo
            if (password.length() < 8) {
                return -1;
            }
            else {
                checker[0] = true;
            }
            
            for (int i = 0; i < password.length(); i++) {
                
                if (!checker[1] && Character.isDigit(password.charAt(i))) {
                    checker[1] = true;
                }
                else if (!checker[2] && Character.isLowerCase(password.charAt(i))) {
                    checker[2] = true;
                }
                else if (!checker[3] && Character.isUpperCase(password.charAt(i))) {
                    checker[3] = true;
                }
            }
        }
        else { //Campo vacío
            return -2;
        }
        
        //Retorna 1 si cumple con todas las condiciones de seguridad
        if (checker[0] && checker[1] && checker[2] && checker[3]) {
            return 1;
        }
        
        //Retorna 0 si no cumple con las normas de seguridad
        return 0;
    }

    
    public static boolean checkBlank(String field) {
        return field.isBlank();
    }
    
}
