package com.example.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DaySixteen2024;
import com.example.adventofcode.utils.Coordinate;

public class DaySixteenTesting {
    private final DaySixteen2024 day16 = new DaySixteen2024("DataFiles\\DaySixteenTestData.txt");
    @Test
    void testGetMazeStartAndEnd(){
        assertEquals(day16.getMazeStart(), new Coordinate(1, 13));
        assertEquals(day16.getMazeEnd(), new Coordinate(13, 1));
    }
    @Test
    void testGetLowestPathScore(){
        assertEquals(7036, day16.getLowestPathScore());
    }
}
