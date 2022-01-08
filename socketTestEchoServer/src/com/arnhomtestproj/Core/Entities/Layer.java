package com.arnhomtestproj.Core.Entities;

import com.arnhomtestproj.Core.Entities.Data.Address.EntityAddress;
import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.Address.LayerAddress;
import com.arnhomtestproj.Core.Entities.Data.Grid;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Entities.Data.WorldContext;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.EventType;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Core.Events.SpecificEvents.DiedEvent;
import com.arnhomtestproj.Core.Events.SpecificEvents.PlantedEvent;
import sun.lwawt.PlatformEventNotifier;

public abstract class Layer {
    private LayerAddress address;
    public String name;

    public Layer(String name){
        address = new LayerAddress();
        this.name = name;
    }
    public abstract void setWorldContext(WorldContext world);

    public abstract Events getDecisions();

    public Events handleEventsOfType(EventType type, Events events){
        Events reactions = new Events();
        if(type == EventType.planted){
            for(Event event : events.events){
                reactions.add( handlePlantedEvent((PlantedEvent) event) );
            }
        }
        else if( type == EventType.died ){
            for(Event event: events.events){
                reactions.add( handleDiedEvent((DiedEvent) event) );
            }
        }
        else{
            reactions.add(handleLayerUniqueEvents(events));
        }
        return reactions;
    }

    protected Events handlePlantedEvent(PlantedEvent event){
        if(event.layer.matches(getLayerAddress())){
            plantEntity( event.pos );
        }
        return null;
    }

    protected Events handleDiedEvent(DiedEvent event){
        if(event.getDiedAddress().matches(address)){
            killEntity( event.getDiedAddress().getEntityAddress() );
        }
        return null;
    }

    protected FullAddress getFullAddress(EntityAddress entityAddress){
        return new FullAddress(address,entityAddress);
    }

    public LayerAddress getLayerAddress(){
        return address;
    }

    public abstract Grid getGridRepresentation();

    protected abstract void plantEntity(Position pos);
    protected abstract void killEntity(EntityAddress entityAddress);
    protected abstract Events handleLayerUniqueEvents(Events events);
}
