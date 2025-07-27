package com.example.adventofcode.solutions;

import java.util.HashSet;

import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.DataLoader;


public class DayThirteen2024 {
    private static final HashSet<ClawMachine> clawMachines = DataLoader.loadDayThirteen("DataFiles\\DayThirteenTestData.txt");
    public static void main(String[] args) {
        printMachines();
    }
    public DayThirteen2024(){}
    private static void printMachines(){
        for(ClawMachine c : clawMachines){
            System.out.println(c.toString() + " isSolvable " + c.isSolvable() + " is a cheapest: " + isButtonACheapest(c));
        }
    }
    public static Boolean isButtonACheapest(ClawMachine cm){
        double distancePerPointA = (((double)cm.getButtonA().getX()/cm.getPrize().getX()) + ((double)cm.getButtonA().getY()/cm.getPrize().getY())) / 3; 
        double distancePerPointB = ((double)cm.getButtonB().getX()/cm.getPrize().getX()) + ((double)cm.getButtonB().getY()/cm.getPrize().getY());
        return distancePerPointA > distancePerPointB;
    }
}