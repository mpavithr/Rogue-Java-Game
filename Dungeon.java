package com.mycompany.step2eg;


import java.util.ArrayList;

public class Dungeon {
    private String name;
    private int width;
    private int gameHeight;
    private int topHeight;
    private int bottomHeight;
    Dungeon dungeon = null;
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Passage> passages = new ArrayList<>();
   
    
    public Dungeon(String _name, int _width, int _topHeight, int _gameHeight, int _bottomHeight){
        name = _name;
        width = _width;
        gameHeight = _gameHeight;
        topHeight = _topHeight;
        bottomHeight = _bottomHeight;
    }

    /*Dungeon(String name, String width, String gameHeight, String topHeight, String bottomHeight) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public Dungeon getDungeon(String name, int width, int gameHeight, int topHeight, int bottomHeight){
        if(dungeon == null){
            Dungeon dungeon = new Dungeon(name, width, gameHeight, topHeight, bottomHeight);
        }
        else{
            return dungeon;
        }
        return dungeon;
    }
    public int getDungeonWidth(){
        return width;
    }
    public int getDungeonGameHeight(){
        return gameHeight;
    }
    public int getDungeonTopHeight(){
        return topHeight;
    }
    public int getDungeonBottomHeight(){
        return bottomHeight;
    }
    public void addRoom (Room room) {
        rooms.add(room);
    }
    public void addCreature(Creature creature){
        creatures.add(creature);
    }
    public void addPassage(Passage passage){
        passages.add(passage);
    }
    public void addItem(Item item){
        items.add(item);
    }
    @Override
    public String toString( ) {
        //width="80" topHeight="2" gameHeight="41" bottomHeight="4"
        String str = "Dungeon: \n";
        str += "   name: "+name + "\n";
        str += "   width: "+width + "\n";
        str += "   topHeight: "+topHeight + "\n";
        str += "   gameHeight: "+gameHeight + "\n";
        str += "   bottomHeight: "+bottomHeight + "\n";
        for (Room room : rooms) {
            System.out.println("      these are rooms");
            str += room.toString( ) + "\n";
        }
        for(Creature creature: creatures){
            str += creature.toString() +"\n";
        }
        for(Item item: items){
            str += item.toString() +"\n";
        }
        for(Passage passage: passages){
            str += passage.toString() + "\n";
        }
        return str;
    } 
}
