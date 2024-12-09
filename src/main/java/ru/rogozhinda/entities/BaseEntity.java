package ru.rogozhinda.entities;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class BaseEntity {

    protected String id;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId() {
        return id;
    }

    protected void setId(String id) {
        this.id = id;
    }
}