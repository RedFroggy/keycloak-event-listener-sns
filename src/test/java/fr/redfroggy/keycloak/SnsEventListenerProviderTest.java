package fr.redfroggy.keycloak;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;
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

    @Mock
    private Event eventMock;
    
    @Mock
    private SnsEvent snsEventMock;

    @Mock
    private AdminEvent adminEventMock;

    @Mock
    private SnsAdminEvent snsAdminEventMock;

    @Mock
    private SnsEventPublisher snsEventPublisherMock;

    @InjectMocks
    private SnsEventListenerProvider snsEventListenerProvider;

    @Test
    void shouldAddNewEventListenedWhenCalled() {
        snsEventListenerProvider.onEvent(eventMock);
        verify(snsEventPublisherMock).sendEvent(snsEventCaptor.capture());
        SnsEvent result = snsEventCaptor.getValue();
        assertEquals(eventMock, result.getEvent());
    }

    @Test
    void shouldAddNewAdminEventListenedWhenCalled(){
        boolean includeRepresentation = true;
        snsEventListenerProvider.onEvent(adminEventMock, includeRepresentation);
        verify(snsEventPublisherMock).sendAdminEvent(snsAdminEventCaptor.capture());
        SnsAdminEvent result = snsAdminEventCaptor.getValue();
        assertEquals(adminEventMock, result.getAdminEvent());
    } 
    
    @Test
    void shouldCloseTheProvider(){
        snsEventListenerProvider.close();
        // For coverage
    }

}
