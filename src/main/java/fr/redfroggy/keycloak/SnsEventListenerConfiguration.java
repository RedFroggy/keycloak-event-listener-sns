package fr.redfroggy.keycloak;

public class SnsEventListenerConfiguration {
    
    private final String eventTopicArn;
    private final String adminEventTopicArn;

    public SnsEventListenerConfiguration(String eventTopicArn, String adminEventTopicArn) {
        this.eventTopicArn = eventTopicArn;
        this.adminEventTopicArn = adminEventTopicArn;
    }

    public String getEventTopicArn() {
        return eventTopicArn;
    }

    public String getAdminEventTopicArn() {
        return adminEventTopicArn;
    }
    
}
