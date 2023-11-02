package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerTransaction;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.*;

import static org.keycloak.models.utils.KeycloakModelUtils.runJobInTransaction;

public class SnsEventListenerProvider implements EventListenerProvider {

    private final SnsEventPublisher snsEventPublisher;

    private final KeycloakSessionFactory sessionFactory;
    private final EventListenerTransaction transaction = new EventListenerTransaction(this::sendAdminEvent,
            this::sendEvent);

    public SnsEventListenerProvider(SnsEventPublisher snsEventPublisher,
                                    KeycloakSessionFactory sessionFactory,
                                    KeycloakTransactionManager transactionManager) {
        this.snsEventPublisher = snsEventPublisher;
        this.sessionFactory = sessionFactory;
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

    private void sendEvent(Event event) {
        runJobInTransaction(sessionFactory, session ->
                snsEventPublisher.sendEvent(new SnsEvent(event, getUsername(session.realms(), session.users(),  event.getRealmId(), event.getUserId()))));
    }

    private void sendAdminEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        runJobInTransaction(sessionFactory, session -> {
                    String adminUserId = null;
                    if (adminEvent.getAuthDetails() != null) {
                        adminUserId = adminEvent.getAuthDetails().getUserId();
                    }
                    snsEventPublisher.sendAdminEvent(
                            new SnsAdminEvent(adminEvent, getUsername(session.realms(), session.users(),  adminEvent.getRealmId(), adminUserId)));
                }
        );
    }

    private String getUsername(RealmProvider realmProvider, UserProvider userProvider, String realmId, String userId) {
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
