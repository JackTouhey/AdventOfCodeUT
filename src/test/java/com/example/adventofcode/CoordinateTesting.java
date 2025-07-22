package com.example.adventofcode;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.utils.Coordinate;

class CoordinateTesting {
    private final HashSet<Coordinate> coordSet = new HashSet<>();;
    @BeforeAll
    void setUp() {
        Coordinate c1 = new Coordinate(1, 1);
        Coordinate c2 = new Coordinate(1, 1);
        Coordinate c3 = new Coordinate(1, 2);

        Collections.addAll(coordSet, c1, c2, c3);
    }
    @Test
    @DisplayName("Hashset coordinate test")
    void testHashSetSize() {
        assertEquals(2, coordSet.size());
    }

    @Test
    void testHashSetContains(){
        Coordinate c1 = new Coordinate(1, 1);
        assertTrue(coordSet.contains(c1));
    }
    @Test
    void testHashSetContainsFailState(){
        Coordinate c1 = new Coordinate(2, 2);
        assertTrue(coordSet.contains(c1));
    }
}