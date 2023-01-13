package fr.redfroggy.keycloak;

import org.keycloak.events.admin.AdminEvent;

public class SnsAdminEvent extends AdminEvent {
    private final AdminEvent adminEvent;

    public SnsAdminEvent(AdminEvent adminEvent) {
        this.adminEvent = adminEvent;
    }

    public AdminEvent getAdminEvent() {
        return adminEvent;
    }

}
