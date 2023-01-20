package fr.redfroggy.keycloak;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class SnsEventListenerProviderFactoryTest {

    @Mock
    private Config.Scope config;

    @Mock
    KeycloakSession session;

    @Mock
    private SnsEventPublisher snsEventPublisherMock;

    @Mock
    private SnsEventListenerConfiguration snsEventListenerConfigurationMock;
    @Mock
    private SnsEventListenerProvider snsEventListenerProviderMock = new SnsEventListenerProvider(snsEventPublisherMock);

    @Mock
    private SnsEventListenerProviderFactory snsEventListenerProviderFactoryMock = new SnsEventListenerProviderFactory();

    @Test
    public void shouldCreateEventListenerProvider() {
        when(snsEventListenerProviderFactoryMock.create(session)).thenReturn(snsEventListenerProviderMock);
        snsEventListenerProviderFactoryMock.create(session);
        verify(snsEventListenerProviderFactoryMock).create(session);
    }

    @Test
    public void shouldGetEventListenerId(){
        String SNS_EVENT_LISTENER = "SNS_EVENT_LISTENER";
        when(snsEventListenerProviderFactoryMock.getId()).thenReturn(SNS_EVENT_LISTENER);
        snsEventListenerProviderFactoryMock.getId();
        verify(snsEventListenerProviderFactoryMock).getId();
    }

    @Test
    public void shouldInitEventListenerProviderConfig() {
        
    }
    
}
