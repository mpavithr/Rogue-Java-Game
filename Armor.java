package com.mycompany.step2eg;

public class Armor extends Item{
    public String Name;
    public int roomid;
    public int serialid;
    private String ch;
    public Armor(String s){
        this.setName(s);
    }
   @Override
    public String getChar( ) {
        ch = "]";
        return ch;
    }
}
