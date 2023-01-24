package fr.redfroggy.keycloak;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SnsEventListenerConfigurationTest {

    private final SnsEventListenerConfiguration action = new SnsEventListenerConfiguration("eventTopicArn", "adminEventTopicArn");
    
    @Test
    void shouldGetEventTopicArn() {
        assertThat(action.getEventTopicArn()).isEqualTo("eventTopicArn");
    }

    @Test
    void shouldGetAdminEventTopicArn() {
        assertThat(action.getAdminEventTopicArn()).isEqualTo("adminEventTopicArn");
    }

}
