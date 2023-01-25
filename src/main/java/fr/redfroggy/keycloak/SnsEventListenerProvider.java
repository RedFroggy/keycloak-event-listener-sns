package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerTransaction;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakTransactionManager;
public class SnsEventListenerProvider implements EventListenerProvider {

    private final SnsEventPublisher snsEventPublisher;

    private final EventListenerTransaction transaction = new EventListenerTransaction(this::sendAdminEvent, this::sendEvent);

    public SnsEventListenerProvider(SnsEventPublisher snsEventPublisher, KeycloakTransactionManager transactionManager) {
        this.snsEventPublisher = snsEventPublisher;
        transactionManager.enlistAfterCompletion(transaction);
    }

    @Override
    public void onEvent(Event event) {
        transaction.addEvent(event);
       
    }

    @Override
    public void onEvent(AdminEvent event, boolean includeRepresentation) {
        transaction.addAdminEvent(event, includeRepresentation);      
    }

    @Override
    public void close() {
    }

    private void sendEvent(Event event){
        snsEventPublisher.sendEvent(new SnsEvent(event));
    }

    private void sendAdminEvent(AdminEvent adminEvent, boolean includeRepresentation){
        snsEventPublisher.sendAdminEvent(new SnsAdminEvent(adminEvent)); 
    }

}
