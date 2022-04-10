package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeserializeDemo1 {
    private ArrayList<UniversityRelatedHuman> humanArrayList;
    private String path;
    private BufferedReader bufferedReader;
    private String file = new String();
    DeserializeDemo1(String path) {
        humanArrayList = new ArrayList<>();
        this.path = path;
    }
    public ArrayList<UniversityRelatedHuman> deserialize() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        humanArrayList.clear();
        try (FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            StringBuilder tempFile = new StringBuilder();
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                tempFile.append(s);
            }
            file = String.valueOf(tempFile);
            file = file.replace("\t","");
            extractElement("testClasses");

        }
        return null;
    }
    private String extractElement(String className) {
        String myRegexp = String.format("(?<=%s>).*?(?=%s)", "<"+className,"</"+className + ">");
        Pattern pattern = Pattern.compile("(?<=<testInt>).*?(?=</testInt>)");
        System.out.println(Pattern.matches(pattern.pattern(),file));

        return null;
    }
}
