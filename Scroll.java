package com.mycompany.step2eg;


public class Scroll extends Item{
    private String ch;

    public Scroll(String _name){
        this.setName(_name);
    }
    @Override
    public String getChar( ) {
        ch = "?";
        return ch;
    }
}
