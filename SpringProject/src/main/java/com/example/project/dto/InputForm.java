package com.example.project.dto;

import javax.validation.constraints.NotBlank;

public class InputForm {
    @NotBlank(message = "Enter last name!")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
