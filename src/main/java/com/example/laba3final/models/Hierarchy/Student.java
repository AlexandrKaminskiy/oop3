package com.example.laba3final.models.Hierarchy;

public class Student extends UniversityRelatedHuman {
    private String groupName;
    private double GPA;

    public Student(int age, String firstName, String lastName, String groupName, double GPA) {
        super(age, firstName, lastName);
        this.groupName = groupName;
        this.GPA = GPA;
    }

    public Student(){
        super(0, "", "");
        this.groupName = "";
        this.GPA = 0;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    @Override
    void goToUniversity() {
        System.out.println("Я иду в университет получать знания");
    }
    void study() {
        System.out.println("Я читаю книги");
    }
}
