/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author Usayd Jahangiri
 */
public class Manager extends User {
    
    // instance variables
    private String position;
    private String username;
    private String password;
    
    public Manager(String user, String pass){
        this.position = "manager";
        this.username = user;
        this.password = pass;
    }
    
    @Override
    public String getUsername(){
        return this.username;
    }
    
    @Override
    public String getPassword(){
        return this.password;
    }
    
    @Override
    public String getPosition(){
        return this.position;
    }
}
