/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author Usayd Jahangiri
 */
public class Account {
    /**
     * OVERVIEW: Account represents a bank account with a balance. It is responsible for ensuring the balance 
     * is non-negative and managing the balance by providing methods to deposit or withdraw from the
     * balance, as well as setting or getting the balance value. Account is mutable as the balance changes due 
     * to deposit and withdraw methods.
     * 
     * The abstraction function is:
     * AF(c) = "Account balance: " + c.balance
     * 
     * The rep invariant is:
     * RI(c) = true if
     *         c.balance >= 0
     * 
     */
    
    // instance variables
    protected double balance;
    
    /**
     * Constructor for initializing instance variables
     * @param initialAmt the initial balance
     */
    public Account(double initialAmt){
        this.balance = initialAmt;
    }
    
    /**
     * This method deposits given amount to balance
     * @param amt amount to deposit
     * 
     * EFFECTS: balance increases by deposited amount
     * REQUIRES: deposit value must be non-negative
     * MODIFIES: account balance
     */
    public void deposit(double amt){
        if (amt > 0){
            balance += amt;
        }
        else{
            System.out.println("cannot deposit less than 0");
        }
    }
    
    /**
     * This method withdraws the specified amount from the balance
     * @param amt amount to withdraw
     * 
     * EFFECTS: withdraw amount decreases the balance
     * REQUIRES: withdraw amount must be non-negative
     * MODIFIES: account balance
     */
    public void withdraw(double amt){
        if (amt >= 0 && amt <= balance){
            balance -= amt;
        }
        else{
            System.out.println("invalid withdrawal amount");
        }
    }
    
    /**
     * 
     * @return the balance
     * EFFECTS: returns the balance
     */
    public double getBalance(){
        return balance;
    }
    
    /**
     * 
     * @param amt the new balance amount
     * EFFECTS: sets the balance to the given amount
     */
    public void setBalance(double amt){
        this.balance = amt;
    }
    
    /**
     * 
     * @return a string
     * EFFECTS: Returns a string representation of the account balance 
     */
    @Override
    public String toString() {
        return "Account Balance: " + this.balance;
    }
    
    /**
     * 
     * @return true or false
     * EFFECTS: returns true if rep invariant holds, false otherwise
     */
    public boolean repOk() {
        if (balance >= 0){
            return true;
        }
        else{
            return false;
        }
    }
}
