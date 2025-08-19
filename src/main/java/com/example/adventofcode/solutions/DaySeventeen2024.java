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
    public int getInstructionPointer(){return this.instructionPointer;}
    public void setRegisterA(int newValue){this.registerA = newValue;}
    public void runProgram(){
        while(instructionPointer < program.size() - 2){
            switch(program.get(instructionPointer)){
                case 0 -> adv(program.get(instructionPointer+1));
                case 1 -> bxl(program.get(instructionPointer+1));
                case 2 -> bst(program.get(instructionPointer+1));
                case 3 -> jnz(program.get(instructionPointer+1));
                case 4 -> bxc();
                case 5 -> out(program.get(instructionPointer+1));
                case 6 -> bdv(program.get(instructionPointer+1));
                case 7 -> cdv(program.get(instructionPointer+1));
            }
        }
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
    public void jnz (int literalOperand){
        this.instructionPointer = this.registerA == 0 ? this.instructionPointer += 2 : literalOperand;
    }
    public void bxc (){
        this.registerB = this.registerB ^ this.registerC;
        instructionPointer += 2;
    }
    public void out (int literalOperand){
        if(literalOperand < 7){
            int comboOperand = getComboOperand(literalOperand);
            int output = comboOperand % 8;
            System.out.print(output + ",");
        }
        instructionPointer += 2;
    }
    public void bdv (int literalOperand){
        if(literalOperand < 7){
            int comboOperand = getComboOperand(literalOperand);
            double divisionResult = this.registerA / Math.pow(2, comboOperand);;
            this.registerB = (int) Math.floor(divisionResult);
            instructionPointer += 2;
        }
    }
    public void cdv (int literalOperand){
        if(literalOperand < 7){
            int comboOperand = getComboOperand(literalOperand);
            double divisionResult = this.registerA / Math.pow(2, comboOperand);;
            this.registerC = (int) Math.floor(divisionResult);
            instructionPointer += 2;
        }
    }
}
