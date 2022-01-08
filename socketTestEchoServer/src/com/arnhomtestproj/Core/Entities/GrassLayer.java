package com.arnhomtestproj.Core.Entities;

import com.arnhomtestproj.Core.Entities.Data.Address.EntityAddress;
import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.Grid;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Core.Events.SpecificEvents.DiedEvent;
import com.arnhomtestproj.Core.Events.SpecificEvents.PlantedEvent;

import java.util.Random;

public class GrassLayer extends Layer{

    private Grid grassGrid;
    Random rand = new Random();

    public GrassLayer() {
        super("grassLayer");
        grassGrid = new Grid(5,5);
    }

    @Override
    public Events getDecisions() {
        Events decisions = new Events();

        // get random spot
        Position randomPosition = new Position(rand.nextInt(5), rand.nextInt(5));
        FullAddress sourceAddress = getFullAddress(positionToEntityAddress(randomPosition));

        // either plant or kill depending on if there is something there already.
        if(grassGrid.get(randomPosition) > 0){
            decisions.add( new DiedEvent(sourceAddress));
        }
        else{
            decisions.add(new PlantedEvent(sourceAddress, randomPosition));
        }
        return decisions;
    }

    @Override
    protected void plantEntity(Position pos) {
        grassGrid.add(pos,1);
    }

    @Override
    protected void killEntity(EntityAddress entityAddress) {
        grassGrid.set( parseEntityAddress(entityAddress) , 0);
    }

    private Position parseEntityAddress(EntityAddress entityAddress){
        String coord = entityAddress.getId();
        String[] xy = coord.split(",");
        return new Position( Integer.parseInt(xy[0]) , Integer.parseInt(xy[1]) );
    }

    private EntityAddress positionToEntityAddress(Position pos){
        String coord = "" + pos.getX() + "," + pos.getY();
        return new EntityAddress(coord);
    }

    @Override
    protected Events handleLayerUniqueEvents(Event event) {
        return null;
    }
}
