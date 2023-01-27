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
    private AmazonSNSAsync snsClientMock;

    @Mock
    private SnsEvent snsEventMock;

    @Mock
    private SnsAdminEvent snsAdminEventMock;

    @Mock
    private ObjectMapper mapperMock;

    @Mock
    private SnsEventListenerConfiguration snsEventListenerConfigurationMock;
    
    @InjectMocks
    private SnsEventPublisher snsEventPublisher;

    
    @Test
    void shouldSendEventWhenEventTopicArnExists()  throws JsonProcessingException {
        when(snsEventListenerConfigurationMock.getEventTopicArn()).thenReturn("eventTopicArn");
        when(mapperMock.writeValueAsString(snsEventMock)).thenReturn("eventJSONString");
        snsEventPublisher.sendEvent(snsEventMock);
        verify(snsClientMock).publish("eventTopicArn", "eventJSONString");
    }

    @Test
    void shouldNotSendEventWhenEventTopicArnDoenstExistsAndThrowAnException() throws Exception{
        snsEventPublisher.sendEvent(snsEventMock);
        verify(snsClientMock, never()).publish(any(),any());
    }

    @Test
    void shouldSendAdminEventWhenEventTopicArnExists() throws JsonProcessingException{
        when(snsEventListenerConfigurationMock.getAdminEventTopicArn()).thenReturn("adminEventTopicArn");
        when(mapperMock.writeValueAsString(snsAdminEventMock)).thenReturn("adminEventJSONString");
        snsEventPublisher.sendAdminEvent(snsAdminEventMock);
        verify(snsClientMock).publish("adminEventTopicArn", "adminEventJSONString");
    }

    @Test
    void shouldNotSendAdminEventWhenEventTopicArnDoenstExistsAndThrowAnException(){
        snsEventPublisher.sendAdminEvent(snsAdminEventMock);
        verify(snsClientMock, never()).publish(any(),any());
    }

    @Test
    void shouldNotSendEventAndLogAnErrorWhenAnyValueIsWrite() throws JsonProcessingException  {
        when(snsEventListenerConfigurationMock.getEventTopicArn()).thenReturn("eventTopicArn");
        when(mapperMock.writeValueAsString(snsEventMock)).thenThrow(JsonProcessingException.class);
        snsEventPublisher.sendEvent(snsEventMock);
        verify(snsClientMock, never()).publish(any(),any());
    }

}
