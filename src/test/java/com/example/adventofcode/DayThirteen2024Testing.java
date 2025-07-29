package com.example.adventofcode;

import java.util.HashSet;

import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.DataLoader;

public class DayThirteen2024Testing {
    private static final HashSet<ClawMachine> clawMachines = DataLoader.loadDayThirteen("DataFiles\\DayThirteenTestData.txt");

    // @Test
    // void testIsButtonACheapest(){
    //     Coordinate a = new Coordinate(100, 100);
    //     Coordinate b = new Coordinate(1,1);
    //     Coordinate prize = new Coordinate(10000,10000);
    //     ClawMachine cm = new ClawMachine(a, b, prize);
    //     assertTrue(DayThirteen2024.isButtonACheapest(cm));
    // }
    // @Test
    // void testIsButtonBCheapest(){
    //     Coordinate b = new Coordinate(100, 100);
    //     Coordinate a = new Coordinate(1,1);
    //     Coordinate prize = new Coordinate(10000,10000);
    //     ClawMachine cm = new ClawMachine(a, b, prize);
    //     assertFalse(DayThirteen2024.isButtonACheapest(cm));
    // }
    // @Test
    // void testFindButtonAPushes(){
    //     Coordinate a = new Coordinate(94, 34);
    //     Coordinate b = new Coordinate(22,67);
    //     Coordinate prize = new Coordinate(8400,5400);
    //     ClawMachine cm = new ClawMachine(a, b, prize);
    //     assertEquals(80, DayThirteen2024.getAandBcount(cm).get("A"));
    // }
}
