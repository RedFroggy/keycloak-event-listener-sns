package fr.redfroggy.keycloak;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class SnsEventListenerProviderTest {

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
        verify(snsEventPublisherMock, times(1)).sendEvent(any());
    }

    @Test
    void shouldAddNewAdminEventListenedWhenCalled(){
        boolean includeRepresentation = true;
        snsEventListenerProviderMock.onEvent(adminEvent, includeRepresentation);
        verify(snsEventPublisherMock, times(1)).sendAdminEvent(any());
    }

    @Test
    void shouldNotAddNewAdminEventListenedWhenCalled(){
        boolean includeRepresentation = false;
        snsEventListenerProviderMock.onEvent(adminEvent, includeRepresentation);
        verify(snsEventPublisherMock, never()).sendAdminEvent(any());
    }

}
