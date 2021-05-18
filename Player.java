package com.mycompany.step2eg;

import java.util.Stack;

public class Player extends Creature{
    private Item weapon;
    private Item armorOn;
    private String PlayerName;
    private int roomid;
    private int serialid;
    private String ch;
    private Stack<Item> stack = null;
    private MsgCharacter msg;
    
    public Player(){
        stack = new Stack<Item>(); 
    }
    public Stack getItemStack(){
        return stack;
    }
    public void setWeapon(Item sword) {
        weapon = sword;
    }
    
    public void setArmor(Item armor) {
        armorOn = armor;
    }
    public void setName(String string) {
        PlayerName = string;
    }
    
    public void setID(int room, int serial) {
        roomid = room;
        serialid = serial;
    }
    public void stack_push(Item item) { 
        stack.push(item); 
    } 
    public Displayable stack_pop(){
        if (stack.empty()){
            return msg;
        }
        Item item = stack.pop();
        return item;
    }
    @Override
    public String toString() {
        String str = "Monster: \n";
        str += "    Name: " + PlayerName + "\n";
        str += "    Room: " + roomid + "\n";
        str += "    Serial: " + serialid + "\n";
        str += super.toString();
        return str;
    } 
    @Override
    public String getChar( ) {
        ch = "@";
        return ch;
    }
    
    
}
