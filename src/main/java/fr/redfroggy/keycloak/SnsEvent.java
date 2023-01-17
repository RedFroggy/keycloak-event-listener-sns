package fr.redfroggy.keycloak;

import org.keycloak.events.Event;

public class SnsEvent {
   
    private final Event snsEvent;

    public SnsEvent(Event snsEvent) {
        this.snsEvent = snsEvent;
    }

    public Event getSnsEvent() {
        return snsEvent;
    }


}
