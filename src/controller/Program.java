package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.utilities.Database;
import model.utilities.Session;

public class Program extends Application{
    
    public static void main(String[] args) throws IOException {
        
        //Inicializacion de base de datos
        db.initDatabase();
        
        Application.launch(args);
    }
    
    //Instanciacion de clase base de datos
    public static Database db = new Database();
    public static Session session = new Session();
    
    @Override
    public void init() throws Exception{
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Establece titulo de ventana
        primaryStage.setTitle("Amection");
       
        Parent root = FXMLLoader.load(getClass().getResource("../view/loginPanel.fxml"));
        Scene scene = new Scene(root);
        
        //Ajusta ventana a la escena
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(true);
        
        //Muestra ventana principal
        primaryStage.show();
    }
    
    @Override
    public void stop() throws Exception {
        
    }
    
}