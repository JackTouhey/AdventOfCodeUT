package com.example.adventofcode;

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
}
