package com.example.laba3final.models.Hierarchy;


public class Lecturer extends Teacher {

    public Lecturer(int age, String firstName, String lastName, int salary, String subject,int i,String str) {
        super(age, firstName, lastName, salary, subject);
    }

    @Override
    void teach() {
        System.out.println("Читаю лекции студентам");
    }

    public Lecturer(){
        super(0, "", "", 0, "");

    }
}
