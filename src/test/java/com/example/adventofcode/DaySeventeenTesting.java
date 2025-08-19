package com.example.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DaySeventeen2024;

public class DaySeventeenTesting {
    @Test
    void getComboOperandTesting(){
        DaySeventeen2024 day17 = new DaySeventeen2024("DataFiles\\DaySeventeenTestData.txt");
        assertEquals(Integer.valueOf(0), day17.getComboOperand(0));
        assertEquals(Integer.valueOf(1), day17.getComboOperand(1));
        assertEquals(Integer.valueOf(2), day17.getComboOperand(2));
        assertEquals(Integer.valueOf(3), day17.getComboOperand(3));
        assertEquals(Integer.valueOf(729), day17.getComboOperand(4));
        assertEquals(Integer.valueOf(0), day17.getComboOperand(5));
        assertEquals(Integer.valueOf(0), day17.getComboOperand(6));
        assertEquals(Integer.valueOf(-1), day17.getComboOperand(7));
    }
}
