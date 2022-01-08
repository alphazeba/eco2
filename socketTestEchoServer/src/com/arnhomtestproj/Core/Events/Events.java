package com.arnhomtestproj.Core.Events;

import java.util.List;
import java.util.Vector;

public class Events {
    public List<Event> events;

    public Events(){
        events = new Vector<>();
    }

    public void add(Events events){
        if(events == null)
            return;
        this.events.addAll(events.events);
    }

    public void add(Event event){
        if(event == null)
            return;
        events.add(event);
    }
}
