package fr.redfroggy.keycloak;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerTransaction;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.AuthDetails;
import org.keycloak.models.KeycloakTransactionManager;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RealmProvider;
import org.keycloak.models.UserModel;
import org.keycloak.models.UserProvider;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class SnsEventListenerProviderTest {

    @Captor
    private ArgumentCaptor<SnsAdminEvent> snsAdminEventCaptor;

    @Captor
    private ArgumentCaptor<SnsEvent> snsEventCaptor;

    @Captor
    private ArgumentCaptor<EventListenerTransaction> transactionCaptor;

    @Mock
    private Event eventMock;
    
    @Mock
    private SnsEvent snsEventMock;

    @Mock
    private AdminEvent adminEventMock;

    @Mock
    private SnsAdminEvent snsAdminEventMock;

    @Mock
    private KeycloakTransactionManager transactionManagerMock;

    @Mock
    private SnsEventPublisher snsEventPublisherMock;

    @Mock
    private UserProvider userProviderMock;

    @Mock
    private RealmProvider realmProviderMock;

    @Mock
    private RealmModel realmMock;

    @Mock
    private UserModel userMock;

    @Mock
    private AuthDetails authDetailsMock;

    @InjectMocks
    private SnsEventListenerProvider snsEventListenerProvider;

    @Test
    void shouldAddEventToTransaction(){
        snsEventListenerProvider.onEvent(eventMock);
        when(realmProviderMock.getRealm(eventMock.getRealmId())).thenReturn(realmMock);
        when(userProviderMock.getUserById(realmMock, eventMock.getUserId())).thenReturn(userMock);
        verify(transactionManagerMock).enlistAfterCompletion(transactionCaptor.capture());
        EventListenerTransaction transaction = transactionCaptor.getValue();
        transaction.begin();
        transaction.commit();
        verify(snsEventPublisherMock).sendEvent(snsEventCaptor.capture());
        SnsEvent result = snsEventCaptor.getValue();
        assertThat(result.getEvent()).isEqualTo(eventMock);
        assertThat(result.getUsername()).isEqualTo(userMock.getUsername());
    }

    @Test
    void shouldAddAdminEventToTransaction(){
        snsEventListenerProvider.onEvent(adminEventMock, true);
        when(realmProviderMock.getRealm(adminEventMock.getRealmId())).thenReturn(realmMock);
        when(adminEventMock.getAuthDetails()).thenReturn(authDetailsMock);
        when(userProviderMock.getUserById(realmMock, adminEventMock.getAuthDetails().getUserId())).thenReturn(userMock);
        verify(transactionManagerMock).enlistAfterCompletion(transactionCaptor.capture());
        EventListenerTransaction transaction = transactionCaptor.getValue();
        transaction.begin();
        transaction.commit();
        verify(snsEventPublisherMock).sendAdminEvent(snsAdminEventCaptor.capture());
        SnsAdminEvent result = snsAdminEventCaptor.getValue();
        assertThat(result.getAdminEvent()).isEqualTo(adminEventMock);
        assertThat(result.getUsername()).isEqualTo(userMock.getUsername());
    }
    
    @Test
    void shouldCloseTheProvider(){
        snsEventListenerProvider.close();
        // For coverage
    }

}
