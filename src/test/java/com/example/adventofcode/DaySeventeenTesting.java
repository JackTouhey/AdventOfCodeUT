package com.example.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DaySeventeen2024;

public class DaySeventeenTesting {
    private final String filePath = "DataFiles\\DaySeventeenTestData.txt";
    @Test
    void getComboOperandTesting(){
        DaySeventeen2024 day17 = new DaySeventeen2024(filePath);
        assertEquals(Integer.valueOf(0), day17.getComboOperand(0));
        assertEquals(Integer.valueOf(1), day17.getComboOperand(1));
        assertEquals(Integer.valueOf(2), day17.getComboOperand(2));
        assertEquals(Integer.valueOf(3), day17.getComboOperand(3));
        assertEquals(Integer.valueOf(729), day17.getComboOperand(4));
        assertEquals(Integer.valueOf(0), day17.getComboOperand(5));
        assertEquals(Integer.valueOf(0), day17.getComboOperand(6));
        assertEquals(Integer.valueOf(-1), day17.getComboOperand(7));
    }
    @Test
    void testAllADV(){
        assertEquals(729, testAdvInstruction(0));
        assertEquals(364, testAdvInstruction(1));
        assertEquals(182, testAdvInstruction(2));
        assertEquals(91, testAdvInstruction(3));
        assertEquals(0, testAdvInstruction(4));
        assertEquals(729, testAdvInstruction(5));
        assertEquals(729, testAdvInstruction(6));
        assertEquals(729, testAdvInstruction(7));
    }
    @Test
    void testAllBxl(){
        assertEquals(0, testBxlInstruction(0));
        assertEquals(1, testBxlInstruction(1));
        assertEquals(2, testBxlInstruction(2));
        assertEquals(3, testBxlInstruction(3));
        assertEquals(4, testBxlInstruction(4));
        assertEquals(5, testBxlInstruction(5));
        assertEquals(6, testBxlInstruction(6));
        assertEquals(7, testBxlInstruction(7));

        assertEquals(1, testDoubleBxlInstruction(0));
        assertEquals(0, testDoubleBxlInstruction(1));
        assertEquals(3, testDoubleBxlInstruction(2));
        assertEquals(2, testDoubleBxlInstruction(3));
        assertEquals(5, testDoubleBxlInstruction(4));
        assertEquals(4, testDoubleBxlInstruction(5));
        assertEquals(7, testDoubleBxlInstruction(6));
        assertEquals(6, testDoubleBxlInstruction(7));
    }
    @Test
    void testAllbst(){
        assertEquals(0, testBstInstruction(0));
        assertEquals(1, testBstInstruction(1));
        assertEquals(2, testBstInstruction(2));
        assertEquals(3, testBstInstruction(3));
        assertEquals(1, testBstInstruction(4));
        assertEquals(0, testBstInstruction(5));
        assertEquals(0, testBstInstruction(6));
        assertEquals(0, testBstInstruction(7));
    }
    @Test
    void testJnz(){
        DaySeventeen2024 day17 = new DaySeventeen2024(filePath);
        day17.jnz(3);
        assertEquals(3, day17.getInstructionPointer());
        day17.setRegisterA(0);
        day17.jnz(4);
        assertEquals(5, day17.getInstructionPointer());
    }
    int testAdvInstruction(int literalOperand){
        DaySeventeen2024 day17 = new DaySeventeen2024(filePath);
        day17.adv(literalOperand);
        return day17.getRegisterA();
    }
    int testBxlInstruction(int literalOperand){
        DaySeventeen2024 day17 = new DaySeventeen2024(filePath);
        day17.bxl(literalOperand);
        return day17.getRegisterB();
    }
    int testDoubleBxlInstruction(int literalOperand){
        DaySeventeen2024 day17 = new DaySeventeen2024(filePath);
        day17.bxl(1);
        day17.bxl(literalOperand);
        return day17.getRegisterB();
    }
    int testBstInstruction(int literalOperand){
        DaySeventeen2024 day17 = new DaySeventeen2024(filePath);
        day17.bst(literalOperand);
        return day17.getRegisterB();
    }
}
