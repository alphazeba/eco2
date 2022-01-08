package com.arnhomtestproj.Core.Events.SpecificEvents;

import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Entities.Data.Address.LayerAddress;
import com.arnhomtestproj.Core.Entities.Data.Position;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.EventType;
import com.arnhomtestproj.Core.LayerManager;

public class PlantedEvent extends Event {
    public Position pos;
    public LayerAddress layer;

    public PlantedEvent(FullAddress source, Position pos, LayerAddress layer){
        super(EventType.planted, source);
        this.pos = pos;
        this.layer = layer;
    }
}
