package com.example.adventofcode.utils;

import java.util.Objects;

public class Coordinate {
    private final long x;
    private final long y;
    public Coordinate(int x, int y) {
        this((long) x, (long) y);
    }
    public Coordinate(long x, long y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { 
        return (int) this.x; 
    }
    public int getY() { 
        return (int) this.y; 
    }
    public long getLongX() {
        return this.x;
    }
    public long getLongY() {
        return this.y;
    }
    public Coordinate addCoordinate(Coordinate c){
        return new Coordinate(this.x + c.x, this.y + c.y);
    }
    public Coordinate addCoordinate(long deltaX, long deltaY) {
        return new Coordinate(this.x + deltaX, this.y + deltaY);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return x == that.x && y == that.y;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public String toString() {
        return "(y:" + y + ", x:" + x + ")";
    }
}