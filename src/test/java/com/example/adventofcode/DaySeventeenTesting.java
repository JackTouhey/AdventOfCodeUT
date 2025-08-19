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
    int testAdvInstruction(int literalOperand){
        DaySeventeen2024 day17 = new DaySeventeen2024(filePath);
        day17.adv(literalOperand);
        return day17.getRegisterA();
    }
}
