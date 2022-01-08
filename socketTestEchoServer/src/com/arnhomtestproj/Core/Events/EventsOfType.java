package com.arnhomtestproj.Core.Events;

public class EventsOfType extends Events{

    private EventType type;

    public EventsOfType(EventType type, Events events){
        this.type = type;
        this.events = events.events;
    }

    public EventType getType(){
        return type;
    }
}
