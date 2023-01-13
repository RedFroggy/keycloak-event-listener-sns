package fr.redfroggy.keycloak;

import org.keycloak.events.Event;

public class SnsEvent extends Event {    

    public static SnsEvent create(Event event){
        SnsEvent snsEvent = new SnsEvent();

        snsEvent.setClientId(event.getClientId());
        snsEvent.setDetails(event.getDetails());
        snsEvent.setError(event.getError());
        snsEvent.setId(event.getId());
        snsEvent.setIpAddress(event.getIpAddress());
        snsEvent.setRealmId(event.getRealmId());
        snsEvent.setSessionId(event.getSessionId());
        snsEvent.setTime(event.getTime());
        snsEvent.setType(event.getType());
        snsEvent.setUserId(event.getUserId());

        return snsEvent;
    }
}
