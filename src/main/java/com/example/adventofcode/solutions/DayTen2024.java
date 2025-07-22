package com.example.adventofcode.solutions;

import java.util.ArrayList;
import java.util.HashSet;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayTen2024 {
    private static final Integer[][] topographicMap = DataLoader.generateIntegerGrid("DataFiles\\DayTenData.txt");
    private static final ArrayList<Trailhead> trailheads = findTrailheads();
    public static void main(String[] args) {
        for(Trailhead head : trailheads){
            System.out.print("Beginning populate route of trailhead:");
            head.printSelf();
            Trail startTrail = new Trail(head);
            populateRoutes(head, startTrail);
        }
        int peakCount = 0;
        int routeCount = 0;
        for(Trailhead head : trailheads){
            int numPeaks = head.getNumberOfReachablePeaks();
            int numRoutes = head.getRoutes().size();
            peakCount += numPeaks;
            routeCount += numRoutes;
            System.out.println("Trailhead at y: " + head.getStart().getY() + " x: " + head.getStart().getX() + " had numPeaks: " 
            + numPeaks + " currentPeakCount: " + peakCount + " and numRoutes: " + numRoutes + " currentRouteCount: " + routeCount);
        }
    }
    private static ArrayList<Trailhead> findTrailheads(){
        ArrayList<Trailhead> returnList = new ArrayList<>();
        for(int y = 0; y < topographicMap.length; y++){
            for(int x = 0; x < topographicMap[y].length; x++){
                if(topographicMap[y][x] == 0){
                    returnList.add(new Trailhead(new Coordinate(x, y)));
                }
            }
        }
        return returnList;
    }
    private static Trail cloneTrail(Trail trailToClone){
        Trail returnTrail = new Trail(trailToClone.getTrailhead());
        for(Coordinate c : trailToClone.getRoute()){
            if(!c.equals(trailToClone.getTrailhead().getStart())){
                returnTrail.addCoordinate(c);
            }
        }
        return returnTrail;
    }
    private static void populateRoutes(Trailhead head, Trail currentTrail){
        if(topographicMap[currentTrail.getCurrentPosition().getY()][currentTrail.getCurrentPosition().getX()] == 9){
            System.out.print("Adding following trail to trailhead: ");
            head.printSelf();
            currentTrail.printSelf();
            head.addRoute(currentTrail);
            head.addPeak(new Coordinate(currentTrail.getCurrentPosition().getX(), currentTrail.getCurrentPosition().getY()));
        }
        else if(!checkIfNextStep(currentTrail)){}
        else{
            if(checkIfNorthStep(currentTrail)){
                Coordinate nextStep = new Coordinate(currentTrail.getCurrentPosition().getX(), currentTrail.getCurrentPosition().getY()-1);
                Trail continuedTrail = cloneTrail(currentTrail);
                continuedTrail.addCoordinate(nextStep);
                System.out.println("Added north step, trail now:");
                continuedTrail.printSelf();
                populateRoutes(head, continuedTrail);
            }
            if(checkIfEastStep(currentTrail)){
                Coordinate nextStep = new Coordinate(currentTrail.getCurrentPosition().getX() + 1, currentTrail.getCurrentPosition().getY());
                Trail continuedTrail = cloneTrail(currentTrail);
                continuedTrail.addCoordinate(nextStep);
                System.out.println("Added east step, trail now:");
                continuedTrail.printSelf();
                populateRoutes(head, continuedTrail);
            }
            if(checkIfSouthStep(currentTrail)){
                Coordinate nextStep = new Coordinate(currentTrail.getCurrentPosition().getX(), currentTrail.getCurrentPosition().getY()+1);
                Trail continuedTrail = cloneTrail(currentTrail);
                continuedTrail.addCoordinate(nextStep);
                System.out.println("Added south step, trail now:");
                continuedTrail.printSelf();
                populateRoutes(head, continuedTrail);
            }
            if(checkIfWestStep(currentTrail)){
                Coordinate nextStep = new Coordinate(currentTrail.getCurrentPosition().getX() - 1, currentTrail.getCurrentPosition().getY());
                Trail continuedTrail = cloneTrail(currentTrail);
                continuedTrail.addCoordinate(nextStep);
                System.out.println("Added west step, trail now:");
                continuedTrail.printSelf();
                populateRoutes(head, continuedTrail);
            }
        }
    }
    private static Boolean checkIfNextStep(Trail currentTrail){
        return checkIfNorthStep(currentTrail) || checkIfEastStep(currentTrail) || checkIfSouthStep(currentTrail) || checkIfWestStep(currentTrail);
    }
    private static Boolean checkIfNorthStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentY > 0){
            if(topographicMap[currentY - 1][currentX].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
    }
    private static Boolean checkIfSouthStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentY < topographicMap.length-1){
            if(topographicMap[currentY + 1][currentX].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
    }
    private static Boolean checkIfEastStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentX < topographicMap[currentY].length-1){
            if(topographicMap[currentY][currentX + 1].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
    }
    private static Boolean checkIfWestStep(Trail currentTrail){
        Boolean isNextStep = false;
        int currentX = currentTrail.getCurrentPosition().getX();
        int currentY = currentTrail.getCurrentPosition().getY();
        Integer nextElevation = topographicMap[currentY][currentX] + 1;
        if(currentX > 0){
            if(topographicMap[currentY][currentX - 1].equals(nextElevation)){
                isNextStep = true;
            }
        }
        return isNextStep;
    }
}
class Trailhead {
    private final Coordinate start;
    private final HashSet<Coordinate> reachablePeaks = new HashSet<>();
    ArrayList<Trail> routes = new ArrayList<>();
    public Trailhead(Coordinate startCoordinate){
        this.start = startCoordinate;
    }
    public void addRoute(Trail r){routes.add(r);}
    public Coordinate getStart(){return this.start;}
    public void printSelf(){
        System.out.println("Trailhead coordinate y: " + start.getY() + " x: " + start.getX());
    }
    public ArrayList<Trail> getRoutes(){return this.routes;}
    public void addPeak(Coordinate c){
        Boolean contained = false;
        for(Coordinate peak : reachablePeaks){
            if(c.getX() == peak.getX() && c.getY() == peak.getY()){
                contained = true;
            }
        }
        if(!contained){
            reachablePeaks.add(c);
        }
    }
    public Integer getNumberOfReachablePeaks(){return reachablePeaks.size();}
}
class Trail {
    Trailhead trailhead;
    ArrayList<Coordinate> route = new ArrayList<>();
    public Trail(Trailhead trailhead){
        this.trailhead = trailhead;
        route.add(trailhead.getStart());
    }
    public void addCoordinate(Coordinate c){
        route.add(c);
    }
    public Coordinate getCurrentPosition(){
        return route.get(route.size()-1);
    }
    public ArrayList<Coordinate> getRoute(){return this.route;}
    public Trailhead getTrailhead(){return this.trailhead;}
    public void printSelf(){
        for(Coordinate c : route){
            System.out.print(c.getY()+","+ c.getX()+" ");
        }
        System.out.println();
    }
}