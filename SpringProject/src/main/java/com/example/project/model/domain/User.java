package com.example.project.model.domain;

public class User {
    private final int id;
    private final String lastName;
    private final String firstName;

    public User(int id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}