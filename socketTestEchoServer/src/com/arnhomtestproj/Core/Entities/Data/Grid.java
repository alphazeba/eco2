package com.arnhomtestproj.Core.Entities.Data;

import java.util.Iterator;

public class Grid implements Iterable<GridCell> {

    private Position size;
    private int length;
    private int[] data;

    public Grid(int width, int height){
        init(width,height,0);
    }

    public int getWidth(){
        return size.getX();
    }

    public int getHeight(){
        return size.getY();
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

    private int posToIndex(Position pos) throws IndexOutOfBoundsException{
        if(pos.within(new Position(0,0), size)) {
            return pos.getY() * size.getX() + pos.getX();
        }
        throw new IndexOutOfBoundsException();
    }

    private Position indexToPos(int index){
        int x = index % size.getX();
        int y = index / size.getX();
        return new Position(x,y);
    }

    public int get(Position pos) throws IndexOutOfBoundsException{
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

    public boolean hasAValue(Position pos){
        int value = 0;
        try{
            value = this.get(pos);
        }
        catch(IndexOutOfBoundsException ignore){};
        return value != 0;
    }

    public String toString(){
         final String ANSI_RESET = "\u001B[0m";
         final String ANSI_BLACK = "\u001B[30m";
         final String ANSI_RED = "\u001B[31m";
         final String ANSI_GREEN = "\u001B[32m";
        StringBuilder sb = new StringBuilder();
        int lastY = 0;
        char newLine = '\n';
        for(GridCell cell: this){
            if(lastY != cell.getPos().getY()){
                sb.append(newLine);
                lastY = cell.getPos().getY();
            }
            if(cell.getContent() <= 0){
                sb.append(ANSI_BLACK);
            }
            else{
                sb.append(ANSI_GREEN);
            }
            sb.append(cell.getContent()).append(" , ");
            sb.append(ANSI_RESET);
        }
        sb.append(newLine);
        return sb.toString();
    }

    @Override
    public Iterator<GridCell> iterator() {
        return new GridIterator(this);
    }

    public class GridIterator implements Iterator<GridCell> {

        int i;
        Grid grid;

        public GridIterator(Grid grid){
            i = 0;
            this.grid = grid;
        }

        @Override
        public boolean hasNext() {
            return i < grid.length;
        }

        @Override
        public GridCell next() {
            GridCell cell = new GridCell( grid.indexToPos(i), grid.data[i]);
            i++;
            return cell;
        }
    }
}
