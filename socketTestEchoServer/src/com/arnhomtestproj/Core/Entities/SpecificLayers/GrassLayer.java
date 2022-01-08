package com.arnhomtestproj.Core.Entities.SpecificLayers;

import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Entities.GridBasedLayer;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Core.Events.SpecificEvents.DiedEvent;
import com.arnhomtestproj.Core.Events.SpecificEvents.PlantedEvent;

import java.util.Random;

public class GrassLayer extends GridBasedLayer {

    Random rand = new Random();

    public GrassLayer() {
        this("");
    }

    public GrassLayer(String identifier){
        super("grassLayer" + identifier);
    }

    @Override
    public Events getDecisions() {
        Events decisions = new Events();

        // get random spot
        Position randomPosition = new Position(rand.nextInt(grid.getWidth()), rand.nextInt(grid.getHeight()));
        FullAddress sourceAddress = getFullAddressOfPosition(randomPosition);

        // either plant or kill depending on if there is something there already.
        if(grid.get(randomPosition) > 0){
            decisions.add( new DiedEvent(sourceAddress));
        }
        else{
            decisions.add(new PlantedEvent(sourceAddress, randomPosition, getLayerAddress()));
        }
        return decisions;
    }

    @Override
    protected Events handleLayerUniqueEvents(Events events) {
        return null;
    }
}
