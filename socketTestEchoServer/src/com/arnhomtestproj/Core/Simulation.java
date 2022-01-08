package com.arnhomtestproj.Core;

import com.arnhomtestproj.Core.Entities.Data.Grid;
import com.arnhomtestproj.Core.Entities.Data.WorldContext;
import com.arnhomtestproj.Core.Entities.Layer;
import com.arnhomtestproj.Core.Entities.SpecificLayers.ConwayLifeLayer;
import com.arnhomtestproj.Core.Entities.SpecificLayers.GrassLayer;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Exceptions.EventManagerOutOfEvents;

import java.util.List;
import java.util.Vector;

public class Simulation {

    LayerManager layerManager;
    EventManager eventManager;

    public Simulation(){
        WorldContext world = new WorldContext();
        eventManager = new EventManager();
        layerManager = new LayerManager(world);

        layerManager.add(new GrassLayer());
        layerManager.add(new GrassLayer("Second"));
        layerManager.add(new ConwayLifeLayer());
    }

    public int stepForward(){
        // get Decisions from the layers
        Events decisions = layerManager.getDecisions();
        eventManager.digestNewEvents( decisions );
        try{
            while(true){
                Events resultingEvents = layerManager.handleEventsOfType(eventManager.getNextEvents());
                eventManager.digestNewEvents(resultingEvents);
            }
        }
        catch (EventManagerOutOfEvents ignored){};
        draw();
        System.out.println("there were " + decisions.events.size() + " decisions handled");
        return decisions.events.size();
    }

    private void draw(){
        for(Layer layer: layerManager.layers){
            System.out.println(layer.name);
            System.out.print(layer.getGridRepresentation().toString());
        }
    }
}
