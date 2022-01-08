package com.arnhomtestproj.Core.Entities;

import com.arnhomtestproj.Core.Entities.Data.Address.EntityAddress;
import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.Grid;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Entities.Data.WorldContext;
import com.arnhomtestproj.Core.Entities.Layer;

public abstract class GridBasedLayer extends Layer {

    protected Grid grid;

    public GridBasedLayer(String name){
        super(name);
    }

    @Override
    public void setWorldContext(WorldContext world){
        grid = new Grid(world.size.getX(), world.size.getY());
    }

    @Override
    protected void plantEntity(Position pos) {
        grid.add(pos,1);
    }

    @Override
    protected void killEntity(EntityAddress entityAddress) {
        grid.set( parseEntityAddress(entityAddress) , 0);
    }

    protected FullAddress getFullAddressOfPosition(Position pos){
        return getFullAddress(positionToEntityAddress(pos));
    }

    protected Position parseEntityAddress(EntityAddress entityAddress){
        String coord = entityAddress.getId();
        String[] xy = coord.split(",");
        return new Position( Integer.parseInt(xy[0]) , Integer.parseInt(xy[1]) );
    }

    protected EntityAddress positionToEntityAddress(Position pos){
        String coord = "" + pos.getX() + "," + pos.getY();
        return new EntityAddress(coord);
    }

    public Grid getGridRepresentation(){
        return grid;
    }

    @Override
    public boolean containsEntity(EntityAddress entityAddress) {
        Position pos = parseEntityAddress(entityAddress);
        return grid.hasAValue(pos);
    }
}
