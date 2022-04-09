package com.company;

import java.io.Serializable;
import java.util.ArrayList;


public class Lecturer extends Teacher implements Serializable {
    public TestClass testClass;
    //Integer ii[];
    public ArrayList<TestClass> testClasses;
    public Lecturer(int age, String firstName, String lastName, int salary, String subject,int i,String str) {
        super(age, firstName, lastName, salary, subject);
        testClass = new TestClass(i,str);

        testClasses = new ArrayList<>();
        testClasses.add(testClass);
        testClasses.add(testClass);
      //  ii = new Integer[]{1, 2, 3, 4};
    }

    @Override
    void teach() {
        System.out.println("Читаю лекции студентам");
    }

    public Lecturer(){}
}
