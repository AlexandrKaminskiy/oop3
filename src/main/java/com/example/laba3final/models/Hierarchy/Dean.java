package com.example.laba3final.models.Hierarchy;

public class Dean extends Employee {
    private String faculity;

    public Dean(int age, String firstName, String lastName, int salary, String faculity) {
        super(age, firstName, lastName, salary);
        this.faculity = faculity;
    }

    public Dean() {
        super(0, null, null, 0);
        this.faculity = null;
    }
}
