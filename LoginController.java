/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;

/**
 * FXML Controller class
 *
 * @author Usayd Jahangiri
 */
public class LoginController implements Initializable {
    
    @FXML
    private Button loginButton;
    @FXML
    private Label errorloginbutton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ChoiceBox<String> userChoice;

    // array of user types
    private String[] users = {"manager", "customer"};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userChoice.getItems().addAll(users);
    }   
    
    // method to call verification when sign in button is pressed
    public void login(ActionEvent event) throws IOException{
        verifyLogin();
    }
    
    // verify user login and pass
    private void verifyLogin() throws IOException{
        FinalProject f =  new FinalProject();
        String currentUser = userChoice.getValue();
        
        if (currentUser == null){
            errorloginbutton.setText("please enter role");
            return;
        }
        
        String usernameInput = username.getText();
        String passInput = password.getText();
        
        if(username.getText().isEmpty() && password.getText().isEmpty()){
            errorloginbutton.setText("Please enter all fields");
        }
        
        else if (currentUser.equals("manager") && usernameInput.equals("admin") && passInput.equals("admin")){
            Manager m = new Manager("admin", "admin");
            errorloginbutton.setText("success");
            
           f.changeScene("managerView.fxml");
        }
        
        else if (currentUser.equals("manager") && !usernameInput.equals("admin") && !passInput.equals("admin")){
            errorloginbutton.setText("incorrect username or password");
        }
        
        else{
            File customerFile = new File("customers" + File.separator + usernameInput + ".txt");
            if (customerFile.exists()) {
                System.out.println("exists");
                try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
                    String storedPassword = reader.readLine();
                    if (storedPassword != null && storedPassword.equals(passInput)) {
                        String balanceStr = reader.readLine();
                        double balance = Double.parseDouble(balanceStr); // Read balance
                        Account acc = new Account(balance);
                        Customer cust = new Customer(usernameInput, passInput);
                        cust.updateBalance(balance); // Read balance from file
                        // Password matches
                        errorloginbutton.setText("Success");
                        f.changeSceneTwo("CustomerView.fxml", cust, acc);
                        // Navigate to customer view
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                
                }
            }
            // Incorrect username or password
            errorloginbutton.setText("Incorrect username or password");
        }
    }
}
    
