package com.company;

import java.io.Serializable;

public class Staff extends Employee implements Serializable {
    private String typeOfWork;

    public Staff(int age, String firstName, String lastName, int salary, String typeOfWork) {
        super(age, firstName, lastName, salary);
        this.typeOfWork = typeOfWork;
    }
    public Staff(){}
    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    void work() {
        System.out.println("Выполняю работу, не связанную с учебой в университете");
    }
}
