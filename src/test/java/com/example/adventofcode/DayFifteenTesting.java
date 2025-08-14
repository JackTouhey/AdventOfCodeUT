package com.example.adventofcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DayFifteen2024;
import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.SystemOut;
import com.example.adventofcode.utils.WarehouseBox;

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
        assertTrue(Arrays.deepEquals(getMoveDoubleBoxLeftResultantGrid(), 
        DayFifteen2024.moveDoubleSizeBoxLeft(getMoveDoubleBoxLeftResultantGrid(), 5, 1)));
    }
    @Test
    void testGetDoubleBoxesToMoveRight(){
        assertEquals(2, DayFifteen2024.getDoubleBoxesToMoveRight(getMoveDoubleBoxRightInitialGrid(),
        new Coordinate(2, 0)));
    }
    @Test
    void testMoveDoubleSizeBoxesRight(){
        System.out.println("Initial: " + Arrays.deepToString(getMoveDoubleBoxRightInitialGrid()));
        System.out.println("Expected resultant: " + Arrays.deepToString(getMoveDoubleBoxRightResultantGrid()));
        System.out.println("Computed resultant: " + Arrays.deepToString(DayFifteen2024.moveDoubleSizeBoxRight(getMoveDoubleBoxRightInitialGrid(), 1, 0)));
        assertTrue(Arrays.deepEquals(getMoveDoubleBoxRightResultantGrid(), 
        DayFifteen2024.moveDoubleSizeBoxRight(getMoveDoubleBoxRightInitialGrid(), 1, 0)));
        assertTrue(Arrays.deepEquals(getMoveDoubleBoxRightResultantGrid(), 
        DayFifteen2024.moveDoubleSizeBoxRight(getMoveDoubleBoxRightResultantGrid(), 2, 0)));
    }
    @Test
    void canBoxMoveUpTesting(){
        WarehouseBox box = new WarehouseBox(new Coordinate(3,3), new Coordinate(4, 3));
        assertTrue(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveGrid(), box));
        assertTrue(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveLeftGrid(), box));
        assertTrue(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveRightGrid(), box));
        assertTrue(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveLeftAndRightGrid(), box));
    }
    @Test
    void canBoxMoveUpTestingFailStates(){
        WarehouseBox box = new WarehouseBox(new Coordinate(3,3), new Coordinate(4, 3));
        assertFalse(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveGridImmovable(), box));
        assertFalse(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveLeftGridImmovable(), box));
        assertFalse(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveRightGridImmovable(), box));
        assertFalse(DayFifteen2024.canDoubleSizeBoxMoveUp(getDoubleSidedBoxAboveLeftAndRightGridImmovable(), box));
    }
    @Test
    void warehouseBoxTesting(){
        WarehouseBox boxAbove = new WarehouseBox(new Coordinate(3,2), new Coordinate(4, 2));
        WarehouseBox boxBelow = new WarehouseBox(new Coordinate(3,3), new Coordinate(4, 3));
        assertTrue(boxAbove.canMoveUp(getDoubleSidedBoxAboveGrid()));
        assertTrue(boxAbove.isAboveClear(getDoubleSidedBoxAboveGrid()));
        assertTrue(boxBelow.canMoveUp(getDoubleSidedBoxAboveGrid()));
        assertFalse(boxBelow.isAboveClear(getDoubleSidedBoxAboveGrid()));
    }
    @Test 
    void getBoxesAboveTesting(){
        WarehouseBox bottomBox = new WarehouseBox(new Coordinate(3,3), new Coordinate(4,3));
        String[][] warehouse = getDoubleSidedBoxAboveLeftAndRightGrid();
        HashSet<WarehouseBox> boxList = new HashSet<>();
        DayFifteen2024.populateDoubleSizeBoxesToMoveUp(warehouse, bottomBox, boxList);
        System.out.println("bottomBox boxes above size: " + bottomBox.getBoxesAbove(warehouse).size());
        System.out.println("BoxList Size: " + boxList.size());
        for(WarehouseBox wb : boxList){
            System.out.println(wb.toString());
        }
        assertTrue(getBoxAboveLeftAndRightSet().equals(boxList));
    }
    @Test 
    void getLargeAmountOfConnectedBoxesTesting(){
        WarehouseBox bottomBox = new WarehouseBox(new Coordinate(3,6), new Coordinate(4,6));
        String[][] warehouse = getLargeAmountOfConnectedBoxesGrid();
        HashSet<WarehouseBox> boxList = new HashSet<>();
        DayFifteen2024.populateDoubleSizeBoxesToMoveUp(warehouse, bottomBox, boxList);
        for(WarehouseBox wb : boxList){
            System.out.println(wb.toString());
        }
        assertTrue(getLargeAmountOfConnectedBoxesSet().equals(boxList));
    }
    //Next 2 require removal of placing the robot from the movement methods to work
    @Test
    void movingLargeAmountOfConnectedBoxesUpTesting(){
        String[][] initialGrid = getLargeAmountOfConnectedBoxesGrid();
        initialGrid = DayFifteen2024.moveDoubleSizeBoxUp(initialGrid, 3, 7, 
        new WarehouseBox(new Coordinate(3, 6), new Coordinate(4,6)));
        assertTrue(Arrays.deepEquals(initialGrid, getLargeAmountOfConnectedBoxesResultantGrid()));
    }
    @Test
    void movingLargeAmountOfConnectedBoxesDownTesting(){
        String[][] initialGrid = getLargeAmountOfConnectedBoxesResultantGrid();
        initialGrid = DayFifteen2024.moveDoubleSizeBoxDown(initialGrid, 3, 0, 
        new WarehouseBox(new Coordinate(3, 1), new Coordinate(4,1)));
        SystemOut.printGrid(initialGrid);
        assertTrue(Arrays.deepEquals(initialGrid, getLargeAmountOfConnectedBoxesGrid()));
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
    String[][] getMoveDoubleBoxRightInitialGrid(){
        return new String[][] {
            {"#", "@", "[", "]", "[", "]", ".", "#"}
        };
    }
    String[][] getMoveDoubleBoxRightResultantGrid(){
        return new String[][] {
            {"#", ".", "@", "[", "]", "[", "]", "#"}
        };
    }
    String[][] getDoubleSidedBoxAboveGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", ".", ".", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    String[][] getDoubleSidedBoxAboveLeftGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", ".", ".", ".", "#"},
            {"#", ".", "[", "]", ".",  ".", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    String[][] getDoubleSidedBoxAboveRightGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", ".", ".", ".", "#"},
            {"#", ".", ".", ".", "[",  "]", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    String[][] getDoubleSidedBoxAboveLeftAndRightGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", ".", ".", ".", "#"},
            {"#", ".", "[", "]", "[",  "]", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    HashSet<WarehouseBox> getBoxAboveLeftAndRightSet(){
        HashSet<WarehouseBox> returnList = new HashSet<>();
        Collections.addAll(returnList, 
        new WarehouseBox(new Coordinate(2, 2), new Coordinate(3, 2)),
        new WarehouseBox(new Coordinate(4, 2),new Coordinate(5,2)));
        return returnList;
    }
    String[][] getLargeAmountOfConnectedBoxesGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", ".", ".", ".", "#"},
            {"#", ".", ".", "[", "]", ".", ".", "#"},
            {"#", ".", "[", "]", ".", ".", ".", "#"},
            {"#", "[", "]", "[", "]", ".", ".", "#"},
            {"#", ".", "[", "]", "[",  "]", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    HashSet<WarehouseBox> getLargeAmountOfConnectedBoxesSet(){
        HashSet<WarehouseBox> returnList = new HashSet<>();
        Collections.addAll(returnList, 
        new WarehouseBox(new Coordinate(2, 5), new Coordinate(3, 5)),
        new WarehouseBox(new Coordinate(4, 5),new Coordinate(5,5)),
        new WarehouseBox(new Coordinate(1, 4), new Coordinate(2, 4)),
        new WarehouseBox(new Coordinate(3, 4), new Coordinate(4, 4)),
        new WarehouseBox(new Coordinate(2, 3), new Coordinate(3, 3)),
        new WarehouseBox(new Coordinate(1, 2), new Coordinate(2, 2)),
        new WarehouseBox(new Coordinate(3, 2), new Coordinate(4, 2)));
        return returnList;
    }
    String[][] getLargeAmountOfConnectedBoxesResultantGrid(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", "[", "]", ".", ".", "#"},
            {"#", ".", "[", "]", ".", ".", ".", "#"},
            {"#", "[", "]", "[", "]", ".", ".", "#"},
            {"#", ".", "[", "]", "[",  "]", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", ".", ".", ".", ".", ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    String[][] getDoubleSidedBoxAboveGridImmovable(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", "#", ".", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    String[][] getDoubleSidedBoxAboveLeftGridImmovable(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", "#", ".", ".", ".", ".", "#"},
            {"#", ".", "[", "]", ".",  ".", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    String[][] getDoubleSidedBoxAboveRightGridImmovable(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", ".", "#", ".", "#"},
            {"#", ".", ".", ".", "[",  "]", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
    String[][] getDoubleSidedBoxAboveLeftAndRightGridImmovable(){
        return new String[][] {
            {"#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", ".", ".", ".", ".", "#", ".", "#"},
            {"#", ".", "[", "]", "[",  "]", ".", "#"},
            {"#", ".", ".", "[", "]",  ".", ".", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#"},
        };
    }
}
