package com.arnhomtestproj.Core;

import com.arnhomtestproj.Core.Entities.GrassLayer;
import com.arnhomtestproj.Core.Entities.Layer;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.Events;

import java.util.AbstractMap;

public class Simulation {

    public Simulation(){
        Layer layer = new GrassLayer();

        Events events = layer.getDecisions();

        for( Event event : events.events){
            layer.handleEvent(event);
        }
    }
}
