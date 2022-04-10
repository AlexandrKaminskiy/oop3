package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeserializeDemo {
    private ArrayList<UniversityRelatedHuman> humanArrayList;
    private String path;

    DeserializeDemo(String path) {
        humanArrayList = new ArrayList<>();
        this.path = path;
    }

    public ArrayList<UniversityRelatedHuman> deserialize() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        humanArrayList.clear();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String s;
            bufferedReader.readLine();
            Class clazz;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.startsWith("<object")){
                    UniversityRelatedHuman human = (UniversityRelatedHuman) createObject(s.substring(15, s.length()-2));
                    clazz = human.getClass();
                    clazz.getDeclaredFields();
                    System.out.println(human.getClass());
                }

            }
        }
        return null;
    }

    private Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName(className);
        Object result = clazz.newInstance();
        return result;
    }
}
