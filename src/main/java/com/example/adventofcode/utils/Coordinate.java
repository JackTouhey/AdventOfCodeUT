package com.example.adventofcode.utils;

import java.util.Objects;

public class Coordinate {
    private final int x;
    private final int y;
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { 
        return this.x; 
    }
    
    public int getY() { 
        return this.y; 
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