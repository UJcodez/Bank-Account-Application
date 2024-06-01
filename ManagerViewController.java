/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;

public class ManagerViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button addCustomerButton, logoutButton;
    
    @FXML
    private Label managerActionLabel;
    

    private static final String CUSTOMERS_FOLDER = "customers";
    
    // method to log out. returns to login page
    public void logout(ActionEvent event) throws IOException{
        FinalProject m = new FinalProject();
        m.changeScene("login.fxml");
    }

    // This method creates a new folder
    private void validateFolderExistance() {
        File f = new File(CUSTOMERS_FOLDER);
        if (!f.exists()) {
            f.mkdirs();
            System.out.println("New customer folder created"); // print for verifying
        }
    }
    
    @FXML
    // Method used by add button to handle customer adding
    private void handleAddCustomer() {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        addCustomer(user, pass);
    }

    @FXML
    // Method used by delete button to handle customer deleting
    private void handleDeleteCustomer() {
        String user = usernameField.getText();
        deleteCustomer(user);
    }

    // This method adds a customer
    public void addCustomer(String username, String password) {
        // verify that all fields are not empty
        if (username.isEmpty() || password.isEmpty()) {
            managerActionLabel.setText("please enter all fields");
            return;
        }
        // verify if the customer already exists or shares same name as other customer
        if (checkDuplicateCustomers(username)) {
            managerActionLabel.setText("customer already exists");
            return;
        }
        validateFolderExistance();
        // This creates a new file in customers with the file name as the username.txt
        File customerFile = new File(CUSTOMERS_FOLDER + File.separator + username + ".txt");
        try {
            customerFile.createNewFile();
            // first line of file is the password
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile))) {
                writer.write(password);
                writer.newLine(); // Go to new line in the file
                writer.write("100.0"); // write initial balance = 100 to file
            }
            managerActionLabel.setText("Customer Added.");
        } catch (IOException e) {
            System.out.println("An error has occured: " + e.getMessage());
        }
    }

    
    // returns true if the customer file already exists, else false
    private boolean checkDuplicateCustomers(String username){
        File customerFile = new File(CUSTOMERS_FOLDER + File.separator + username + ".txt");
        return customerFile.exists();
    }

    // This method deletes a customer
    public void deleteCustomer(String username) {
        File customerFile = new File(CUSTOMERS_FOLDER + File.separator + username + ".txt");
        if (customerFile.exists()) {
            if (customerFile.delete()) {
                managerActionLabel.setText("Customer Deleted.");
            } else {
                managerActionLabel.setText("Customer Deletion Failed");
            }
        } else {
            managerActionLabel.setText("Customer does not exist.");
        }
    }
}
