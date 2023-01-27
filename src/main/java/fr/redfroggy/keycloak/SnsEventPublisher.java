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

    public SnsEventPublisher(AmazonSNSAsync snsClient, SnsEventListenerConfiguration snsEventListenerConfiguration,
            ObjectMapper mapper) {
        this.snsClient = snsClient;
        this.snsEventListenerConfiguration = snsEventListenerConfiguration;
        this.mapper = mapper;
    }

    public void sendEvent(SnsEvent snsEvent) {
        try {
            if (snsEventListenerConfiguration.getEventTopicArn() == null) {
                throw new Exception();
            }
            publishEvent(snsEvent, snsEventListenerConfiguration.getEventTopicArn());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAdminEvent(SnsAdminEvent snsAdminEvent) {
        try {
            if (snsEventListenerConfiguration.getAdminEventTopicArn() == null) {
                throw new Exception();
            }
            publishEvent(snsAdminEvent, snsEventListenerConfiguration.getAdminEventTopicArn());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void publishEvent(Object event, String topicArn) {
        try {
            snsClient.publish(topicArn, mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            log.error("The payload wasn't created.", e);
            return;
        }
    }
}
