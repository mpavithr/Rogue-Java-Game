package com.mycompany.step2eg;


import java.util.ArrayList;

public class TopMessageArea extends Displayable{
    private int topHeight;
    private int width;
    private String ch;
    public TopMessageArea(int _topHeight, int _width){
        width = _width;
        topHeight = _topHeight;
    }
    public int getHeight(){
        return topHeight;
    }
    public int getWidth(){
        return width;
    }
    public void setHeight(int height){
        topHeight = height;
    }
    public void setWidth(int _width){
        width = _width;
    }
    @Override 
    public String getChar() {
        ch = " ";        
        return ch;
    }  

}
