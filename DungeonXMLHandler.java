package com.mycompany.step2eg;


import static com.mycompany.step2eg.DungeonTest.main;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DungeonXMLHandler extends DefaultHandler {
    private static final int DEBUG = 1;
    private static final String CLASSID = "DungeonXMLHandler";
    private static objDisplayGrid displayGrid = null;

    private StringBuilder data = null;
    
    private Dungeon dungeon;
    //private static List<Room> rooms = new ArrayList<>();
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<CreatureAction> creatureactions = new ArrayList<>();
    ArrayList<ItemAction> itemactions = new ArrayList<>();
    ArrayList<Passage> passages = new ArrayList<>();
    ArrayList<Displayable> displayables = new ArrayList<>();
    
    public ArrayList<Room> getRoomList() {
       return rooms;
    }
    public ArrayList<Creature> getCreatureList() {
       return creatures;
    }
    public ArrayList<Displayable> getDisplayableList(){
        return displayables;
    }
    public ArrayList<Item> getItemList(){
        return items;
    }
    public ArrayList<Passage> getPassageList(){
        return passages;
    }
    public Player getPlayer(){
        return playerBeingParsed;
    }
        
    
    //private Displayable displayableBeingParsed = null;
    private Dungeon dungeonBeingParsed = null;
    //private TopMessageArea topmsgarea = null;
    private Room roomBeingParsed = null;
    private Passage passageBeingParsed = null;
    private Player playerBeingParsed = null;
    private Monster monsterBeingParsed = null;
    private Creature creatureBeingParsed = null;
    private Item itemBeingParsed = null;
    private CreatureAction cactionBeingParsed = null;
    private ItemAction iactionBeingParsed = null;
    private Action actionBeingParsed = null;
    
    private boolean bName = false;
    private boolean bPosX = false;
    private boolean bPosY = false;
    private boolean bWidth = false;
    private boolean bHeight = false;
    private boolean bType = false;
    private boolean bHp = false;
    private boolean bMaxhit = false;
    private boolean bVisible = false;
    private boolean bHpMoves = false;
    private boolean bActionMessage = false;
    private boolean bActionCharValue = false;
    private boolean bActionIntValue = false;
    private boolean bItemIntValue = false;

    public Dungeon getDungeon() {
        return dungeon;
    }

    public DungeonXMLHandler() {
        
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (DEBUG > 1) {
            System.out.println(CLASSID + ".startElement qName: " + qName);
        }

        if (qName.equalsIgnoreCase("Dungeon")) {
            String name = attributes.getValue("name");
            int width = Integer.parseInt(attributes.getValue("width"));
            int gameHeight = Integer.parseInt(attributes.getValue("gameHeight"));
            int topHeight = Integer.parseInt(attributes.getValue("topHeight"));
            int bottomHeight = Integer.parseInt(attributes.getValue("bottomHeight"));
            dungeon = new Dungeon(name, width, topHeight, gameHeight, bottomHeight);
            //topmsgarea = new TopMessageArea(topHeight, width);
            //setTopMsgArea(topmsgarea);
            //displayables.add(topmsgarea);
            dungeonBeingParsed = dungeon;
            //System.out.println(dungeonBeingParsed);
        } else if (qName.equalsIgnoreCase("Rooms")) {
            //System.out.println(rooms);
        } else if (qName.equalsIgnoreCase("Room")) {
            int RoomID = Integer.parseInt(attributes.getValue("room"));
            Room room = new Room(RoomID);
            roomBeingParsed = room;
            roomBeingParsed.setId(RoomID);
            dungeonBeingParsed.addRoom(roomBeingParsed);
            addRoom(roomBeingParsed);
            displayables.add(roomBeingParsed);
            //System.out.println(roomBeingParsed);
        } else if (qName.equalsIgnoreCase("visible")) {
            bVisible = true;
        } else if (qName.equalsIgnoreCase("posX")) {
            bPosX = true;
        } else if (qName.equalsIgnoreCase("posY")) {
            bPosY = true;
        } else if (qName.equalsIgnoreCase("width")) {
            bWidth = true;
        } else if (qName.equalsIgnoreCase("height")) {
            bHeight = true;
        } else if (qName.equalsIgnoreCase("Scroll")){
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Scroll scroll = new Scroll(name);
            scroll.setID(roomID, serial);
            itemBeingParsed = scroll;
            roomBeingParsed.setItem((Scroll) itemBeingParsed);
            //addItem(scroll);
            items.add(scroll);
            displayables.add(scroll);
            //System.out.println(itemBeingParsed);
        } else if(qName.equalsIgnoreCase("ItemAction")){
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            ItemAction itemaction = new ItemAction(itemBeingParsed, name, type);
            iactionBeingParsed = itemaction;
            actionBeingParsed = itemaction;
            itemBeingParsed.setAction((ItemAction) iactionBeingParsed);
            addItemAction(iactionBeingParsed);
            displayables.add(itemaction);
            //System.out.println(iactionBeingParsed);
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        } else if (qName.equalsIgnoreCase("hp")) {
            bHp = true;
        } else if (qName.equalsIgnoreCase("maxhit")) {
            bMaxhit = true;
        } else if (qName.equalsIgnoreCase("visible")) {
            bVisible = true;
        } else if (qName.equalsIgnoreCase("hpMoves")) {
            bHpMoves = true;
        } else if (qName.equalsIgnoreCase("actionMessage")) {
            bActionMessage = true;        
        } else if (qName.equalsIgnoreCase("actionIntValue")) {
            bActionIntValue = true;
        } else if (qName.equalsIgnoreCase("actionCharValue")) {
            bActionCharValue = false;
        } else if (qName.equalsIgnoreCase("Player")) {
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Player player = new Player();
            player.setName(name);
            player.setID(roomID, serial);
            creatureBeingParsed = player;
            playerBeingParsed = player;
            roomBeingParsed.setCreature(playerBeingParsed);
            addCreature(playerBeingParsed);
            displayables.add(playerBeingParsed);
            //System.out.println(playerBeingParsed);
        } else if (qName.equalsIgnoreCase("CreatureAction")){
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            CreatureAction creatureaction = new CreatureAction(creatureBeingParsed);
            actionBeingParsed = creatureaction;
            cactionBeingParsed = creatureaction;
            creatureBeingParsed.setDeathAction(cactionBeingParsed);
            addCreatureAction(cactionBeingParsed);
            displayables.add(cactionBeingParsed);
            //System.out.println(cactionBeingParsed);
        } else if (qName.equalsIgnoreCase("Sword")) {
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int serial = Integer.parseInt(attributes.getValue("serial"));
            Sword sword = new Sword(name);
            sword.setID(roomID, serial);
            itemBeingParsed = sword;
            Displayable temp =displayables.get(displayables.size()-1);
            if (temp instanceof Player){
                playerBeingParsed.setWeapon(itemBeingParsed);
                //addItem(itemBeingParsed);
                items.add(itemBeingParsed);
                displayables.add(itemBeingParsed);
                //System.out.println(itemBeingParsed);
            }
            if(temp instanceof Room){
                roomBeingParsed.setItem((Sword) sword);
                //addItem(itemBeingParsed);
                items.add(itemBeingParsed);
                displayables.add(itemBeingParsed);
                //System.out.println(itemBeingParsed);
            }
            
        } else if (qName.equalsIgnoreCase("ItemIntValue")) {
            bItemIntValue = false;
        } else if (qName.equalsIgnoreCase("Monster")) {
            String nameMonster = attributes.getValue("name");
            int roomNum = Integer.parseInt(attributes.getValue("room"));
            int serialNum = Integer.parseInt(attributes.getValue("serial"));
            Monster monster = new Monster();
            monster.setName(nameMonster);
            monster.setID(roomNum, serialNum);
            creatureBeingParsed = monster;
            monsterBeingParsed = monster;
            Displayable temp =displayables.get(displayables.size()-1);
            if(temp instanceof Room){
                roomBeingParsed.setCreature(monsterBeingParsed);
                addCreature(monsterBeingParsed);
                displayables.add(monsterBeingParsed);
                //System.out.println(monsterBeingParsed);
            }
        } else if (qName.equalsIgnoreCase("Armor")) {
            String name = attributes.getValue("name");
            int roomID = Integer.parseInt(attributes.getValue("room"));
            int Serial = Integer.parseInt(attributes.getValue("serial"));
            Armor armor = new Armor(name);
            armor.setID(roomID, Serial);
            itemBeingParsed = armor;
            Displayable temp =displayables.get(displayables.size()-1);
            if(temp instanceof Player){
                playerBeingParsed.setWeapon(itemBeingParsed);
                //addItem(itemBeingParsed);
                displayables.add(itemBeingParsed);
                items.add(itemBeingParsed);
                //System.out.println(itemBeingParsed);
            }
            if(temp instanceof Room){
                roomBeingParsed.setItem((Armor) armor);
                //addItem(itemBeingParsed);
                displayables.add(itemBeingParsed);
                items.add(itemBeingParsed);
                //System.out.println(itemBeingParsed);
            }
        } else if (qName.equalsIgnoreCase("Passages")) {
            //do nothing
        } else if (qName.equalsIgnoreCase("Passage")) {
            int room1 = Integer.parseInt(attributes.getValue("room1"));
            int room2 = Integer.parseInt(attributes.getValue("room2"));
            Passage passage = new Passage();
            passage.setID(room1, room2);
            passageBeingParsed = passage;
            dungeonBeingParsed.addPassage(passageBeingParsed);
            addPassage(passageBeingParsed);
            displayables.add(passageBeingParsed);
            //System.out.println(passageBeingParsed);
        }else {
            System.out.println("Unknown qname: " + qName);
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bVisible) {
            Displayable atemp =displayables.get(displayables.size()-1);
            
            if(atemp instanceof Player){
                playerBeingParsed.setVisible();
                bVisible = false;
            }
            else if(atemp instanceof Monster){
                monsterBeingParsed.setVisible();
                bVisible = false;
            }
            else if(atemp instanceof Item){
                itemBeingParsed.setVisible();
                bVisible = false;
            }
            else if(atemp instanceof Room){
                roomBeingParsed.setVisible();
                bVisible = false;
            }
            else if(atemp instanceof Passage){
                passageBeingParsed.setVisible();
                bVisible = false;
            }
            
        }
        if (bPosX) {
            Displayable atemp =displayables.get(displayables.size()-1);
            if(atemp instanceof Player){
                playerBeingParsed.SetPosX(roomBeingParsed.getPosX() + Integer.parseInt(data.toString()));
                bPosX = false;
            }
            else if(atemp instanceof Monster){
                monsterBeingParsed.SetPosX(roomBeingParsed.getPosX() + Integer.parseInt(data.toString()));
                bPosX = false;
            }
            else if(atemp instanceof Item){
                itemBeingParsed.SetPosX(roomBeingParsed.getPosX() + Integer.parseInt(data.toString()));
                bPosX = false;
            }
            else if(atemp instanceof Room){
                //removed topmsgarea.getPosX()
                roomBeingParsed.SetPosX(Integer.parseInt(data.toString()));
                bPosX = false;
            }
            else if(atemp instanceof Passage){
                passageBeingParsed.SetPosX(Integer.parseInt(data.toString()));
                bPosX = false;
            }
        } else if (bPosY) {
            Displayable atemp =displayables.get(displayables.size()-1);
            if(atemp instanceof Player){
                playerBeingParsed.SetPosY(roomBeingParsed.getPosY() + Integer.parseInt(data.toString()));
                bPosY = false;
            }
            else if(atemp instanceof Monster){
                monsterBeingParsed.SetPosY(roomBeingParsed.getPosY() + Integer.parseInt(data.toString()));
                bPosY = false;
            }
            else if(atemp instanceof Item){
                itemBeingParsed.SetPosY(roomBeingParsed.getPosY() + Integer.parseInt(data.toString()));
                bPosY = false;
            }
            else if(atemp instanceof Room){
                //roomBeingParsed.SetPosY(Integer.parseInt(data.toString()));
                roomBeingParsed.SetPosY(dungeonBeingParsed.getDungeonTopHeight()+Integer.parseInt(data.toString()));
                bPosY = false;
            }
            else if(atemp instanceof Passage){
                passageBeingParsed.SetPosY(dungeonBeingParsed.getDungeonTopHeight()+Integer.parseInt(data.toString()));
                bPosY = false;
            }
        } else if (bWidth) {
            roomBeingParsed.SetWidth(Integer.parseInt(data.toString()));
            bWidth = false;
        } else if (bHeight) {
            roomBeingParsed.SetHeight(Integer.parseInt(data.toString()));
            bHeight = false;
        } else if (bType) {
            monsterBeingParsed.setType(data.toString());
            bType = false;
        } else if (bHp) {
            creatureBeingParsed.setHp(Integer.parseInt(data.toString()));
            bHp = false;
        } else if (bMaxhit) {
            creatureBeingParsed.setMaxHit(Integer.parseInt(data.toString()));
            bMaxhit = false;
        } else if (bHpMoves) {
            playerBeingParsed.setHpMoves(Integer.parseInt(data.toString()));
            bHpMoves = false;
        } else if (bActionMessage) {
            actionBeingParsed.setMessage(data.toString());
            bActionMessage = false;
        } else if (bItemIntValue){
            itemBeingParsed.setIntValue(Integer.parseInt(data.toString()));
            bItemIntValue = false;
        } else if (bActionCharValue){
            actionBeingParsed.setCharValue(data.charAt(0));
            bActionCharValue = false;
        }

        if (qName.equalsIgnoreCase("Room")) {
            roomBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("Monster")) {
            creatureBeingParsed = null;
            monsterBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("CreatureAction")){
            actionBeingParsed = null;
            cactionBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("Player")){
            creatureBeingParsed = null;
            playerBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("Scroll")){
            itemBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("ItemAction")){
            actionBeingParsed = null;
            iactionBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("Armor")){
            itemBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("Sword")){
            itemBeingParsed = null;
            displayables.remove(displayables.size()-1);
        } else if (qName.equalsIgnoreCase("Passage")){
            passageBeingParsed = null;
            displayables.remove(displayables.size()-1);
        }
    }

    private void addRoom(Room room) {
        rooms.add(room);     
    }
    
    private void addPassage(Passage passage){
        passages.add(passage);
    }
    public void addCreature(Creature creature){
        creatures.add(creature);
    }
    /*public void addItem(Item item){
        items.add(item);
    }*/
    public void addCreatureAction(CreatureAction creatureaction){
        creatureactions.add(creatureaction);
    }
    public void addItemAction(ItemAction itemaction){
        itemactions.add(itemaction);
    }
    /*public TopMessageArea getTopMsgArea(){
        return topmsgarea;
    }
    public void setTopMsgArea(TopMessageArea var){
        topmsgarea = var;
    }*/
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
        if (DEBUG > 1) {
            System.out.println(CLASSID + ".characters: " + new String(ch, start, length));
            System.out.flush();
        }
    }

    @Override
    public String toString() {
        String str = "DungeonXMLHandler\n";
        for (Room room : rooms) {
            str += room.toString() + "\n";
        }
        str += "   roomBeingParsed: " + roomBeingParsed.toString() + "\n";
        str += "   passageBeingParsed: " + passageBeingParsed.toString() + "\n";
        str += "   creatureBeingParsed: " + creatureBeingParsed.toString() + "\n";
        str += "   itemBeingParsed: " + itemBeingParsed.toString() + "\n";
        str += "   actionBeingParsed: " + actionBeingParsed.toString() + "\n";
        str += "   bPosX: " + bPosX + "\n";
        str += "   bPosY: " + bPosY + "\n";
        str += "   bWidth: " + bWidth + "\n";
        str += "   bHeight: " + bHeight + "\n";
        str += "   bType: " + bType + "\n";
        str += "   bHp: " + bHp + "\n";
        str += "   bMaxhit: " + bMaxhit + "\n";
        str += "   bHpMoves: " + bHpMoves + "\n";
        str += "   actionMessage: " + bActionMessage + "\n";
        return str;
    }
}
