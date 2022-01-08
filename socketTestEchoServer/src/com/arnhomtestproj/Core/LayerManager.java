package com.arnhomtestproj.Core;

import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.WorldContext;
import com.arnhomtestproj.Core.Entities.Layer;
import com.arnhomtestproj.Core.Events.EventType;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Core.Events.EventsOfType;

import java.util.List;
import java.util.Vector;

public class LayerManager {

    List<Layer> layers;
    WorldContext world;

    public LayerManager(WorldContext world){
        layers = new Vector<>();
        this.world = world;
    }

    public void add(Layer layer){
        layer.setWorldContext(world);
        layers.add(layer);
    }

    public boolean addressExists(FullAddress address){
        for(Layer layer: layers){
            if( address.matches(layer.getLayerAddress())  &&
                    layer.containsEntity(address.getEntityAddress()))
            {
                return true;
            }
        }
        return false;
    }

    public Events getDecisions(){
        Events decisions = new Events();
        for(Layer layer: layers){
            decisions.add(layer.getDecisions());
        }
        return decisions;
    }

    public Events handleEventsOfType(EventsOfType typeEvents){ // TODO remove the other version of this function.
        return handleEventsOfType(typeEvents.getType(),typeEvents);
    }

    public Events handleEventsOfType(EventType type, Events events){
        Events reactions = new Events();
        for(Layer layer: layers){
            reactions.add( layer.handleEventsOfType(type, events) );
        }
        return reactions;
    }

}
