package fr.redfroggy.keycloak;

import org.keycloak.events.admin.AdminEvent;

class SnsAdminEvent {
    private final AdminEvent adminEvent;
    private final String username;

    public SnsAdminEvent(AdminEvent adminEvent, String username) {
        this.adminEvent = adminEvent;
        this.username = username;
    }

    public AdminEvent getAdminEvent() {
        return adminEvent;
    }

    public String getUsername(){
        return username;
    }

}
