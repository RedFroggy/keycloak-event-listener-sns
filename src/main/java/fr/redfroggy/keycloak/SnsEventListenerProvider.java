package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;


public class SnsEventListenerProvider implements EventListenerProvider {

    public SnsEventListenerProvider() {
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
    public void onEvent(AdminEvent event, boolean arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void close() {
    }
}
