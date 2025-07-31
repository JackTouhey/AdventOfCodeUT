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
        Coordinate start = new Coordinate(10,0);
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
        Coordinate start = new Coordinate(0,6);
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
    @Test
    void moveOffNWsideTest(){
        Coordinate start = new Coordinate(0,0);
        Coordinate velocity = new Coordinate(-3, -3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(8, r.getPosition().getX());
        assertEquals(4, r.getPosition().getY());
    }
    @Test
    void moveOffNEsideTest(){
        Coordinate start = new Coordinate(10,0);
        Coordinate velocity = new Coordinate(3, -3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(3, r.getPosition().getX());
        assertEquals(4, r.getPosition().getY());
    }
    @Test
    void moveOffSWsideTest(){
        Coordinate start = new Coordinate(0,6);
        Coordinate velocity = new Coordinate(-3, 3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(8, r.getPosition().getX());
        assertEquals(3, r.getPosition().getY());
    }
    @Test
    void moveOffSEsideTest(){
        Coordinate start = new Coordinate(10,6);
        Coordinate velocity = new Coordinate(3, 3);
        Robot r = new Robot(start, velocity, 7, 11);
        r.moveOnce();
        assertEquals(3, r.getPosition().getX());
        assertEquals(3, r.getPosition().getY());
    }
    @Test
    void testGetQuadrant(){
        int height = 7;
        int width = 11;
        Coordinate velocity = new Coordinate(0, 0);
        Robot NW = new Robot(new Coordinate(0, 0), velocity, height, width);
        Robot NE = new Robot(new Coordinate(8, 0), velocity, height, width);
        Robot SW = new Robot(new Coordinate(0, 5), velocity, height, width);
        Robot SE = new Robot(new Coordinate(8, 5), velocity, height, width);
        Robot centre = new Robot(new Coordinate(5, 3), velocity, height, width);
        Robot midLeft = new Robot(new Coordinate(0, 3), velocity, height, width);
        Robot midTop = new Robot(new Coordinate(5, 0), velocity, height, width);
        Robot midRight = new Robot(new Coordinate(10, 3), velocity, height, width);
        Robot midBottom = new Robot(new Coordinate(5, 10), velocity, height, width);
        assertEquals("NW", NW.getQuadrant());
        assertEquals("NE", NE.getQuadrant());
        assertEquals("SW", SW.getQuadrant());
        assertEquals("SE", SE.getQuadrant());
        assertEquals("No Quadrant", centre.getQuadrant());
        assertEquals("No Quadrant", midLeft.getQuadrant());
        assertEquals("No Quadrant", midTop.getQuadrant());
        assertEquals("No Quadrant", midRight.getQuadrant());
        assertEquals("No Quadrant", midBottom.getQuadrant());
    }
}