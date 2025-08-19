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
        this.instructionPointer = 0;
    }
    public int getComboOperand(int literalOperand){
        return switch(literalOperand){
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> this.registerA;
            case 5 -> this.registerB;
            case 6 -> this.registerC;
            default -> -1;
        };
    }
}
