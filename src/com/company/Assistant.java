package com.company;

import java.io.Serializable;

public class Assistant extends Teacher implements Serializable {
    Assistant assistant;
    public Assistant(int age, String firstName, String lastName, int salary, String subject) {
        super(age, firstName, lastName, salary, subject);
    }
    public Assistant(){}
    @Override
    void teach() {
        System.out.println("Проверяю лабораторные работы у студентов");
    }


}
