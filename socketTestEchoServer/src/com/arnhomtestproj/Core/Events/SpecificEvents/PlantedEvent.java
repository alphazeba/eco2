package com.arnhomtestproj.Core.Events.SpecificEvents;

import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.EventType;

public class PlantedEvent extends Event {
    public Position pos;

    public PlantedEvent(FullAddress source, Position pos){
        super(EventType.planted, source);
        this.pos = pos;
    }
}
