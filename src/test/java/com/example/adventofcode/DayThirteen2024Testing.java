package com.example.adventofcode;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.solutions.DayThirteen2024;
import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.DataLoader;

public class DayThirteen2024Testing {
    private static final HashSet<ClawMachine> clawMachines = DataLoader.loadDayThirteen("DataFiles\\DayThirteenTestData.txt");

    @Test
    void testIsButtonACheapest(){
        Coordinate a = new Coordinate(100, 100);
        Coordinate b = new Coordinate(1,1);
        Coordinate prize = new Coordinate(10000,10000);
        ClawMachine cm = new ClawMachine(a, b, prize);
        assertTrue(DayThirteen2024.isButtonACheapest(cm));
    }
    @Test
    void testIsButtonBCheapest(){
        Coordinate b = new Coordinate(100, 100);
        Coordinate a = new Coordinate(1,1);
        Coordinate prize = new Coordinate(10000,10000);
        ClawMachine cm = new ClawMachine(a, b, prize);
        assertFalse(DayThirteen2024.isButtonACheapest(cm));
    }
}
