package com.example.adventofcode.solutions;

import java.util.HashSet;

import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;


public class DayThirteen2024 {
    private static final HashSet<ClawMachine> clawMachines = DataLoader.loadDayThirteen("DataFiles\\DayThirteenTestData.txt");
    public static void main(String[] args) {
        printMachines();
    }
    public DayThirteen2024(){}
    private static void printMachines(){
        for(ClawMachine c : clawMachines){
            System.out.println(c.toString() + " isSolvable " + c.isSolvable());
        }
    }
    private static Boolean isButtonACheapest(ClawMachine cm){
        Coordinate buttonA = cm.getButtonA();
        Coordinate buttonB = cm.getButtonB();
        Coordinate prize = cm.getPrize();
        int distancePerPointA = ((buttonA.getX()/prize.getX()) + (buttonA.getY()/prize.getY())) / 3; 
        int distancePerPointB = (buttonB.getX()/prize.getX()) + (buttonB.getY()/prize.getY());
        return distancePerPointA < distancePerPointB;
    }
}