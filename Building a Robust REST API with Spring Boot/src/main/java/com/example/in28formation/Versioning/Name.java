package com.example.in28formation.Versioning;

public class Name {
    private String FirstName;
    private String LastName;

    public Name(String firstName, String lastName) {
        FirstName = firstName;
        LastName = lastName;
    }

    public Name() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
