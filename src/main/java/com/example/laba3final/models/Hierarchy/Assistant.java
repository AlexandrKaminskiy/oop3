package com.example.laba3final.models.Hierarchy;

public class Assistant extends Teacher {
    private int labCount;
    public Assistant(int age, String firstName, String lastName, int salary, String subject) {
        super(age, firstName, lastName, salary, subject);
        this.labCount = 0;
    }

    public Assistant(){
        super(0, "", "", 0, "");
        this.labCount = 0;
    }
    @Override
    void teach() {
        System.out.println("Проверяю лабораторные работы у студентов");
    }


}
