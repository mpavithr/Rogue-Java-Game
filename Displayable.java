package com.mycompany.step2eg;

import java.util.ArrayList;

public class Displayable {
    private int maxHit;
    private int hpMove;
    private int Hp;
    private String Type;
    private int IntValue;
    private int PosX;
    private int PosY;
    private int Width;
    private int Height;
    private int visibility;
    private String ch;
    
    public String getChar(){
        ch = " ";
        return ch;
    }
    
    public Displayable(){
    }
    public void setInvisible(){
        visibility = 0;        
    }
    public void setVisible(){
        visibility = 1;
    }
    public void setMaxHit(int maxHit_){
        maxHit = maxHit_;
    }
    public void setHpMove(int hpMoves){
        hpMove = hpMoves;
    }
    public void setHp(int Hp_){
        Hp = Hp_;
    }
    public void setType(String t){
        Type = t;
    }
    public void setIntValue(int v){
        IntValue = v;
    }
    public void SetPosX(int x){
        PosX = x;
    }
    public void SetPosY(int y){
        PosY = y;
    }
    public void SetWidth(int x){
        Width = x;
    }
    public void SetHeight(int y){
        Height = y;
    }
    public int getWidth(){
        return Width;
    }
    public int getHeight(){
        return Height;
    }
    public int getPosX(){
        return PosX;
    }
    public int getPosY(){
        return PosY;
    }
    public int getHp(){
        return Hp;
    }
    public int getMaxHit(){
        return maxHit;
    }
    @Override
    public String toString( ) {
        String str = "      visible: " + visibility + "\n";
        str += "      maxHit: " + maxHit + "\n";
        //str += "   hpMoves: " + hpMove + "\n";
        //str += "   Hp: " + Hp + "\n";
        str += "      Type: " + Type + "\n";
        str += "      Int Value: " + IntValue + "\n";
        str += "      pos x: " + PosX + "\n";
        str += "      pos y: " + PosY + "\n";
        str += "      width: " + Width + "\n";
        str += "      height: " + Height + "\n";
        return str;
    }
    
}
