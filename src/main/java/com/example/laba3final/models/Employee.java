package com.example.laba3final.models;

public abstract class Employee extends UniversityRelatedHuman{
    int salary;

    public Employee(int age, String firstName, String lastName, int salary) {
        super(age, firstName, lastName);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    void goToUniversity() {
        System.out.println("Я иду в университет и буду там работать");
    }

    public Employee(){}

}
