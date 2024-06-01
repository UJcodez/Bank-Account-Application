/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import coe528.project.Level;

/**
 *
 * @author Usayd Jahangiri
 */
public class GoldLevel extends Level {
    
    @Override
    public int applyFee(){
        return 10;
    }
    
    @Override
    public String getLevel(){
        return "Gold";
    }
    
    
}
