package com.mycompany.step2eg;


import com.mycompany.step2eg.Armor;
import com.mycompany.step2eg.Creature;
import com.mycompany.step2eg.Item;
import com.mycompany.step2eg.Monster;
import com.mycompany.step2eg.Player;
import com.mycompany.step2eg.Scroll;
import com.mycompany.step2eg.Structure;
import com.mycompany.step2eg.Sword;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Wall extends Structure{
    private String ch;
    public Wall(){
    }
   
    @Override 
    public String getChar() {
        ch = "X";        
        return ch;
    }  

}
