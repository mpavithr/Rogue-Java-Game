package com.mycompany.step2eg;

public class Creature extends Displayable {
    
    private int health;
    private int hpmoves;
    private String name;
    public CreatureAction deathAction;
    public CreatureAction hitAction;
    
    public Creature() {
    }
    
    public void setName(String _name){
        name = _name;
    }
        
    public void setHpMoves(int hpm) {
        hpmoves = hpm;
    }
    
    public void setDeathAction(CreatureAction da) {
        deathAction = da;
    }
    
    public void setHitAction(CreatureAction ha) {
        hitAction = ha;
    }
    
    @Override
    public String toString() {
        String str = "Creature: \n";
        str += super.toString();
        str += "    HP: " + health + "\n";
        str += "    HPMoves: " + hpmoves + "\n";
        str += "    DeathAction: " + deathAction + "\n";
        str += "    HitAction: " + hitAction + "\n";
        return str;
    }
    
}

