package com.example.adventofcode.solutions;

import java.util.HashMap;
import java.util.HashSet;

import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayThirteen2024 {
    private static final HashSet<ClawMachine> clawMachines = DataLoader.loadDayThirteen("DataFiles\\DayThirteenData.txt");
    public static void main(String[] args) {
        // printMachines();
        sumPrizes();
    }
    public DayThirteen2024(){}
    public static void sumPrizes(){
        int count = 0;
        for(ClawMachine cm : clawMachines){
            if(cm.isSolvable()){
                HashMap<String, Integer> AandBCounts = getAandBcount(cm);
                if(AandBCounts.get("A") == null){
                    AandBCounts = getAandBcountReversed(cm);
                    if(AandBCounts.get("A") != null){
                        count += AandBCounts.get("A") * 3;
                        count += AandBCounts.get("B");
                    }
                }
                else{
                    count += AandBCounts.get("A") * 3;
                    count += AandBCounts.get("B");
                }
            }
        }
        System.out.println("Count: " + count);
    }
    private static void printMachines(){
        for(ClawMachine c : clawMachines){
            System.out.println(c.toString() + " isSolvable " + c.isSolvable() + " is a cheapest: " + isButtonACheapest(c));
        }
    }
    public static Boolean isButtonACheapest(ClawMachine cm){
        double distancePerPointA = (((double)cm.getButtonA().getLongX()/cm.getPrize().getLongX()) + ((double)cm.getButtonA().getLongY()/cm.getPrize().getLongY())) / 3; 
        double distancePerPointB = ((double)cm.getButtonB().getLongX()/cm.getPrize().getLongX()) + ((double)cm.getButtonB().getLongY()/cm.getPrize().getLongY());
        return distancePerPointA > distancePerPointB;
    }
    public static int getStartingPushes(Coordinate button, Coordinate prize){
        double xPushes = (double)prize.getLongX()/button.getLongX();
        double yPushes = (double)prize.getLongY()/button.getLongY();
        double startingPushes = xPushes < yPushes ? xPushes : yPushes;
        System.out.println("xPushes: " + xPushes + " yPushes: " + yPushes + " prizeX: " + prize.getLongX() + " bX: " + button.getLongX() + " prizeY: " + prize.getLongY() + " bY: " + button.getLongY());
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
    public static HashMap<String, Integer> getAandBcountReversed(ClawMachine cm){
        Boolean isStartingA = isButtonACheapest(cm);
        System.out.println("isStartingA: " + isStartingA);
        if(!isStartingA){
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
            while((currentAPushes * cm.getButtonA().getLongX()) + (currentBPushes * cm.getButtonB().getLongX()) <= cm.getPrize().getLongX() &&
            (currentAPushes * cm.getButtonA().getLongY()) + (currentBPushes * cm.getButtonB().getLongY()) <= cm.getPrize().getLongY()){
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
            while((currentAPushes * cm.getButtonA().getLongX()) + (currentBPushes * cm.getButtonB().getLongX()) <= cm.getPrize().getLongX() &&
            (currentAPushes * cm.getButtonA().getLongY()) + (currentBPushes * cm.getButtonB().getLongY()) <= cm.getPrize().getLongY()){
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
        return (aPushes * cm.getButtonA().getLongX()) + (bPushes * cm.getButtonB().getLongX()) == cm.getPrize().getLongX()
            && (aPushes * cm.getButtonA().getLongY()) + (bPushes * cm.getButtonB().getLongY()) == cm.getPrize().getLongY();
    }
}