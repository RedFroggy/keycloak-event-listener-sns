package fr.redfroggy.keycloak;

import org.keycloak.Config.Scope;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class SnsEventListenerProviderFactory implements EventListenerProviderFactory {

    static final String SNS_EVENT_LISTENER = "SNS_EVENT_LISTENER";

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        // TODO Auto-generated method stub
        return new SnsEventListenerProvider();
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return SNS_EVENT_LISTENER;
    }

    @Override
    public void init(Scope scope) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postInit(KeycloakSessionFactory sessionFactory) {
        // TODO Auto-generated method stub
        
    }
}
