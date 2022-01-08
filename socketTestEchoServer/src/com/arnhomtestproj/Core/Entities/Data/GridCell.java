package com.arnhomtestproj.Core.Entities.Data;

public class GridCell {
    private Position pos;
    private int content;

    public GridCell(Position pos, int content){
        this.pos = pos;
        this.content = content;
    }

    public Position getPos(){
        return pos;
    }

    public int getContent(){
        return content;
    }
}
