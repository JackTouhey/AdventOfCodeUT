package com.example.adventofcode.utils;

public class ClawMachine {
    private final Coordinate buttonA;
    private final Coordinate buttonB;
    private final Coordinate prize;
    public ClawMachine(Coordinate buttonA, Coordinate buttonB, Coordinate prize){
        this.prize = prize;
        this.buttonA = buttonA;
        this.buttonB = buttonB;
    }
    @Override
    public String toString(){
        return "Button A: " + buttonA.toString() + " Button B: " + buttonB.toString() + " Prize: " + prize.toString();
    }
}
