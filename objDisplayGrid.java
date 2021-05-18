package com.mycompany.step2eg;
import com.mycompany.step2eg.asciiPanel.AsciiPanel;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class objDisplayGrid extends JFrame implements KeyListener, InputSubject {

    private static final int DEBUG = 0;
    private static final String CLASSID = ".ObjectDisplayGrid";

    private static AsciiPanel terminal;
    //private Char[][] objectGrid = null;
    private Stack <Displayable>[][] objectGrid = null; // note change in this line

    private List<InputObserver> inputObservers = null;

    private static int height;
    private static int gameheight;
    private static int bottomheight;
    private static int topheight;
    private static int width;
    
    private Player My_player;

    public objDisplayGrid(int _width, int game_height, int top_height, int bottom_height) {
        width = _width;
        gameheight = game_height;
        bottomheight = bottom_height;
        topheight = top_height;
        height = gameheight+bottomheight+topheight;
        terminal = new AsciiPanel(width, height);
        //i have to somehow initialize objectgrid with dungeon's width height
        //how do i do that?
        objectGrid = (Stack<Displayable>[][]) new Stack[width][height];

        //initializeDisplay();
        
        super.add(terminal);
        super.setSize(width * 9, height * 18);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //super.repaint();
        //terminal.repaint( );
        super.setVisible(true);
        terminal.setVisible(true);
        super.addKeyListener(this);
        initializeDisplay();
        inputObservers = new ArrayList<>();
        super.repaint();
    }
    public void topMessageArea(int width, int height){
        objectGrid = (Stack<Displayable>[][]) new Stack[width][height];
    }
    public void bottomArea(int width, int height){
        objectGrid = (Stack<Displayable>[][]) new Stack[width][height];
    }
    @Override
    public void registerInputObserver(InputObserver observer) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".registerInputObserver " + observer.toString());
        }
        inputObservers.add(observer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (DEBUG > 0) {
            System.out.println(CLASSID + ".keyTyped entered" + e.toString());
        }
        KeyEvent keypress = (KeyEvent) e;
        notifyInputObservers(keypress.getKeyChar());
    }
    /*@Override
    public void keyTyped(KeyEvent h){
        KeyEvent keypress = (KeyEvent) h;
        notifyInputObservers(keypress.getKeyChar());
    }*/

    private void notifyInputObservers(char ch) {
        for (InputObserver observer : inputObservers) {
            observer.observerUpdate(ch);
            if (DEBUG > 0) {
                System.out.println(CLASSID + ".notifyInputObserver " + ch);
            }
        }
    }

    // we have to override, but we don't use this
    @Override
    public void keyPressed(KeyEvent even) {
    }

    // we have to override, but we don't use this
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public final void initializeDisplay() {
        if(DEBUG > 1){
            System.out.println(CLASSID + "initializeDisplay in black display");
        }
        int i = -1;
        int j = -1;

        for (i = 0; i < objectGrid.length; i++) {
            for (j = 0; j < objectGrid[i].length; j++) {
                objectGrid[i][j] = new Stack<>();
            }
        }
       
        terminal.repaint();
    }

    public void fireUp() {
        if (terminal.requestFocusInWindow()) {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow Succeeded");
        } else {
            System.out.println(CLASSID + ".ObjectDisplayGrid(...) requestFocusInWindow FAILED");
        }
    }

    public void addObjectToDisplay(Displayable displayable, int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                if(displayable instanceof Player){
                    setaPlayer((Player) displayable);
                }
                objectGrid[x][y].push(displayable);
                //System.out.println("size="+objectGrid[x][y].size());
                //System.out.println(((objectGrid[x][y]).peek()).getChar());
                writeToTerminal(x, y);
            }
        }
    }
    public void removeObjectFromDisplay(int x, int y) {
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                if(objectGrid[x][y].empty()){
                    System.out.println("that part has no displayables");
                    return;
                }
                objectGrid[x][y].pop();
                System.out.println(((objectGrid[x][y]).peek()).getChar());
                writeToTerminal(x, y);
            }
        }
    }
    public int isitempty(int x, int y){
        if(objectGrid[x][y].empty()){
            return 1;
        }
        else return 0;
    }
    /*public void removeItemFromDisplay(int x, int y){
        if ((0 <= x) && (x < objectGrid.length)) {
            if ((0 <= y) && (y < objectGrid[0].length)) {
                Displayable player = objectGrid[x][y].pop();
                System.out.println(player.getChar()+"item check");
                Displayable item = objectGrid[x][y].pop();
                objectGrid[x][y].push(player);
                writeToTerminal(x, y);
            }
        }
    }*/
    private void writeToTerminal(int x, int y) {

            String ch = ((objectGrid[x][y]).peek()).getChar();
            terminal.write(ch, x, y);
            terminal.repaint();
        //}
    }
    public void setaPlayer(Displayable player){
        My_player = (Player) player;
    }
    public Player getaPlayer(){
        return My_player;
    }
    public int getGameHeight(){
        return gameheight;
    }
    public int getTopHeight(){
        return topheight;
    }
    public int getBottomHeight(){
        return bottomheight;
    }
    public int getWidth(){
        return width;
    }
    public void addMessage(String ch, int x, int y){
        terminal.write(ch, x, y);
        terminal.repaint();
    }
    public void refresh(){
        int i;
        int j;
        for (i = 0; i < objectGrid.length; i++) {
            for (j = 0; j < objectGrid[i].length; j++) {
                if(objectGrid[i][j].size()>0){
                    String ch = ((objectGrid[i][j]).peek()).getChar();
                    terminal.write(ch, i, j);
                    terminal.repaint();
                }
            }
        }
    }
    public Displayable getObject(int x, int y){
        if(objectGrid[x][y].empty()){
            MsgCharacter thisisempty = new MsgCharacter();
            return thisisempty;
        }
        return objectGrid[x][y].peek();
    }
    public Displayable popObject(int x, int y){
        if(objectGrid[x][y].empty()){
            MsgCharacter thisisempty = new MsgCharacter();
            return thisisempty;
        }
        return objectGrid[x][y].pop();
    }
    public Displayable pushObject(int x, int y, Displayable item){
        return objectGrid[x][y].push(item);
    }
    public Stack<Displayable> GetStack(int x, int y){
        return objectGrid[x][y];
    }
}
