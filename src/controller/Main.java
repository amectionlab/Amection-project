package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.utilities.Database;
import model.utilities.Session;

public class Main extends Application{
    
    //Instanciacion de clase base de datos
    public static Database db = new Database();
    public static Session session = new Session();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/loginPanel.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) throws IOException {
        
        //Inicializacion de base de datos
        db.initDatabase();
        
        launch(args);
    }
    
}
