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
    public Boolean isSolvable(){
        int xGCD = getGCD(buttonA.getX(), buttonB.getX());
        int yGCD = getGCD(buttonA.getY(), buttonB.getY());
        return prize.getX() % xGCD == 0 && prize.getY() % yGCD == 0;
    }
    private int getGCD(int a, int b){
        if(b==0) return a;
        return getGCD(b, a%b);
    }
    @Override
    public String toString(){
        return "Button A: " + buttonA.toString() + " Button B: " + buttonB.toString() + " Prize: " + prize.toString();
    }
}
