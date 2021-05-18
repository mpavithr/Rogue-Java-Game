package com.mycompany.step2eg;

public class ItemAction extends Action{
    private Item itemaction;
    private String name;
    private String type;
    public ItemAction(Item owner, String _name, String _type) {
        itemaction = owner;
        name = _name;
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
