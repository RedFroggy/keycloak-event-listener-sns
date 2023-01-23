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
    private Event event;
    
    @Mock
    private SnsEvent snsEvent;

    @Mock
    private AdminEvent adminEvent;

    @Mock
    private SnsAdminEvent snsAdminEvent;

    @Mock
    private SnsEventPublisher snsEventPublisherMock;

    @InjectMocks
    private SnsEventListenerProvider snsEventListenerProviderMock;

    @Test
    void shouldAddNewEventListenedWhenCalled() {
        snsEventListenerProviderMock.onEvent(event);
        verify(snsEventPublisherMock).sendEvent(snsEventCaptor.capture());
        SnsEvent result = snsEventCaptor.getValue();
        assertEquals(event, result.getEvent());
    }

    @Test
    void shouldAddNewAdminEventListenedWhenCalled(){
        boolean includeRepresentation = true;
        snsEventListenerProviderMock.onEvent(adminEvent, includeRepresentation);
        verify(snsEventPublisherMock).sendAdminEvent(snsAdminEventCaptor.capture());
        SnsAdminEvent result = snsAdminEventCaptor.getValue();
        assertEquals(adminEvent, result.getAdminEvent());
    }

}
