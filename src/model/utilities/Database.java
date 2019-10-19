package model.utilities;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        
        //initAdministratorCollection("/ruta/de/ejemplo");
        initDermatologistCollection("src/resources/dermatologists.csv");
        initPatientCollection("src/resources/patients.csv");
    }
    
    /* 
     * Inicializa collecion de administradores
     * Param: string con la ruta del fichero
     * Return: true si la operacion es exitosa, false si falla. 
    */
    private void initAdministratorCollection(String path) throws IOException {
       
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            
            //Lee fichero linea a linea
            String row;
            while ((row = csvReader.readLine()) != null) {
                
                String[] data = row.split(",");
                /*                                           password  firstname lastname  date      rut    gender   mail   phoneNumber address */
                Administrator administrator = new Administrator(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
                
                adminDatabase.addToCollection(data[4], administrator);
            }
        }
    }   
    
    /* 
     * Inicializa collecion de dermatologos
     * Param: string con la ruta del fichero
     * Return: true si la operacion es exitosa, false si falla.
    */
    private void initDermatologistCollection(String path) throws FileNotFoundException, IOException {
        
        try (BufferedReader csvReader = new BufferedReader(new FileReader(path))) {
            
            //Lee fichero linea a linea
            String row;
            while ((row = csvReader.readLine()) != null) {
                
                String[] data = row.split(",");
                /*                                            password  firstname lastname  date      rut    gender   mail   phoneNumber address */
                Dermatologist dermatologist = new Dermatologist(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
                
                dermDatabase.addToCollection(data[4], dermatologist);
            }
        }
    }
    
    /* 
     * Inicializa collecion de pacientes
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
