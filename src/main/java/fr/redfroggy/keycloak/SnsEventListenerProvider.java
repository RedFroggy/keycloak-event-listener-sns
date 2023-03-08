package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerTransaction;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakTransactionManager;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserProvider;

public class SnsEventListenerProvider implements EventListenerProvider {

    private final SnsEventPublisher snsEventPublisher;

    private final EventListenerTransaction transaction = new EventListenerTransaction(this::sendAdminEvent,
            this::sendEvent);

    private final UserProvider userProvider;

    private final RealmProvider realmProvider;

    public SnsEventListenerProvider(SnsEventPublisher snsEventPublisher, KeycloakTransactionManager transactionManager,
            UserProvider userProvider, RealmProvider realmProvider) {
        this.snsEventPublisher = snsEventPublisher;
        transactionManager.enlistAfterCompletion(transaction);
        this.userProvider = userProvider;
        this.realmProvider = realmProvider;
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

    private void sendEvent(Event event) {
        snsEventPublisher.sendEvent(new SnsEvent(event, getUsername(event.getRealmId(), event.getUserId())));
    }

    private void sendAdminEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        String adminUserId;
        if (adminEvent.getAuthDetails() == null) {
            adminUserId = null;
        }else{
            adminUserId = adminEvent.getAuthDetails().getUserId();
        }
        snsEventPublisher.sendAdminEvent(new SnsAdminEvent(adminEvent, getUsername(adminEvent.getRealmId(), adminUserId)));       
    }

    private String getUsername(String realmId, String userId) {
        UserModel user;
        if (userId != null) {
            user = userProvider.getUserById(realmProvider.getRealm(realmId), userId);

            if (user != null) {
                return user.getUsername();
            }
        }
        return null;
    }
}
