package com.example.demomodule.notification;

public class HistoryNotification {

    private String module;

    private Long identifier;

    private String title;

    private String description;

    public HistoryNotification() {}

    public HistoryNotification(String module, Long identifier, String title, String description) {
        this.module = module;
        this.identifier = identifier;
        this.title = title;
        this.description = description;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
