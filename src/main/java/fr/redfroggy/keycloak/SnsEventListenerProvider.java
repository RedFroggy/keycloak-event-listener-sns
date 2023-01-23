package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

public class SnsEventListenerProvider implements EventListenerProvider {

    private final SnsEventPublisher snsEventPublisher;

    public SnsEventListenerProvider(SnsEventPublisher snsEventPublisher) {
        this.snsEventPublisher = snsEventPublisher;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void onEvent(Event event) {
        snsEventPublisher.sendEvent(new SnsEvent(event));
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        snsEventPublisher.sendAdminEvent(new SnsAdminEvent(event));       
    }

    @Override
    public void close() {
    }
}
