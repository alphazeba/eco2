package com.arnhomtestproj.Core.Entities;

import com.arnhomtestproj.Core.Entities.Data.Address.EntityAddress;
import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.Address.LayerAddress;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.EventType;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Core.Events.SpecificEvents.DiedEvent;
import com.arnhomtestproj.Core.Events.SpecificEvents.PlantedEvent;

public abstract class Layer {
    LayerAddress address;
    String name;

    public Layer(String name){
        address = new LayerAddress();
        this.name = name;
    }

    public abstract Events getDecisions();

    public Events handleEvent(Event event){
        Events reactions = new Events();
        if(event.is(EventType.planted)){
            reactions.add( handlePlantedEvent((PlantedEvent) event) );
        }
        else if( event.is(EventType.died) ){
            reactions.add( handleDiedEvent((DiedEvent) event) );
        }
        else{
            reactions.add( handleLayerUniqueEvents(event) );
        }
        return reactions;
    }

    protected Events handlePlantedEvent(PlantedEvent event){
        plantEntity( event.pos );
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

    protected abstract void plantEntity(Position pos);
    protected abstract void killEntity(EntityAddress entityAddress);
    protected abstract Events handleLayerUniqueEvents(Event event);
}
