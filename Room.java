package com.mycompany.step2eg;


import java.util.ArrayList;

public class Room extends Structure{
    private int RoomID;
    private int Id;
    ArrayList<Creature> creatures = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    private Scroll scroll;
    private Armor armor;
    private Sword sword;
    private String ch;
    public Room(int roomid){
        RoomID = roomid;
    }
    public void setId(int room){
        Id = room;
    }
    public void setCreature(Monster monster){
        creatures.add(monster);
    }
    public void setCreature(Player player){
        creatures.add(player);
    }
    public void setItem(Scroll scroll){
        items.add(scroll);
    }
    public void setItem(Armor armor){
        items.add(armor);
    }
    public void setItem(Sword sword){
        items.add(sword);
    }
    @Override 
    public String toString() {
        String str = "   Room: \n";
        str += super.toString();
        str += "      Room ID: " + Id + "\n";
        for(Creature creature: creatures){
            str += creature.toString() +"\n";
        }
        for(Item item: items){
            str += item.toString() +"\n";
        }
        
        return str;
    }  
    @Override 
    public String getChar() {
        ch = ".";        
        return ch;
    }  

}
