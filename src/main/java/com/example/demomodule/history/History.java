package com.example.demomodule.history;

import jakarta.persistence.*;

@Entity
@Table(name = "t_history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hi_id")
    private Long id;

    @Column(name = "hi_module")
    private String module;

    @Column(name = "hi_identifier")
    private Long identifier;

    @Column(name = "hi_title")
    private String title;

    public History(){}

    public History(Long id, String module, Long identifier, String title, String description) {
        this.id = id;
        this.module = module;
        this.identifier = identifier;
        this.title = title;
        this.description = description;
    }

    @Column(name = "hi_description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
