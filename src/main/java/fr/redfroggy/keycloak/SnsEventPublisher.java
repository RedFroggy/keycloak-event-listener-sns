package fr.redfroggy.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.admin.AdminEvent;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SnsEventPublisher {

    private final AmazonSNSAsync amazonSNSAsync;
    private final static ObjectMapper snsObjectMapper = new ObjectMapper();

    public SnsEventPublisher(AmazonSNSAsync amazonSNSAsync) {
        this.amazonSNSAsync = amazonSNSAsync;
    }

    public void send(String eventTopicArn, Event snsEvent) {
     
    }

    public void send(String adminEventTopicArn, AdminEvent SnsAdminEvent) {

    }

}
