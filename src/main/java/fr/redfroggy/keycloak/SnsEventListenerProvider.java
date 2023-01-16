package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;


public class SnsEventListenerProvider implements EventListenerProvider {
    
    private SnsEventListenerConfiguration snsEventListenerConfiguration;
    
    public SnsEventListenerProvider(SnsEventListenerConfiguration snsEventListenerConfiguration) {
        this.snsEventListenerConfiguration = snsEventListenerConfiguration;
    }

    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public void onEvent(Event event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void close() {
    }
}
