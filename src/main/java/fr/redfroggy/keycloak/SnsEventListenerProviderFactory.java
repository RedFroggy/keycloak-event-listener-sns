package fr.redfroggy.keycloak;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class SnsEventListenerProviderFactory implements EventListenerProviderFactory {

    public static final String SNS_EVENT_LISTENER = "SNS_EVENT_LISTENER";
    private SnsEventListenerConfiguration snsEventListenerConfiguration;
    private String CONFIG_EVENT_TOPIC_ARN = snsEventListenerConfiguration.getEventTopicArn();
    private String CONFIG_ADMIN_EVENT_TOPIC_ARN = snsEventListenerConfiguration.getAdminEventTopicArn();
    private String configEventTopicArn;
    private String congifAdminEventTopicArn;

    @Override
    public void close() {        
    }

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new SnsEventListenerProvider();
    }

    @Override
    public String getId() {
        return SNS_EVENT_LISTENER;
    }

    @Override
    public void init(Config.Scope config) {
        configEventTopicArn = config.get(CONFIG_EVENT_TOPIC_ARN, System.getenv("KC_SNS_EVENT_TOPIC_ARN"));
        congifAdminEventTopicArn = config.get(CONFIG_ADMIN_EVENT_TOPIC_ARN, System.getenv("KC_SNS_ADMIN_EVENT_TOPIC_ARN")); 
    }

    @Override
    public void postInit(KeycloakSessionFactory sessionFactory) {        
    }

}
