package com.example.adventofcode.solutions;

import java.util.ArrayList;

import com.example.adventofcode.utils.DataLoader;

public class DaySeventeen2024 {
    private int registerA;
    private int registerB;
    private int registerC;
    private ArrayList<Integer> program;
    private int instructionPointer;
    public DaySeventeen2024(String filePath){
        this.registerA = DataLoader.daySeventeenRegisterA(filePath);
        this.registerB = DataLoader.daySeventeenRegisterB(filePath);
        this.registerC = DataLoader.daySeventeenRegisterC(filePath);
        this.program = DataLoader.daySeventeenProgram(filePath);
        System.out.println(program);
    }
}
