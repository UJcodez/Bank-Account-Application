/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Usayd Jahangiri
 */
public class Customer extends User{
    
    // instance variables
    private String position;
    private String username;
    private String password;
    private Account account;
    private Level level;
    private File customerFile;
    
    public Customer(String user, String pass){
        this.position = "customer";
        this.username = user;
        this.password = pass;
        this.account = new Account(readBalanceFromFile());
        this.account.setBalance(readBalanceFromFile());
        verifyLevel();
    }
    
    @Override
    public String getUsername(){
        return username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getPosition() {
        return position;
    }
    
    public String getBalanceString(){
        return Double.toString(account.getBalance());
    }
    
    public void depositAmt(double d){
        account.deposit(d);
        updateBalance(account.getBalance());
    }
    
    public void withdrawAmt(double d){
        account.withdraw(d);
        updateBalance(account.getBalance());
        
    }
    
    // level dynamically changes state based on account balance
    public void verifyLevel(){
        if (this.account.getBalance() < 1000){
            level = new SilverLevel(); // silver is less than 1000
            System.out.println("silver verified");
        }
        else if (this.account.getBalance() >= 1000 && this.account.getBalance() < 2000){
            level = new GoldLevel(); // gold is between 1000 and 1999
            System.out.println("gold verified");
        }
        else{
            level = new PlatinumLevel(); // plat is 2000 or more
            System.out.println("plat verified");
        }
    }
    
    // returns the customer level
    public String getLevelType(){
        return level.getLevel();
    }
    
    // returns the fee amount
    public double getFee(){
        return level.applyFee();
    }
    
    // this method is used to read the balance in customer's file
    private double readBalanceFromFile() {
        File customerFile = new File("customers" + File.separator + username + ".txt");
        if (customerFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(customerFile))) {
                // skip reading the password in file
                reader.readLine();
                // read the balance from 2nd line, and convert to type double
                String balanceStr = reader.readLine();
                return Double.parseDouble(balanceStr);
            } catch (IOException | NumberFormatException e) {
                System.out.println("The account balance could not be read: " + e.getMessage());
            }
        }
        // in case of error or non-existant file, return default amount of 100
        return 100.0; 
    }

    public void updateBalance(double newBalance) {
        account.setBalance(newBalance);
        saveBalanceToFile(newBalance);
    }

    private void saveBalanceToFile(double balance) {
        // updated balance is written to the customer's file here
        File customerFile = new File("customers" + File.separator + username + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile))) {
            writer.write(password);
            writer.newLine();
            writer.write(String.valueOf(balance)); 
        } catch (IOException e) {
            System.out.println("The balance could not be saved: " + e.getMessage());
        }
    }
}
