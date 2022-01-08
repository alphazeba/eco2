package com.arnhomtestproj.Core.Entities.Data;

public class Grid {

    private Position size;
    private int length;
    private int[] data;

    public Grid(int width, int height){
        init(width,height,0);
    }

    public Grid(int width, int height, int fill){
        init(width,height,fill);
    }

    private void init(int width, int height, int fill){
        size = new Position(width,height);
        length = width*height;
        data = new int[length];
        fillData(0);
    }

    private void fillData(int n){
        for(int i =0; i < length; i++){
            data[i] = n;
        }
    }

    private int posToIndex(Position pos){
        if(pos.within(new Position(0,0), size)) {
            return pos.getX() + pos.getY() * size.getY();
        }
        throw new IndexOutOfBoundsException();
    }

    public int get(Position pos){
        return data[posToIndex(pos)];
    }

    public int set(Position pos, int value){
        data[posToIndex(pos)] = value;
        return value;
    }

    public int add(Position pos, int value){
        int index = posToIndex(pos);
        data[index] += value;
        return data[index];
    }
}
