package com.example.adventofcode;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DayFourteen2024;
import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.Robot;

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
    @Test
    void getRobotHeightTests(){
        Robot r1 = new Robot(new Coordinate(0, 1), new Coordinate(0, 0), 10, 10);
        Robot r2 = new Robot(new Coordinate(0, 5), new Coordinate(0, 0), 10, 10);
        Robot r3 = new Robot(new Coordinate(0, 10), new Coordinate(0, 0), 10, 10);
        ArrayList<Robot> robots = new ArrayList<>();
        Collections.addAll(robots, r1, r2, r3);
        assertEquals(1, DayFourteen2024.getHighestRobotY(robots));
        assertEquals(10, DayFourteen2024.getLowestRobotY(robots));
    }
}
