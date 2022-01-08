package com.arnhomtestproj.Core.Events;


import com.arnhomtestproj.Core.Entities.Data.Address.FullAddress;

public class Event {
    private EventType type;
    protected FullAddress source;

    protected Event(EventType type, FullAddress source){
        this.type = type;
        this.source = source;
    }

    public EventType getType(){
        return type;
    }

    public boolean is(EventType type){
        return this.type == type;
    }
}
