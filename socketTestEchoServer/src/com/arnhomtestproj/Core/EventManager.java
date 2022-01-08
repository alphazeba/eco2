package com.arnhomtestproj.Core;

import com.arnhomtestproj.Core.Events.Event;
import com.arnhomtestproj.Core.Events.EventType;
import com.arnhomtestproj.Core.Events.Events;
import com.arnhomtestproj.Core.Events.EventsOfType;
import com.arnhomtestproj.Exceptions.EventManagerOutOfEvents;

import java.util.HashMap;
import java.util.Map;

public class EventManager {
    static final EventType[] eventOrder = {
            EventType.died,
            EventType.planted
    };

    private Map<EventType, Events> sortedEvents;

    public EventManager(){
        sortedEvents = new HashMap<>();
        for(EventType type: eventOrder){
            sortedEvents.put(type, new Events());
        }
    }

    public void digestNewEvents(Events events){
        for(Event event: events.events){
            sortedEvents.get(event.getType()).add(event);
        }
    }

    public EventsOfType getNextEvents() throws EventManagerOutOfEvents {
        for(EventType type: eventOrder){
            Events typeEvents = sortedEvents.get(type);
            if( !typeEvents.isEmpty() ){
                //replace the current events with an empty
                sortedEvents.put(type, new Events());
                return new EventsOfType(type, typeEvents);
            }
        }
        throw new EventManagerOutOfEvents();
    }

}
