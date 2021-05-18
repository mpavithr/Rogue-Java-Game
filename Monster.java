package com.mycompany.step2eg;

public class Monster extends Creature {

    private String monsterName;
    private int roomid;
    private int serialid;
    private String ch;
    
    public Monster() {
    }
    
    public void setName(String string) {
        monsterName = string;
        System.out.println(monsterName);
        if(monsterName=="Troll"){
            System.out.println("hadihapfoihdsoap");
        }
    }
    
    public void setID(int room, int serial) {
        roomid = room;
        serialid = serial;
    }
    
    @Override
    public String toString() {
        String str = "Monster: \n";
        str += "    Name: " + monsterName + "\n";
        str += "    Room: " + roomid + "\n";
        str += "    Serial: " + serialid + "\n";
        str += super.toString();
        return str;
    }
    @Override
    public String getChar( ) {
        if("Troll".equals(monsterName)){
            ch = "T";
        }
        else if("Snake".equals(monsterName)){
            ch = "S";
        }
        else if("Hobgoblin".equals(monsterName)){
            ch = "H";
        }
        else ch = "M";
        return ch;
    }
}
