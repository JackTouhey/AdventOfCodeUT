package com.example.adventofcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.adventofcode.utils.Coordinate;
import com.example.adventofcode.utils.Robot;

public class RobotTesting {
    @Test
    void moveOffLeftSideTest(){
        Coordinate start = new Coordinate(0,0);
        Coordinate velocity = new Coordinate(-3, 0);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(8, r.getPosition().getX());
    }
    @Test
    void moveOffRightSideTest(){
        Coordinate start = new Coordinate(11,0);
        Coordinate velocity = new Coordinate(3, 0);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(3, r.getPosition().getX());
    }
    @Test
    void moveRightWithinBounds(){
        Coordinate start = new Coordinate(0,0);
        Coordinate velocity = new Coordinate(3, 0);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(3, r.getPosition().getX());
    }
    @Test
    void moveLeftWithinBounds(){
        Coordinate start = new Coordinate(5,0);
        Coordinate velocity = new Coordinate(-3, 0);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(2, r.getPosition().getX());
    }
    @Test
    void moveOffTopSideTest(){
        Coordinate start = new Coordinate(0,0);
        Coordinate velocity = new Coordinate(0, -3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(4, r.getPosition().getY());
    }
    @Test
    void moveOffBottomSideTest(){
        Coordinate start = new Coordinate(0,7);
        Coordinate velocity = new Coordinate(0, 3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(3, r.getPosition().getY());
    }
    @Test
    void moveUpWithinBounds(){
        Coordinate start = new Coordinate(0,5);
        Coordinate velocity = new Coordinate(0, -3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(2, r.getPosition().getY());
    }
    @Test
    void moveDownWithinBounds(){
        Coordinate start = new Coordinate(0,2);
        Coordinate velocity = new Coordinate(0, 3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(5, r.getPosition().getY());
    }
    
}
