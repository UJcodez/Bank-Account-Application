/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package coe528.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 *
 * @author Usayd Jahangiri
 */
public class FinalProject extends Application {
    
    private static Stage stg;
    
    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    // method to dynamically switch scenes
    public void changeScene(String fxml) throws IOException{
        Parent p = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(p);
    }
    
    // method to dynamically switch scenes
    public void changeSceneTwo(String fxml, Customer cust, Account acc) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Access the controller of the loaded FXML
        CustomerViewController controller = loader.getController();
        controller.setCustomerUser(cust, acc);

        stg.setScene(scene);
        stg.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
