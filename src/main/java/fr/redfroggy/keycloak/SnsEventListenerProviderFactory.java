package fr.redfroggy.keycloak;

import org.keycloak.Config.Scope;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class SnsEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public EventListenerProvider create(KeycloakSession arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void init(Scope arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postInit(KeycloakSessionFactory arg0) {
        // TODO Auto-generated method stub
        
    }
}
