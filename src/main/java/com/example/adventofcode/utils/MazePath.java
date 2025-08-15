package com.example.adventofcode.utils;

import java.util.ArrayList;
import java.util.Objects;

public class MazePath {
    private ArrayList<Character> steps;
    public MazePath(){
        steps = new ArrayList<>();
    }
    public MazePath(MazePath priorPath){
        for(Character c : priorPath.getSteps()){
            steps.add(c);
        }
    }
    public ArrayList<Character> getSteps(){return this.steps;}
    public void addStep(Character c){steps.add(c);}
    
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
