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
public class MsgCharacter extends Displayable{
    private String character;
    public MsgCharacter(){
        character = " ";
    }
    public void setChar(char ch) {
        character = String.valueOf(ch);        
    } 
    public String getChar(){
        return character;
    }

}