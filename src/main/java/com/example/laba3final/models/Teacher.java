package com.example.laba3final.models;

public abstract class Teacher extends Employee{
    private String subject;
    abstract void teach();

    public Teacher(int age, String firstName, String lastName, int salary, String subject) {
        super(age, firstName, lastName, salary);
        this.subject = subject;
    }
    public Teacher(){}
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
