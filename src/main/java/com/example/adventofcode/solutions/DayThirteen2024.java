package com.example.adventofcode.solutions;

import java.util.HashMap;
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
            System.out.println(c.toString() + " isSolvable " + c.isSolvable() + " is a cheapest: " + isButtonACheapest(c));
        }
    }
    public static Boolean isButtonACheapest(ClawMachine cm){
        double distancePerPointA = (((double)cm.getButtonA().getX()/cm.getPrize().getX()) + ((double)cm.getButtonA().getY()/cm.getPrize().getY())) / 3; 
        double distancePerPointB = ((double)cm.getButtonB().getX()/cm.getPrize().getX()) + ((double)cm.getButtonB().getY()/cm.getPrize().getY());
        return distancePerPointA > distancePerPointB;
    }
    public static int getStartingPushes(Coordinate button, Coordinate prize){
        double xPushes = (double)prize.getX()/button.getX();
        double yPushes = (double)prize.getY()/button.getY();
        double startingPushes = xPushes < yPushes ? xPushes : yPushes;
        System.out.println("xPushes: " + xPushes + " yPushes: " + yPushes + " prizeY: " + prize.getY() + " bY: " + button.getY());
        return (int)Math.ceil(startingPushes);
    }
    public static HashMap<String, Integer> getAandBcount(ClawMachine cm){
        Boolean isStartingA = isButtonACheapest(cm);
        System.out.println("isStartingA: " + isStartingA);
        if(isStartingA){
            return findPushesStartingA(cm);
        }
        else{
            return findPushesStartingB(cm);
        }
    }
    public static HashMap<String, Integer> findPushesStartingA(ClawMachine cm){
        HashMap<String, Integer> aAndBCount = new HashMap<>();
        int currentAPushes = getStartingPushes(cm.getButtonA(), cm.getPrize());
        int currentBPushes = 0;
        while(currentAPushes > 0){
            System.out.println("About to check before reducing A currentPushesA: " + currentAPushes + " currentPushesB: " + currentBPushes);
            if(doPushesGetPrize(currentAPushes, currentBPushes, cm)){
                aAndBCount.put("A", currentAPushes);
                aAndBCount.put("B", currentBPushes);
                return aAndBCount;
            }
            currentAPushes--;
            while((currentAPushes * cm.getButtonA().getX()) + (currentBPushes * cm.getButtonB().getX()) <= cm.getPrize().getX() &&
            (currentAPushes * cm.getButtonA().getY()) + (currentBPushes * cm.getButtonB().getY()) <= cm.getPrize().getY()){
                System.out.println("About to check before increasing B currentPushesA: " + currentAPushes + " currentPushesB: " + currentBPushes);
                currentBPushes++;
                if(doPushesGetPrize(currentAPushes, currentBPushes, cm)){
                    aAndBCount.put("A", currentAPushes);
                    aAndBCount.put("B", currentBPushes);
                    return aAndBCount;
                }
            }
        }
        return aAndBCount;
    }
    public static HashMap<String, Integer> findPushesStartingB(ClawMachine cm){
        HashMap<String, Integer> aAndBCount = new HashMap<>();
        int currentAPushes = 0;
        int currentBPushes = getStartingPushes(cm.getButtonB(), cm.getPrize());
        while(currentBPushes > 0){
            System.out.println("About to check before reducing B currentPushesA: " + currentAPushes + " currentPushesB: " + currentBPushes);
            if(doPushesGetPrize(currentAPushes, currentBPushes, cm)){
                aAndBCount.put("A", currentAPushes);
                aAndBCount.put("B", currentBPushes);
                return aAndBCount;
            }
            currentBPushes--;
            while((currentAPushes * cm.getButtonA().getX()) + (currentBPushes * cm.getButtonB().getX()) <= cm.getPrize().getX() &&
            (currentAPushes * cm.getButtonA().getY()) + (currentBPushes * cm.getButtonB().getY()) <= cm.getPrize().getY()){
                System.out.println("About to check before increasing A currentPushesA: " + currentAPushes + " currentPushesB: " + currentBPushes);
                currentAPushes++;
                if(doPushesGetPrize(currentAPushes, currentBPushes, cm)){
                    aAndBCount.put("A", currentAPushes);
                    aAndBCount.put("B", currentBPushes);
                    return aAndBCount;
                }
            }
        }
        return aAndBCount;
    }
    public static Boolean doPushesGetPrize(int aPushes, int bPushes, ClawMachine cm){
        return (aPushes * cm.getButtonA().getX()) + (bPushes * cm.getButtonB().getX()) == cm.getPrize().getX()
            && (aPushes * cm.getButtonA().getY()) + (bPushes * cm.getButtonB().getY()) == cm.getPrize().getY();
    }
}