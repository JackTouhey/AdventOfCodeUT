package com.example.adventofcode;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DayTwelve2024;
import com.example.adventofcode.utils.Coordinate;


class DayTwelve2024Testing {
    @Test
    void isNorthPartOfRegionTest() {
        Coordinate c1 = new Coordinate(0, 1);
        assertTrue(DayTwelve2024.isNorthPartOfRegion(c1, 'R'));
    }
    @Test
    void isNorthPartOfRegionFailState(){
        Coordinate c1 = new Coordinate(0, 1);
        assertTrue(DayTwelve2024.isNorthPartOfRegion(c1, 'V'));
    }
    @Test
    void isNorthPartOfRegionEdgeState(){
        Coordinate c1 = new Coordinate(0, 0);
        assertFalse(DayTwelve2024.isNorthPartOfRegion(c1, 'R'));
    }
}