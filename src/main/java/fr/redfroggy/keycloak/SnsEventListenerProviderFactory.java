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
    }

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new SnsEventListenerProvider();
    }

    @Override
    public String getId() {
        return SNS_EVENT_LISTENER;
    }

    @Override
    public void init(Scope scope) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postInit(KeycloakSessionFactory sessionFactory) {        
    }
}
