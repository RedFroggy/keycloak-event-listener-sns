package fr.redfroggy.keycloak;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SnsEventListenerProviderFactory implements EventListenerProviderFactory {

    public static final String SNS_EVENT_LISTENER = "SNS_EVENT_LISTENER";
    private SnsEventListenerConfiguration snsEventListenerConfiguration;
    private String CONFIG_EVENT_TOPIC_ARN = "event-topic-arn";
    private String CONFIG_ADMIN_EVENT_TOPIC_ARN = "admin-event-topic-arn";

    @Override
    public void close() {        
    }

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        AmazonSNSAsync snsClient = AmazonSNSAsyncClientBuilder.standard().build();
        ObjectMapper mapper = new ObjectMapper();
        return new SnsEventListenerProvider(new SnsEventPublisher(snsClient, snsEventListenerConfiguration, mapper));
    }

    @Override
    public String getId() {
        return SNS_EVENT_LISTENER;
    }

    @Override
    public void init(Config.Scope config) {
        String configEventTopicArn = config.get(CONFIG_EVENT_TOPIC_ARN, System.getenv("KC_SNS_EVENT_TOPIC_ARN"));
        String congifAdminEventTopicArn = config.get(CONFIG_ADMIN_EVENT_TOPIC_ARN, System.getenv("KC_SNS_ADMIN_EVENT_TOPIC_ARN")); 
        snsEventListenerConfiguration = new SnsEventListenerConfiguration(configEventTopicArn, congifAdminEventTopicArn);
    }

    @Override
    public void postInit(KeycloakSessionFactory sessionFactory) {        
    }

}
