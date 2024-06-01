/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Usayd Jahangiri
 */
public class CustomerViewController implements Initializable {
    
    @FXML
    private Label usernameLabel, balanceLabel, levelLabel, feeLabel, balanceWarningLabel, noFundsLabel;
    @FXML
    private TextField depositInput, withdrawInput;
   
    private Customer cust;
    private Account acc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    // method to log out. returns to login page
    public void logout(ActionEvent event) throws IOException{
        FinalProject m = new FinalProject();
        m.changeScene("login.fxml");
    }
   
    // Method to set the logged-in username
    public void setCustomerUser(Customer customer, Account acc) {
        this.cust = customer;
        this.acc = acc;
        updateUsernameLabel();
        updateBalanceLabel();
        updateLevelLabel();
        updateFeeLabel();
    }

    // Method to update the username label
    private void updateUsernameLabel() {
        usernameLabel.setText(cust.getUsername());
    }
    
    // this updates the balance label
    private void updateBalanceLabel(){
        balanceLabel.setText(cust.getBalanceString());
    }
    
    // this method updates level
    private void updateLevelLabel(){
        cust.verifyLevel();
        levelLabel.setText(cust.getLevelType());
    }
    
    // this method updates the fee label
    private void updateFeeLabel(){
        feeLabel.setText(String.valueOf(cust.getFee()));
    }
    
    // this method gives a warning if balance less than 50
    private void updateBalanceWarning(){
        if (acc.getBalance() < 50){
            balanceWarningLabel.setText("WARNING: balance less than 50");
        }
        else{
            balanceWarningLabel.setText("");
        }
    }
    
    // set no funds label to nothing when depositing or withdrawing
    private void updateNoFundsLabel(){
        noFundsLabel.setText("");
    }
    
    @FXML
    // When deposit amount entered and button clicked, update balance if valid
    public void handleDeposit(){
        String dep = depositInput.getText();
        if (dep != null){
            try{
                double depValue = Double.parseDouble(dep);
                acc.deposit(depValue);
                cust.updateBalance(acc.getBalance());
                updateBalanceLabel();
                updateLevelLabel();
                updateFeeLabel();
                updateBalanceWarning();
                updateNoFundsLabel();
            } catch (NumberFormatException e){
                System.out.println("please enter valid value");
            }
        }
        else{
            System.out.println("please enter a value");
        }
    }
    
    @FXML
    // When withdraw amount entered and button clicked, update balance if valid
    public void handleWithdraw(){
        String wth = withdrawInput.getText();
        if (wth != null){
            try{
                double wthValue = Double.parseDouble(wth);
                acc.withdraw(wthValue);
                cust.updateBalance(acc.getBalance());
                updateBalanceLabel();
                updateLevelLabel();
                updateFeeLabel();
                updateBalanceWarning();
                updateNoFundsLabel();
            } catch (NumberFormatException e){
                System.out.println("pleaser enter a valid value");
            }
        } else{
            System.out.println("please enter a value");
        }
    }
    
    @FXML
    // when customer spends 100 dollar item
    public void handleBuy100(){
        double val = 100 + cust.getFee();
        if (acc.getBalance() >= val){
            acc.withdraw(val);
            cust.updateBalance(acc.getBalance());
            updateBalanceLabel(); 
            updateLevelLabel();
            updateFeeLabel();
            updateBalanceWarning();
        }
        else{
            noFundsLabel.setText("Insufficient Funds");
        }
    }
    
    @FXML
    // when customer spends 200 dollar item
    public void handleBuy200() {
        double val = 200 + cust.getFee();
        if (acc.getBalance() >= val) {
            acc.withdraw(val);
            cust.updateBalance(acc.getBalance());
            updateBalanceLabel();
            updateLevelLabel();
            updateFeeLabel();
            updateBalanceWarning();
        } else {
            noFundsLabel.setText("Insufficient Funds");
        }
    }
    
    @FXML
    // when customer spends 300 dollar item
    public void handleBuy300() {
        double val = 300 + cust.getFee();
        if (acc.getBalance() >= val) {
            acc.withdraw(val);
            cust.updateBalance(acc.getBalance());
            updateBalanceLabel();
            updateLevelLabel();
            updateFeeLabel();
            updateBalanceWarning();
        } else {
            noFundsLabel.setText("insufficient funds");
        }
    }
}
