package com.example.adventofcode;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.utils.ClawMachine;
import com.example.adventofcode.utils.Coordinate;

public class ClawMachineTesting {
    private static final ArrayList<ClawMachine> machines = new ArrayList<>();
    @BeforeAll
    static void setUp() {
        ClawMachine c1 = new ClawMachine(new Coordinate(94, 34), 
        new Coordinate(22, 67), new Coordinate(8400, 5400));
        ClawMachine c2 = new ClawMachine(new Coordinate(26, 66), 
        new Coordinate(67, 21), new Coordinate(12748, 12176));
        Collections.addAll(machines, c1, c2);
    }
    //Requires test data 1
    @Test
    void testIsSolvableOnSolvableMachine(){
        assertTrue(machines.get(0).isSolvable());
    }
    @Test
    void testIsSolvableOnUnsolvableMachine(){
        assertFalse(machines.get(1).isSolvable());
    }
}
