package com.example.adventofcode.utils;

import java.util.ArrayList;
import java.util.Objects;

public class MazePath {
    private ArrayList<Character> steps;
    private char currentDirection;
    private Coordinate currentLocation;
    public MazePath(Coordinate startLocation){
        steps = new ArrayList<>();
        this.currentDirection = '>';
        this.currentLocation = startLocation;
    }
    public MazePath(MazePath priorPath){
        for(Character c : priorPath.getSteps()){
            steps.add(c);
        }
        this.currentDirection = priorPath.getCurrentDirection();
        this.currentLocation = priorPath.getCurrentLocation();
    }
    public MazePath(MazePath priorPath, char currentDirection, Coordinate currentLocation){
        for(Character c : priorPath.getSteps()){
            steps.add(c);
        }
        this.currentDirection = currentDirection;
        this.currentLocation = currentLocation;
    }
    public ArrayList<Character> getSteps(){return this.steps;}
    public char getCurrentDirection(){return this.currentDirection;}
    public Coordinate getCurrentLocation(){return this.currentLocation;}
    public void setCurrentLocation(Coordinate newLocation){this.currentLocation = newLocation;}
    public void takeStep(){
        steps.add(currentDirection);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MazePath mazePath = (MazePath) obj;
        return Objects.equals(steps, mazePath.steps);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(steps);
    }
}
