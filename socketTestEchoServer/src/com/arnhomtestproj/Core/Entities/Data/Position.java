package com.arnhomtestproj.Core.Entities.Data;

import javafx.geometry.Pos;

public class Position {
    private int x;
    private int y;
    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Position(Position pos){
        this.x = pos.x;
        this.y = pos.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position add(Position other){
        return new Position(this.x+other.x, this.y+other.y);
    }

    public Position subtract(Position other){
        return new Position(this.x-other.x,this.y-other.y);
    }

    public boolean within(Position origin, Position size){
        return origin.x <= this.x && this.x < size.x &&
                origin.y <= this.y && this.y < size.y;
    }
}
