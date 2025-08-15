package com.example.adventofcode.solutions;

import com.example.adventofcode.utils.DataLoader;
import com.example.adventofcode.utils.SystemOut;

public class DaySixteen2024 {
    private final char[][] maze;

    public DaySixteen2024(String filePath){
        this.maze = DataLoader.generateCharGrid(filePath);
    }
    public void printMaze(){
        SystemOut.printGrid(maze);
    }
}
