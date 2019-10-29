package security;

public final class Validator {
    
    private Validator() {}
   
    /*      -VALIDIA ENTRADAS ILEGALES-
     * Comprueba cadenas vacias o nulas
     * return true:  cadena no vacia
     * return false: cadena vacia o nula.
    */
    public static boolean checkBlank(String field) {
        
        return !field.isBlank();
    }
    
    /*      -VALIDA NOMBRE/S || APELLIDO/S-
     * Valida entrada de nombre/s y apellido/s
     * return  1: campo validado con exito
     * return  0: no cumple formato
     * return -1: largo invalido
     * return -2: campo invalido/vacío
    */
    public static int checkNamesField(String field) {
        
        //Comprueba cadenas invalidas 
        if (checkBlank(field)) {
            
            //Comprueba largo mínimo y máximo
            if (field.matches(".{0,45}")) {
        
                //Comprueba que esté en formato "xxx*" o "xxx* xxx*"
                if (field.matches("(^[a-zA-Z]*$|^[a-zA-Z]*[ ]{1}[a-zA-Z]*[ ]*$)")) {                    
                    return 1; //Cadena y formato validados correctamente
                }                
                return 0; //Formato incorrecto
            }            
            return -1; //Cadena con largo invalido
        }        
        return -2; //Cadena vacía
    }
    
    /*      -VALIDA CORREO ELECTRÓNICO-
     * Valida formato del correo electronico 
     * (Se debe añadir lista blanca para evitar mails temporales)
     * return  1: Correo electrónico valido
     * return  0: Correo electrónico invalido
     * return -1: Correo no cumple dimensiones
     * return -2: Campo invalido/vacío
    */
    public static int checkEmail(String email) {
        
        //Comprueba cadena vacia/nula
        if (checkBlank(email)) {
            
            //Comprueba largo mínimo y máximo
            if (email.matches("(.{6,255})")) {
                
                //Formato correo electronico: ^xxxxxx@xxxx.xxx.xxx$
                if (email.matches("^[^\\ ()<>@,;:\\\"\\'\\[\\]ç%&]+@(([^\\ ()<>@,.;:\\\"\\'\\[\\]ç%&]+)\\.){1,2}[a-zA-Z]{2,}$")) {
                    return 1; //Correo verificado con éxito
                }
                return 0; //Correo no cumple formato
            }
            return -1; //Correo no cumple con largo minimo ni maximo
        }
        return -2; //Correo invalido/vacío
    }
    
    /*      -VALIDA POLITICA DE CONTRASEÑA-
     * Valida que las contraseñas sean seguras
     * return  1: contraseña admitida
     * return  0: contraseña debil
     * return -1: largo minimo no cumplido
     * return -2: contraseña vacía
    */
    public static int checkPasswordStrength(String password) {
        
        //Comprueba cadenas invalidas
        if (checkBlank(password)) {
            
            //Valida largo mínimo de la contraseña
            if (password.matches(".{8,128}")) {
                
                //Comprueba que la contraseña ingresada cumpla con la expresión regular.
                if (password.matches("^.*(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")) {
                    return 1; //Contraseña admitida
                } 
                return 0; //Contraseña debil
            }
            return -1; //Contraseña muy corta 
        }
        return -2; //Contraseña vacía
    }
    
    /*      -VALIDA REINGRESO DE CONTRASEÑA-
     * Valida que ambas contraseñas sean iguales
     * return true:  contraseñas iguales
     * return false: contraseñas no coinciden
    */
    public static boolean checkEqualPasswords(String password, String rePassword) {
        return password.equals(rePassword);
    }
    
    /*           -VALIDA RUT-
     * Valida rut en formato (xxxxxxxx-x)
     * return  1: Digito verificador y formato correcto
     * return  0: Digito verificador invalido.
     * return -1: Formato incorrecto.
    */  
    public static int checkRut(String rut) {
        
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
        
        //Verificacion por medio de digito verificador
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
    
}

