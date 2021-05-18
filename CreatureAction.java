package com.mycompany.step2eg;

public class CreatureAction extends Action {
    private Creature creatureaction;

    public CreatureAction(Creature owner) {
        creatureaction = owner;
    }
    private String name;
    private String type;
    public void setName(String _name){
        name = _name;
    }
    public void setType(String _type){
        type = _type;
    }
    @Override
    public String toString( ) {
        //width="80" topHeight="2" gameHeight="41" bottomHeight="4"
        String str = "Creature Action: \n";
        str += "   name: "+name + "\n";
        str += "   type: "+type + "\n";
        str += super.toString();
        return str;
    } 
}