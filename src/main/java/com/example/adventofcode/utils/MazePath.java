package com.example.adventofcode.utils;

import java.util.ArrayList;

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
}
