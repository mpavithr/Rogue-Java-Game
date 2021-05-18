package com.mycompany.step2eg;

public class Action extends Displayable{
    private String Message;
    private int IntValue;
    private int CharValue;

    public void setMessage(String msg) {
        Message = msg;
    }
    
    public void setIntValue(int v) {
        IntValue = v;
    }
    
    public void setCharValue(char c) {
        CharValue = c;
    }
    public String toString( ) {
        //width="80" topHeight="2" gameHeight="41" bottomHeight="4"
        String str = "Action: \n";
        str += "   Message: "+ Message + "\n";
        str += "   IntValue "+IntValue + "\n";
        str += "   CharValue "+CharValue + "\n";
        
        return str;
    } 
}
