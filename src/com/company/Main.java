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
    public static void main(String[] args) throws IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
        new Main();
    }
    Main() throws IllegalAccessException, IOException, ClassNotFoundException, InstantiationException {
//        humanList = new ArrayList<>();
//        UniversityRelatedHuman human = new Lecturer(10, "erg", "feef", 12, "wfe",10,"wf");
//        humanList.add(human);
//        UniversityRelatedHuman human1 = new Assistant();
//        humanList.add(human1);
//        ReflectionDemo reflectionDemo = new ReflectionDemo(humanList);
//        reflectionDemo.serialize();

//        DeserializeDemo demo = new DeserializeDemo("out.xml");
//        demo.deserialize();
        DeserializeDemo1 demo1 = new DeserializeDemo1("out.xml");
        demo1.deserialize();
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
