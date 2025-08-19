package com.example.adventofcode.solutions;

import java.math.BigDecimal;
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
    public int getRegisterA(){return this.registerA;}
    public int getRegisterB(){return this.registerB;}
    public int getRegisterC(){return this.registerC;}
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
    public void adv (int literalOperand){
        if(literalOperand < 7){
            int comboOperand = getComboOperand(literalOperand);
            double divisionResult = this.registerA / Math.pow(2, comboOperand);;
            this.registerA = (int) Math.floor(divisionResult);
            instructionPointer += 2;
        }
    }
    public void bxl (int literalOperand){
        this.registerB = this.registerB ^ literalOperand;
        instructionPointer += 2;
    }
    public void bst (int literalOperand){
        if(literalOperand < 7){
            int comboOperand = getComboOperand(literalOperand);
            this.registerB = comboOperand % 8;
            instructionPointer += 2;
        }
    }
}
