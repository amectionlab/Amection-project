package model.utilities;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import model.collections.*;
import model.Administrator;
import model.Dermatologist;
import model.Patient;

public class Database {
    
    private final AdministratorCollection adminDatabase;
    private final DermatologistCollection dermDatabase;
    private final PatientCollection patientDatabase;
    
    //Constructor
    public Database() {
        adminDatabase = new AdministratorCollection();
        dermDatabase = new DermatologistCollection();
        patientDatabase = new PatientCollection();
    }
    
    //Invoca metodos privados de inicializacion de colecciones
    public void initDatabase() throws IOException {
        
        initAdministratorCollection("src/resources/administrators.csv");
        initDermatologistCollection("src/resources/dermatologists.csv");
        initPatientCollection("src/resources/patients.csv");
    }
    
    /* 
     *      -Inicializa collecion de administradores-
     * Param: string con la ruta del fichero
     * Return: true si la operacion es exitosa, false si falla. 
    */
    private void initAdministratorCollection(String path) throws IOException {
       
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            
            //Lee fichero linea a linea
            String row;
            while ((row = csvReader.readLine()) != null) {
                
                String[] data = row.split(",");
                /*                                               firstname lastname  date      rut    gender   mail   phoneNumber address  password  */
                Administrator administrator = new Administrator(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
                
                adminDatabase.addToCollection(data[3], administrator);
            }
        }
    }   
    
    /* 
     *      -Inicializa collecion de dermatologos-
     * Param: string con la ruta del fichero
     * Return: true si la operacion es exitosa, false si falla.
    */
    private void initDermatologistCollection(String path) throws FileNotFoundException, IOException {
        
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            
            //Lee fichero linea a linea
            String row;
            while ((row = csvReader.readLine()) != null) {
                
                String[] data = row.split(",");
                /*                                             firstname lastname  date      rut    gender   mail   phoneNumber address password */
                Dermatologist dermatologist = new Dermatologist(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
                
                dermDatabase.addToCollection(data[3], dermatologist);
            }
        }
    } 
    
    /* 
     *      -Inicializa collecion de pacientes-
     * Param: string con la ruta del fichero
     * Return: true si la operacion es exitosa, false si falla. 
    */
    private void initPatientCollection(String path) throws FileNotFoundException, IOException {
        
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            
            //Lee fichero linea a linea
            String row;
            while ((row = csvReader.readLine()) != null) {
                
                String[] data = row.split(",");
                /*                                firstname  lastname  date   rut    gender   mail  phoneNumber  address*/
                Patient newPatient = new Patient(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
                this.addPatient(data[3], newPatient);
            }
        }
    } 
    
    
    /*
     *      -Crea nuevo admin/dermatologo/paciente
     * Param typeOf: tipo de usuario a crear 0:admin, 1:derm, 2:paciente
     * Params : Datos necesarios para constructor de nuevo usuario a crear
    */
    public boolean addNew(int typeOf, String firstname, String lastname, String date, String rut, String gender, String mail, String phoneNumber, String address, String password) {
        
        switch (typeOf) {
            case 0:
                //EN CONSTRUCCION
                return true;
            case 1:
                Dermatologist newDermatologist = new Dermatologist(firstname, lastname, date, rut, gender, mail, phoneNumber, address, password);
                this.addDermatologist(rut, newDermatologist);
                this.saveDbToFile();
                return true;
            case 2:
                //EN CONSTRUCCION
                return true;
            default:
                break;
        }
        
        return false;
    }
    
    /*
     *      -GUARDA ESTADO ACTUAL DE LA BASE DE DATOS CARGADA EN MEMORIA-
     * Return true:  Si logra abrir el archivo en modo escritura y guardar
     * Return false: Error al abrir el archivo o nada que guardar.
    */
    public void saveDbToFile() {
        
        FileWriter file = null;
        PrintWriter pw;
        
        try
        {
            file = new FileWriter("src/resources/administrators.csv");
            pw = new PrintWriter(file);

            /* Actualiza base de datos de dermatologos */
            HashMap data = adminDatabase.getCollection();
            
            //
            Iterator<Map.Entry<String, Object>> entries = data.entrySet().iterator();
            while (entries.hasNext()) {
                
                Map.Entry<String, Object> entry = entries.next();
                
                Administrator user = (Administrator) entry.getValue();
                
                //Recopila datos de usuario
                String userData = user.getFirstname() + "," + user.getLastname() + "," + user.getDate() + "," + user.getRut() + "," + user.getGender() + "," + user.getEmail() + "," + user.getPhoneNumber() + "," + user.getAddress() + "," + user.getPassword();
                pw.println(userData);
            }
            
        } 
        catch (IOException e) { 
        } 
        finally {
            
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != file)
              file.close();
           } 
           catch (IOException e2) {
           }
        }
    }
    
    //Busca administrador en la base de datos
    public Administrator searchAdministrator(String rut) {
        return (Administrator) adminDatabase.selectFromCollection(rut);
    }
    
    //Busca dermatologo en la base de datos
    public Dermatologist searchDermatologist(String rut) {
        return (Dermatologist) dermDatabase.selectFromCollection(rut);
    }
    
    //Busca paciente en la base de datos
    public Patient searchPatient(String rut) {
        return (Patient) patientDatabase.selectFromCollection(rut);
    }
  
    //Añade administrador a la base de datos
    public boolean addAdministrator(String rut, Administrator admin) {
        return adminDatabase.addToCollection(rut, admin);
    }
    
    //Añade dermatologo a la base de datos
    public boolean addDermatologist(String rut, Dermatologist dermatologist) {
        return dermDatabase.addToCollection(rut, dermatologist);
    }
    
    //Añade paciente a la base de datos
    public boolean addPatient(String rut, Patient patient) {
        return patientDatabase.addToCollection(rut, patient);
    }
    
    //Borra administrador de la base de datos
    public boolean deleteAdministrator(String rut) {
        return adminDatabase.removeFromCollection(rut);
    }
    
    //Borra dermatologo de la base de datos
    public boolean deleteDermatologist(String rut) {
        return dermDatabase.removeFromCollection(rut);
    }
    
    //Borra paciente de la base de datos
    public boolean deletePatient(String rut) {
        return patientDatabase.removeFromCollection(rut);
    }
}
