package com.company;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    ArrayList<UniversityRelatedHuman> humanList;
    public static void main(String[] args) throws IllegalAccessException, IOException {
        new Main();
    }
    Main() throws IllegalAccessException, IOException {
        humanList = new ArrayList<>();
        UniversityRelatedHuman human = new Lecturer(10, "erg", "feef", 12, "wfe",10,"wf");
        humanList.add(human);

        ReflectionDemo reflectionDemo = new ReflectionDemo(humanList);
        reflectionDemo.serialize();
    }

    void testEncode(Lecturer lecturer) {
        try {
            XMLEncoder e = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream("Test.xml")));
            e.writeObject(lecturer);
            e.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
