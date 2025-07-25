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
    //Requires original test data
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
    //Requires test data 2
    @Test
    void isConvexCornerTopLeft(){
        Coordinate c1 = new Coordinate(0, 0);
        assertTrue(DayTwelve2024.isConvexCorner(c1, 'A'));
    }
    @Test
    void isConvexCornerTopRight(){
        Coordinate c1 = new Coordinate(5, 0);
        assertTrue(DayTwelve2024.isConvexCorner(c1, 'A'));
    }
    @Test
    void isConvexCornerBottomLeft(){
        Coordinate c1 = new Coordinate(0, 5);
        assertTrue(DayTwelve2024.isConvexCorner(c1, 'A'));
    }
    @Test
    void isConvexCornerBottomRight(){
        Coordinate c1 = new Coordinate(5, 5);
        assertTrue(DayTwelve2024.isConvexCorner(c1, 'A'));
    }
    @Test
    void isConvexCornerFailState(){
        Coordinate c1 = new Coordinate(1, 1);
        assertFalse(DayTwelve2024.isConvexCorner(c1, 'A'));
    }
    // @Test 
    // void isConcaveCornerTopLeft(){
    //     Coordinate c1 = new Coordinate(0, 2);
    //     assertTrue(DayTwelve2024.isConcaveCorner(c1, 'A'));
    // }
    // @Test 
    // void isConcaveCornerTopRight(){
    //     Coordinate c1 = new Coordinate(5, 0);
    //     assertTrue(DayTwelve2024.isConcaveCorner(c1, 'A'));
    // }
    // @Test 
    // void isConcaveCornerBottomLeft(){
    //     Coordinate c1 = new Coordinate(0, 5);
    //     assertTrue(DayTwelve2024.isConcaveCorner(c1, 'A'));
    // }
    // @Test 
    // void isConcaveCornerBottomRight(){
    //     Coordinate c1 = new Coordinate(3, 5);
    //     assertTrue(DayTwelve2024.isConcaveCorner(c1, 'A'));
    // }
    @Test
    void isConcaveCornerFailState(){
        Coordinate c1 = new Coordinate(1, 1);
        assertFalse(DayTwelve2024.isConvexCorner(c1, 'A'));
    }
}