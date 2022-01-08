package com.arnhomtestproj.Core.Events.SpecificEvents;

import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;
import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.EventType;

public class DiedEvent extends Event {
    public DiedEvent(FullAddress source){
        super(EventType.died,source);
    }

    public FullAddress getDiedAddress(){
        return source;
    }
}
