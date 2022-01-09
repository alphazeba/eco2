package com.arnhomtestproj.Core.Entities.Data;

import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.lang.reflect.Array;
import java.util.Iterator;

public class Grid<T> implements Iterable<GridCell<T>> {

    private Position size;
    private int length;
    private T[] data;
    private T initialValue;

    public Grid(int width, int height, T initialValue){
        this.initialValue = initialValue;
        init(width,height, initialValue);
    }

    public int getWidth(){
        return size.getX();
    }

    public int getHeight(){
        return size.getY();
    }

    private void init(int width, int height, T fill){
        size = new Position(width,height);
        length = width*height;
        data = (T[])Array.newInstance(fill.getClass(),length);
        fillData(fill);
    }

    private void fillData(T n){
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

    public T get(Position pos) throws IndexOutOfBoundsException{
        return data[posToIndex(pos)];
    }

    public T set(Position pos, T value){
        data[posToIndex(pos)] = value;
        return value;
    }

    public boolean hasAValue(Position pos){
        T value = initialValue;
        try{
            value = this.get(pos);
        }
        catch(IndexOutOfBoundsException ignore){};
        return value != initialValue;
    }

    public String toString(){
         final String ANSI_RESET = "\u001B[0m";
         final String ANSI_BLACK = "\u001B[30m";
         final String ANSI_RED = "\u001B[31m";
         final String ANSI_GREEN = "\u001B[32m";
        StringBuilder sb = new StringBuilder();
        int lastY = 0;
        char newLine = '\n';
        for(GridCell<T> cell: this){
            if(lastY != cell.getPos().getY()){
                sb.append(newLine);
                lastY = cell.getPos().getY();
            }
            if(cell.getContent() == initialValue){
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
    public Iterator<GridCell<T>> iterator() {
        return new GridIterator(this);
    }

    public class GridIterator implements Iterator<GridCell<T>> {

        int i;
        Grid<T> grid;

        public GridIterator(Grid<T> grid){
            i = 0;
            this.grid = grid;
        }

        @Override
        public boolean hasNext() {
            return i < grid.length;
        }

        @Override
        public GridCell<T> next() {
            GridCell<T> cell = new GridCell<>( grid.indexToPos(i), grid.data[i]);
            i++;
            return cell;
        }
    }
}
