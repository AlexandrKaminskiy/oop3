package com.example.laba3final.models;

import java.io.Serializable;

public class Assistant extends Teacher implements Serializable {
    private Staff staff;
    public Assistant(int age, String firstName, String lastName, int salary, String subject) {
        super(age, firstName, lastName, salary, subject);
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Assistant(){
        super(0, "", "", 0, "");
        staff = new Staff();
    }
    @Override
    void teach() {
        System.out.println("Проверяю лабораторные работы у студентов");
    }


}
