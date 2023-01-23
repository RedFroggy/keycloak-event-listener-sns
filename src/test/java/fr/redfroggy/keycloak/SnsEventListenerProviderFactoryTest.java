package fr.redfroggy.keycloak;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
@Disabled
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
    private SnsEventListenerProvider snsEventListenerProviderMock;

    @InjectMocks
    private SnsEventListenerProviderFactory snsEventListenerProviderFactoryMock;

    @Test
    public void shouldCreateEventListenerProvider() {
        SnsEventListenerProvider toCreate = new SnsEventListenerProvider(snsEventPublisherMock);
        assertEquals(toCreate, snsEventListenerProviderFactoryMock.create(session));
        
    }

    @Test
    public void shouldGetEventListenerId(){
        String SNS_EVENT_LISTENER = "SNS_EVENT_LISTENER";
        assertEquals(SNS_EVENT_LISTENER, snsEventListenerProviderFactoryMock.getId());
    }

    @Test
    public void shouldInitEventListenerProviderConfig() {
        //TODO
    }
    
}
