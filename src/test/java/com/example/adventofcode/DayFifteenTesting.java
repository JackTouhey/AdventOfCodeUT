package com.example.adventofcode;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DayFifteen2024;
import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayFifteenTesting {
    @Test
    void testFindRobotCoord(){
        assertEquals(new Coordinate(3, 2), DayFifteen2024.findRobotCoordinate(getTestGrid()));
    }
    @Test
    void testCanBoxMoveLeftTrue(){
        assertTrue(DayFifteen2024.canBoxMoveLeft(getTestGrid(), new Coordinate(3, 1)));
    }
    @Test
    void testCanBoxMoveLeftFalse(){
        assertFalse(DayFifteen2024.canBoxMoveLeft(getTestGrid(), new Coordinate(2, 2)));
    }
    @Test
    void testBoxesToMoveLeft(){
        assertEquals(1, (Integer)DayFifteen2024.getSingleBoxesToMoveLeft(getTestGrid(), 
        new Coordinate(3, 1)));
        assertEquals(2, (Integer)DayFifteen2024.getSingleBoxesToMoveLeft(getTestGrid(), 
        new Coordinate(2, 2)));
        assertEquals(3, (Integer)DayFifteen2024.getSingleBoxesToMoveLeft(getTestGrid(), 
        new Coordinate(4, 4)));
    }
    @Test
    void testGetDoubleSizeBoxesToMoveLeft(){
        assertEquals(2, (Integer)DayFifteen2024.getDoubleBoxesToMoveLeft(getDoubleSizeTestGrid(), 
        new Coordinate(5, 2)));
        assertEquals(1, (Integer)DayFifteen2024.getDoubleBoxesToMoveLeft(getDoubleSizeTestGrid(), 
        new Coordinate(7, 3)));
        assertEquals(3, (Integer)DayFifteen2024.getDoubleBoxesToMoveLeft(getDoubleSizeTestGrid(), 
        new Coordinate(9, 4)));
        assertEquals(2, (Integer)DayFifteen2024.getDoubleBoxesToMoveLeft(getMoveDoubleBoxLeftInitialGrid(), 
        new Coordinate(5, 1)));
    }
    @Test
    void testMoveDoubleSizeBoxesLeft(){
        assertTrue(Arrays.deepEquals(getMoveDoubleBoxLeftResultantGrid(), 
        DayFifteen2024.moveDoubleSizeBoxLeft(getMoveDoubleBoxLeftInitialGrid(), 6, 1)));

    }
    String[][] getTestGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", "O", ".", "#"},
            {"#", "O", "O", "@", ".", "#"},
            {"#", ".", ".", "O", ".", "#"},
            {"#", ".", "O", "O", "O", "#"},
            {"#", "#", "#", "#", "#", "#"}
        };
    }
    String[][] getDoubleSizeTestGrid(){
        return DataLoader.dayFifteenDoubleSizeWarehouse(getTestGrid());
    }
    String[][] getMoveDoubleBoxLeftInitialGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", "[", "]", "[", "]", "@", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"}
        };
    }
    String[][] getMoveDoubleBoxLeftResultantGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", "[", "]", "[", "]", "@", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"}
        };
    }
}
