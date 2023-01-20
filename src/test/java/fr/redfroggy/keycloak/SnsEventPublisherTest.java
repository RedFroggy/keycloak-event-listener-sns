package fr.redfroggy.keycloak;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({MockitoExtension.class})
class SnsEventPublisherTest {

    @Mock
    private AmazonSNSAsync snsClient;

    @Mock
    private SnsEvent snsEvent;

    @Mock
    private SnsAdminEvent snsAdminEvent;

    @Mock
    private ObjectMapper mapperMock;

    @Mock
    private SnsEventListenerConfiguration snsEventListenerConfigurationMock;
    
    @InjectMocks
    private SnsEventPublisher snsEventPublisherMock;

    
    @Test
    void shouldsendEventWhenEventTopicArnExists() throws JsonProcessingException{
        when(snsEventListenerConfigurationMock.getEventTopicArn()).thenReturn("eventTopicArn");
        when(mapperMock.writeValueAsString(snsEvent)).thenReturn("eventJSONString");
        snsEventPublisherMock.sendEvent(snsEvent);
        verify(snsClient).publish("eventTopicArn", "eventJSONString");
    }

    @Test
    void shouldNotsendEventWhenEventTopicArnDoenstExistsAndGetAWarning(){
        snsEventPublisherMock.sendEvent(snsEvent);
        verify(snsClient, never()).publish(any(),any());
    }

    @Test
    void shouldsendAdminEventWhenEventTopicArnExists() throws JsonProcessingException{
        when(snsEventListenerConfigurationMock.getAdminEventTopicArn()).thenReturn("adminEventTopicArn");
        when(mapperMock.writeValueAsString(snsAdminEvent)).thenReturn("adminEventJSONString");
        snsEventPublisherMock.sendAdminEvent(snsAdminEvent);
        verify(snsClient).publish("adminEventTopicArn", "adminEventJSONString");
    }

    @Test
    void shouldNotsendAdminEventWhenEventTopicArnDoenstExists(){
        snsEventPublisherMock.sendAdminEvent(snsAdminEvent);
        verify(snsClient, never()).publish(any(),any());
    }

}
