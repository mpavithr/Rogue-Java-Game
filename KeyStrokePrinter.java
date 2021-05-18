package com.mycompany.step2eg;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Random;
import java.util.Stack;
public class KeyStrokePrinter implements InputObserver, Runnable {

    private static int DEBUG = 1;
    private static String CLASSID = "KeyStrokePrinter";
    private static Queue<Character> inputQueue = null;
    private objDisplayGrid displayGrid;
    private Player my_player;
    boolean working = true;
    public KeyStrokePrinter(objDisplayGrid grid) {
        inputQueue = new ConcurrentLinkedQueue<>();
        displayGrid = grid;
    }

    @Override
    public void observerUpdate(char ch) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".observerUpdate receiving character " + ch);
        }
        inputQueue.add(ch);
    }

    private void rest() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private boolean processInput() {

        char ch;
        
        boolean processing = true;
        while (processing) {
            if (inputQueue.peek() == null) {
                processing = false;
            } else {
                ch = inputQueue.poll();
                if (DEBUG > 1) {
                    System.out.println(CLASSID + ".processInput peek is " + ch);
                }
                if (ch == 'h') {
                    move_player(-1, 0);
                } if(ch == 'l'){
                    move_player(1,0);
                } if(ch == 'k'){
                    move_player(0, 1);
                } if(ch == 'j'){
                    move_player(0,-1);
                } if(ch == 'p'){
                    my_player = displayGrid.getaPlayer();
                    int x = my_player.getPosX();
                    int y = my_player.getPosY();
                    Displayable item;
                    System.out.println("start stack");
                    PrintStack(displayGrid.GetStack(x,y));
                    System.out.println("Next");
                    Displayable player = displayGrid.popObject(x, y);
                    System.out.println("start stack");
                    PrintStack(displayGrid.GetStack(x,y));
                    System.out.println("Next");
                    if((item = displayGrid.popObject(x,y)) instanceof Item){
                        System.out.println("this is item's char" + item.getChar());
                        my_player.stack_push((Item)item);
                        System.out.println("start stack");
                        PrintStack(displayGrid.GetStack(x,y));
                        System.out.println("Next");
                        displayGrid.addObjectToDisplay(player,x,y);
                        displayGrid.refresh();
                    }
                    else{
                        displayGrid.addObjectToDisplay(item, x, y);
                        displayGrid.addObjectToDisplay(player,x,y);
                        displayGrid.refresh();
                    }
                } if(ch == 'd'){
                    displayGrid.refresh();
                    int x = my_player.getPosX();
                    int y = my_player.getPosY();
                    if(my_player.getItemStack().empty()){
                        System.out.println("Stack is empty");
                    }
                    else{
                        if(displayGrid.getObject(x,y) instanceof Player){
                            Displayable player = displayGrid.popObject(x,y);
                        }
                        Displayable var = displayGrid.getObject(x, y);
                        if(var instanceof Room||var instanceof Passage||var instanceof Item){
                            Displayable item = my_player.stack_pop();
                            if(item instanceof Item){
                                displayGrid.addObjectToDisplay(item, x, y);
                                displayGrid.addObjectToDisplay(my_player, x, y);                            
                            }
                            else{
                                System.out.println("there is no item in the my player stack");
                            }
                        
                        }
                    }
                } if(ch == 'I'){
                    MsgCharacter msg = new MsgCharacter();
                    for(int i = 0; i < displayGrid.getWidth(); i++){
                        displayGrid.addMessage(msg.getChar(), i, displayGrid.getTopHeight()+displayGrid.getGameHeight());
                    }
                    my_player = displayGrid.getaPlayer();
                    Stack<Item> stack = my_player.getItemStack();
                    String bottom_pack_string = makeStringforBottomPack(stack);
                    System.out.println(bottom_pack_string);
                    ArrayList<MsgCharacter> chars = getMsgChars(bottom_pack_string);
                    for(int i = 0; i < chars.size(); i++){
                        String temp = (chars.get(i)).getChar();
                        displayGrid.addMessage(temp, i, displayGrid.getTopHeight()+displayGrid.getGameHeight());
                        displayGrid.refresh();
                    }
                } else {
                    System.out.println("character " + ch + " entered on the keyboard");
                }
            }
        }
        return working;
    }
    public int canMove(Displayable d){
        if (d instanceof Room||d instanceof Passage||d instanceof Door||d instanceof Item||d instanceof Monster){
            return 1;
        }
        else return 0;
    }
    public void move_player(int delta_x, int delta_y){
        System.out.println("got an e, ending input checking");
        my_player = displayGrid.getaPlayer();
        int x = my_player.getPosX();
        int y = my_player.getPosY();
        Displayable var;
        /*Displayable checking = displayGrid.getObject(x,y);
        if(checking instanceof Player){
            System.out.println("all is okay");
        }
        else{
            while((Displayable temp = displayGrid.popObject(x, y))){
                if(temp instanceof Player){
                    return 1;
                }
                else{
            }
        }*/
        var = displayGrid.getObject(x+delta_x,y+delta_y);

        if(canMove(var)==1){
            if(var instanceof Monster){
                int hp_player = my_player.getHp();
                //is this how I end the game
                if(hp_player<=0){
                    working = false;
                    System.out.println("Game has ended");
                    return;
                }
                int hp_monster = var.getHp();
                System.out.println("this is it"+hp_monster);
                Random rand = new Random();
                int random_ofMonster = rand.nextInt(var.getMaxHit());
                int random_ofPlayer = rand.nextInt(my_player.getMaxHit());
                my_player.setHp(hp_player-random_ofMonster);
                var.setHp(hp_monster-random_ofPlayer);
                if(hp_player-random_ofMonster <= 0){
                    working = false;
                    System.out.println("Game has ended");
                }
                String bottom_info_string = makeStringforBottomInfo(var.getHp());
                ArrayList<MsgCharacter> infochars = getMsgChars(bottom_info_string);
                for(int i = 0; i < infochars.size(); i++){
                    String temp = (infochars.get(i)).getChar();
                    displayGrid.addMessage(temp, i, displayGrid.getTopHeight()+displayGrid.getGameHeight()+2);
                }
                String top_string = makeStringforTop(my_player.getHp(), 0);
                ArrayList<MsgCharacter> chars = getMsgChars(top_string);
                for(int i = 0; i < chars.size(); i++){
                    String temp = (chars.get(i)).getChar();
                    displayGrid.addMessage(temp, i, 0);
                }
            }
            else{
                int core=0;
                int hp_player = my_player.getHp()+1;
                if(hp_player<0){
                    working = false;
                    System.out.println("Game has ended");
                }
                my_player.setHp(hp_player);
                String top_string = makeStringforTop(my_player.getHp(), core);
                System.out.println("this is it"+top_string);
                ArrayList<MsgCharacter> chars = getMsgChars(top_string);
                for(int i = 0; i < chars.size(); i++){
                    String temp = (chars.get(i)).getChar();
                    System.out.println(temp);
                    displayGrid.addMessage(temp, i, 0);
                }
                displayGrid.addObjectToDisplay(my_player, x+delta_x, y+delta_y);
                displayGrid.removeObjectFromDisplay(x, y);
                my_player.SetPosX(x+delta_x);
                my_player.SetPosY(y+delta_y);
                displayGrid.refresh();
            }
        }
        else{
            System.out.println("Player can only move within rooms or passages");
        }
    }
    public void PrintStack(Stack<Displayable> s){
	if(s.empty()){ 
		return; 
	} 
	Displayable item = s.pop(); 
	System.out.println(item.getChar()); //Actually print here 
	PrintStack(s); 
	s.push(item); 
    }
    public String makeStringforBottomInfo(int hp_monster){
        String str = "Info: ";
        str += "HP monster: ";
        str += hp_monster;
        return str;
    }
    public String makeStringforBottomPack(Stack<Item> stack){
        String str = "Pack: ";
        if (stack.empty()){ 
            return str;
        }
        else{
            Iterator<Item> iterator = stack.iterator();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                str+= item.getName()+" "+item.getSerialid()+". ";
            }
        }    
        return str;
    }
    /*
    public void PrintStack(Stack<Item> s, String str){ 
        Item x = s.peek(); 
        s.pop(); 
        str += x.getName() + " " + x.getSerialid() + ", ";
        PrintStack(s, str); 
        s.push(x);
    }*/ 
    public ArrayList<MsgCharacter> getMsgChars(String str){
        ArrayList<MsgCharacter> the_chars = new ArrayList<>();
        for(int i=0; i<=str.length()-1; i++) {
                MsgCharacter dummy = new MsgCharacter();
		char ch = str.charAt(i);
                dummy.setChar(ch);
                
                the_chars.add(dummy);
	}
        return the_chars;
        
    }
    public String makeStringforTop(int hp, int core){
        String str = "HP: ";
        str += hp;
        str += "   Core: ";
        str += core;
        return str;
    }
    
    @Override
    public void run() {
        displayGrid.registerInputObserver(this);
        //boolean working = true;
        while (working) {
            rest();
            working = (processInput( ));
        }
    }
}
