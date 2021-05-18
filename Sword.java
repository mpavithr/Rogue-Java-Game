package com.mycompany.step2eg;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author meena
 */
public class Sword extends Item{
    public String Name;
    public int roomid;
    public int serialid;
    private String ch;
    public Sword(String name){
       this.setName(name);
    }
    @Override
    public String getChar( ) {
        ch = ")";
        return ch;
    }
}
