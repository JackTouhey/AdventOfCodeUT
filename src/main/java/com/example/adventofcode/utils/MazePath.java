package com.example.adventofcode.utils;

import java.util.ArrayList;
import java.util.Objects;

public class MazePath {
    private ArrayList<Character> steps;
    private char currentDirection;
    public MazePath(){
        steps = new ArrayList<>();
        this.currentDirection = '>';
    }
    public MazePath(MazePath priorPath){
        for(Character c : priorPath.getSteps()){
            steps.add(c);
        }
        this.currentDirection = priorPath.getCurrentDirection();
    }
    public MazePath(MazePath priorPath, char currentDirection){
        for(Character c : priorPath.getSteps()){
            steps.add(c);
        }
        this.currentDirection = currentDirection;
    }
    public ArrayList<Character> getSteps(){return this.steps;}
    public void addStep(Character c){steps.add(c);}
    public char getCurrentDirection(){return this.currentDirection;}
    public void changeDirection(char newDirection){
        this.currentDirection = newDirection;
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
