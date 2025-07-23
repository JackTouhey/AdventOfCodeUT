package com.example.adventofcode;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertFalse(DayTwelve2024.isNorthPartOfRegion(c1, 'V'));
    }
    @Test
    void isNorthPartOfRegionEdgeState(){
        Coordinate c1 = new Coordinate(0, 0);
        assertFalse(DayTwelve2024.isNorthPartOfRegion(c1, 'R'));
    }
    @Test
    void isSouthPartOfRegionTest() {
        Coordinate c1 = new Coordinate(0, 8);
        assertTrue(DayTwelve2024.isSouthPartOfRegion(c1, 'M'));
    }
    @Test
    void isSouthPartOfRegionFailState(){
        Coordinate c1 = new Coordinate(0, 8);
        assertFalse(DayTwelve2024.isSouthPartOfRegion(c1, 'V'));
    }
    @Test
    void isSouthPartOfRegionEdgeState(){
        Coordinate c1 = new Coordinate(0, 9);
        assertFalse(DayTwelve2024.isSouthPartOfRegion(c1, 'M'));
    }
    @Test
    void doesRegionContinueTest(){
        HashSet<Coordinate> plots = new HashSet<>();
        Collections.addAll(plots, new Coordinate(4, 0), new Coordinate(5, 0));
        Coordinate c1 = new Coordinate(4, 1);
        assertTrue(DayTwelve2024.doesRegionContinue(plots, c1, 'I'));
    }
    @Test
    void doesRegionContinueFailState(){
        HashSet<Coordinate> plots = new HashSet<>();
        Collections.addAll(plots, new Coordinate(4, 0), new Coordinate(5, 0), new Coordinate(5, 1));
        Coordinate c1 = new Coordinate(4, 1);
        assertFalse(DayTwelve2024.doesRegionContinue(plots, c1, 'I'));
    }
    @Test
    void checkRegionsLoading(){
        assertEquals(11, DayTwelve2024.getRegionsSize());
    }
    @Test
    void calculatePerimeterTest(){
        assertEquals(8, DayTwelve2024.testCalculatePerimeter());
    }
    @Test 
    void findStartPointTest(){
        Coordinate c1 = new Coordinate(4, 0);
        assertEquals(c1, DayTwelve2024.testFindStartPoint());
    }
    @Test
    void basicCalculateSidesTest(){
        assertEquals(4, DayTwelve2024.basicTestOfCalculateSides());
    }
}