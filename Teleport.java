package com.mycompany.step2eg;

public class Teleport extends CreatureAction {

    public Teleport(String name, Creature owner) {
        super(owner);
        System.out.println("Teleport constructor");
    }
}
