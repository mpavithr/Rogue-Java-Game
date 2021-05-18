package com.mycompany.step2eg;

public class Item extends Displayable{
    private Creature Owner;
    private Action action;
    private ItemAction itemaction;
    private CreatureAction creatureaction;
    private int roomid;
    private int serialid;
    private String name;
    
    public void setOwner(Creature owner){
        Owner = owner;
    }
    public void setAction(ItemAction itemaction){
        action = itemaction;
    }
    public void setAction(CreatureAction creatureaction){
        action = creatureaction;
    }
    public void setID(int room, int serial) {
        roomid = room;
        serialid = serial;
    }
    public void setName(String _name){
        name = _name;
    }
    public String getName(){
        return name;
    }
    public int getSerialid(){
        return serialid;
    }
    @Override
    public String toString() {
        String str = "Item Name: " + name + "\n";
        str += "    Room: " + roomid + "\n";
        str += "    Serial: " + serialid + "\n";
        str += super.toString();
        str += "Action: " + action + "\n";
        return str;
    }   
}
