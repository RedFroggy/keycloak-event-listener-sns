package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SnsEventListenerProvider implements EventListenerProvider {
    
    private final SnsEventListenerConfiguration snsEventListenerConfiguration;
    private final SnsEventPublisher snsEventPublisher;
    


    public SnsEventListenerProvider(SnsEventListenerConfiguration snsEventListenerConfiguration,
            SnsEventPublisher snsEventPublisher, KeycloakSession session, AmazonSNSAsync snsClient,
            ObjectMapper mapper) {
        this.snsEventListenerConfiguration = snsEventListenerConfiguration;
        this.snsEventPublisher = snsEventPublisher;
        
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onEvent(Event event) {
        snsEventPublisher.sendEvent(snsEventListenerConfiguration.getEventTopicArn(), event);
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        snsEventPublisher.sendAdminEvent(snsEventListenerConfiguration.getAdminEventTopicArn(), event);
        
    }

    @Override
    public void close() {
    }
}
