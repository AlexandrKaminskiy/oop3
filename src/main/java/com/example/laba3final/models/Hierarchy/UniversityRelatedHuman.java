package com.example.laba3final.models.Hierarchy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class UniversityRelatedHuman implements Serializable {
    private int age;
    private String firstName;
    public String lastName;
    abstract void goToUniversity();
    public UniversityRelatedHuman(int age, String firstName, String lastName) {
        this.age = age;
        this.firstName = null;
        this.lastName = lastName;
    }

    public UniversityRelatedHuman() {}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
