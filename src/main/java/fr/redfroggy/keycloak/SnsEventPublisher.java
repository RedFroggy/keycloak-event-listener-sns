package fr.redfroggy.keycloak;

import org.jboss.logging.Logger;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class SnsEventPublisher {

    private final AmazonSNSAsync snsClient;
    private final ObjectMapper mapper;
    private static final Logger log = Logger.getLogger(SnsEventPublisher.class);
    private final SnsEventListenerConfiguration snsEventListenerConfiguration;

    public SnsEventPublisher(AmazonSNSAsync snsClient, SnsEventListenerConfiguration snsEventListenerConfiguration, ObjectMapper mapper) {
        this.snsClient = snsClient;
        this.snsEventListenerConfiguration = snsEventListenerConfiguration;
        this.mapper = mapper;
    }

    public void sendEvent(SnsEvent snsEvent) {
        if (snsEventListenerConfiguration.getEventTopicArn() == null) {
            log.warn("No topicArn specified. Can not send event to AWS SNS! Set environment variable KC_SNS_EVENT_TOPIC_ARN");
            return;
        }
        publishEvent(snsEvent,snsEventListenerConfiguration.getEventTopicArn());
    }

    public void sendAdminEvent(SnsAdminEvent snsAdminEvent) {
        if (snsEventListenerConfiguration.getAdminEventTopicArn() == null) {
            log.warn("No topicArn specified. Can not send event to AWS SNS! Set environment variable KC_SNS_ADMIN_EVENT_TOPIC_ARN");
            return;
        }
        publishEvent(snsAdminEvent, snsEventListenerConfiguration.getAdminEventTopicArn());
    }   

    private void publishEvent(Object event, String topicArn){        
        try {
            snsClient.publish(topicArn, mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            log.error("The payload wasn't created.", e);
            return;
        }
        
    }

}
