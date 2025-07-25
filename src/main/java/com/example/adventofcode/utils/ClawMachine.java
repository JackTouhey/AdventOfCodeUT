package com.example.adventofcode.utils;

public class ClawMachine {
    private final Coordinate buttonA;
    private final Coordinate buttonB;
    private final Coordinate prize;
    public ClawMachine(Coordinate prize, Coordinate buttonA, Coordinate buttonB){
        this.prize = prize;
        this.buttonA = buttonA;
        this.buttonB = buttonB;
    }
}
