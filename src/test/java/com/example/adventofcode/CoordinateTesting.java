package com.example.adventofcode;

import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.utils.Coordinate;

class CoordinateTesting {

    @BeforeEach
    void setUp() {

    }
    
    @Test
    @DisplayName("Hashset coordinate test")
    void testCoordinate() {
        Coordinate c1 = new Coordinate(1, 1);
        Coordinate c2 = new Coordinate(1, 1);
        Coordinate c3 = new Coordinate(1, 2);

        HashSet<Coordinate> coordSet = new HashSet<>();
        Collections.addAll(coordSet, c1, c2, c3);
        assertEquals(2, coordSet.size());
    }
}