package com.mycompany.step2eg;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class DungeonTest implements Runnable{
    private static final int DEBUG = 0;
    private boolean isRunning;
    public static final int FRAMESPERSECOND = 60;
    public static final int TIMEPERLOOP = 1000000000 / FRAMESPERSECOND;
    private static objDisplayGrid displayGrid = null;
    private Thread keyStrokePrinter;
    private int width_;
    private int height_;
    private int gameheight_;
    private int topheight_;
    private int bottomheight_;
    private String ObjDisplayString;
    private Displayable wall;
    private TopMessageArea my_top_msg_area;
    public DungeonTest(int width, int gameheight, int topheight, int bottomheight) {
        width_ = width;
        gameheight_ = gameheight;
        topheight_=topheight;
        bottomheight_ = bottomheight;
        height_=gameheight_+topheight_+bottomheight_;
        displayGrid = new objDisplayGrid(width, gameheight_, topheight_, bottomheight_);
        displayGrid.initializeDisplay();
    }

    
    public void run() {
        //displayGrid.fireUp();
        for (int step = 1; step < width_ / 2; step *= 2) {
            for (int i = 0; i < width_; i += step) {
                for (int j = 0; j < height_; j += step) {
                    //displayGrid.addObjectToDisplay(main, i, j);
                }
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            //displayGrid.initializeDisplay();
        }
    }
    public static void main(String[] args) throws Exception{
        String fileName = null;
        switch (args.length) {
        case 1:
           fileName = "src/xmlFiles/" + args[0];
           break;
        default:
           System.out.println("java Test <xmlfilename>");
           System.out.println("file not provided, check path or check file");
	   return;
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DungeonXMLHandler handler = new DungeonXMLHandler();
            saxParser.parse(new File(fileName), handler);
	    
            Dungeon dungeon = handler.getDungeon();
            Player My_Player = null;
            DungeonTest dungeontest = new DungeonTest(dungeon.getDungeonWidth(), dungeon.getDungeonGameHeight(),dungeon.getDungeonTopHeight(),dungeon.getDungeonBottomHeight());
            int top_width = dungeon.getDungeonWidth();
            int top_height = dungeon.getDungeonTopHeight();
            Player player = handler.getPlayer();
            //TopMessageArea topmessagearea = new TopMessageArea(top_width, top_height);
            //trying to display items
            //displayGrid.addObjectToDisplay(displayable, DEBUG, DEBUG);

            //trying to display rooms
            ArrayList<Room> the_rooms = handler.getRoomList();
            for(int i = 0; i < the_rooms.size(); i++){
                Room temp = the_rooms.get(i);
                int x = temp.getPosX();
                int y = temp.getPosY();
                int width = temp.getWidth();
                int height = temp.getHeight();
                for(int j = x; j < x+width; j++){
                    for(int k = y; k < height+y; k++){
                        System.out.println(temp.getChar());
                        if(j == x || k == y || j == x+width-1 || k == y + height-1){
                            Wall wall = new Wall();
                            displayGrid.addObjectToDisplay(wall, j, k);
                        }
                        else{
                            displayGrid.addObjectToDisplay(temp, j, k);
                        } //filling it with .s but how do i do x's??????
                    }
                }
            }
            ArrayList<Passage> the_passages = handler.getPassageList();
            for(int i = 0; i < the_passages.size(); i++){
                Passage temp = the_passages.get(i); 
                //i want to loop through all the posxs and posys
                //(5,2) and (9,2) (6,2)
                ArrayList<Integer>x = temp.getListPosX();
                ArrayList<Integer>y = temp.getListPosY();
                for(int j = 0; j < x.size()-1; j++){
                    int x1position = x.get(j);
                    int y1position = y.get(j);
                    int x2position = x.get(j+1);
                    int y2position = y.get(j+1);
                    if(x1position == x2position){
                        if(y1position < y2position){
                            for(int k = y1position; k < (y2position+1);k++){
                                displayGrid.addObjectToDisplay(temp, x1position, k);
                            }
                        }
                        else {
                            for(int k = y1position; k > (y2position-  1); k--){
                                displayGrid.addObjectToDisplay(temp, x1position, k);
                            }
                        }
                    }
                    else if(y1position == y2position){
                        int var;
                        if(x1position < x2position){
                            var = 1;
                        }
                        else{
                            var = -1;
                        }
                        for(int k = x1position; k < (x2position+1); k=k+var){
                            displayGrid.addObjectToDisplay(temp, k, y1position);
                        }
                    }
                    else{
                        Displayable random = new Displayable();
                        displayGrid.addObjectToDisplay(random, j, j); 
                    }
                }    
                Door door1 = new Door();
                Door door2 = new Door();
                displayGrid.addObjectToDisplay(door1, x.get(0), y.get(0));
                displayGrid.addObjectToDisplay(door2, x.get(x.size()-1), y.get(y.size()-1));
            }

            ArrayList<Creature> the_creatures = handler.getCreatureList();
            for(int i = 0; i < the_creatures.size(); i++){
                Creature temp = the_creatures.get(i);
                if(temp instanceof Player){
                    displayGrid.setaPlayer(temp);
                    My_Player = (Player) temp;
                }
                int x = temp.getPosX();
                int y = temp.getPosY();
                System.out.println(temp.getChar());
                displayGrid.addObjectToDisplay(temp, x, y);
            }
            ArrayList<Item> the_items = handler.getItemList();

            for(int i = 0; i < the_items.size(); i++){
                Item temp = the_items.get(i);
                int x = temp.getPosX();
                int y = temp.getPosY();
                System.out.println(temp.getChar());
                displayGrid.addObjectToDisplay(temp, x, y);
            }
            int hp_player = My_Player.getHp();
            System.out.println(My_Player.getHp()+"ladjflakdhfopaishfdopaihfpoahj");
            int core = 0;
            String top_string = dungeontest.makeStringforTop(hp_player, core);
            ArrayList<MsgCharacter> chars = dungeontest.getMsgChars(top_string);
            for(int i = 0; i < chars.size(); i++){
                String temp = (chars.get(i)).getChar();
                displayGrid.addMessage(temp, i, 0);
            }
            String bottom_pk_string = dungeontest.makeStringforBottomPack();
            ArrayList<MsgCharacter> packchars = dungeontest.getMsgChars(bottom_pk_string);
            for(int i = 0; i < packchars.size(); i++){
                String temp = (packchars.get(i)).getChar();
                displayGrid.addMessage(temp, i, top_height+dungeon.getDungeonGameHeight());
            }

            String bottom_info_string = dungeontest.makeStringforBottomInfo();
            ArrayList<MsgCharacter> infochars = dungeontest.getMsgChars(bottom_info_string);
            for(int i = 0; i < infochars.size(); i++){
                String temp = (infochars.get(i)).getChar();
                displayGrid.addMessage(temp, i, top_height+dungeon.getDungeonGameHeight()+2);
            }
            
            Thread testThread = new Thread(dungeontest);
            testThread.start();
            dungeontest.keyStrokePrinter = new Thread(new KeyStrokePrinter(displayGrid));
            dungeontest.keyStrokePrinter.start();

            testThread.join();
            dungeontest.keyStrokePrinter.join();

            System.out.println(dungeon.toString());

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
    public String makeStringforTop(int hp, int core){
        String str = "HP: ";
        str += hp;
        str += "   Core: ";
        str += core;
        return str;
    }
    public String makeStringforBottomPack(){
        String str = "Pack: ";
        return str;
    }
    public String makeStringforBottomInfo(){
        String str = "Info: ";
        //str += "HP monster: ";
        //str += hp_monster;
        return str;
    }
   
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

}
            