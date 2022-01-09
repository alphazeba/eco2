package com.arnhomtestproj.Core.Entities.Data;

public class GridCell<T> {
    private Position pos;
    private T content;

    public GridCell(Position pos, T content){
        this.pos = pos;
        this.content = content;
    }

    public Position getPos(){
        return pos;
    }

    public T getContent(){
        return content;
    }
}
