package com.example.adventofcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DayFourteen2024;

public class DayFourteen2024Testing {
    @Test
    void isChristmasTreeTest(){
        String[][] grid = {
            {".",".",".",".","1",".",".",".","."},
            {".",".",".","1","1","1",".",".","."},
            {".",".","1","1","1","1","1",".","."},
            {".","1","1","1","1","1","1","1","."},
            {"1","1","1","1","1","1","1","1","1"},
        };
        assertTrue(DayFourteen2024.isChristmasTree(grid));
    }
    @Test
    void isChristmasTreeTestFailState(){
        String[][] grid = {
            {".",".",".",".","1",".",".",".","."},
            {".",".",".","1","1","1",".",".","."},
            {".",".","1","1","1","1","1",".","."},
            {".","1","1","1","1","1","1","1","."},
            {"1","1","1","1","1","1","1","1","."},
        };
        assertFalse(DayFourteen2024.isChristmasTree(grid));
    }
}
