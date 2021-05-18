package com.mycompany.step2eg;

import java.util.ArrayList;

public class Passage extends Structure {
    private int roomid1;
    private int roomid2;
    private String ch;
    ArrayList<Integer> PosX = new ArrayList<>();
    ArrayList<Integer> PosY = new ArrayList<>();
    public Passage(){
        
    }
    public void setName(String abc){
        
    }
    public void setID(int room1, int room2){ //switch these to rooms
        roomid1 = room1;
        roomid2 = room2;
    }
    @Override 
    public void SetPosX(int x){
        PosX.add(x);
    }
    @Override
    public void SetPosY(int y){
        PosY.add(y);
    }
    
    public ArrayList<Integer> getListPosX(){
        return PosX;
    }
    public ArrayList<Integer> getListPosY(){
        return PosY;
    }
    
    @Override
    public String toString() {
        String str = "Passage: \n";
        str += "Room 1:" + roomid1 + "\n";
        str += "Room 2:" + roomid2 + "\n";
        str += super.toString();
        return str;
    }
    @Override 
    public String getChar() {
        String ch = "#";        
        return ch;
    }  
}
