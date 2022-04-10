package com.company;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class ReflectionDemo {

    private ArrayList<UniversityRelatedHuman> humanArrayList;
    private FileWriter writer;
    private Stack<String> selectorName;
    private int tabsCount;
    ReflectionDemo(ArrayList<UniversityRelatedHuman> humanArrayList) throws IOException {
        this.humanArrayList = humanArrayList;
        writer = new FileWriter("out.xml");
        selectorName = new Stack<>();
        tabsCount = 0;
    }


    public void serialize() throws IOException, IllegalAccessException {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

        for (var human : humanArrayList) {
            String s = String.valueOf(human.getClass()).substring(6);
            writer.write( "<object class=\"" + s + "\">\n");
            tabsCount++;
            getFields(human);
            writer.write( "</object>\n");
        }
        writer.flush();
    }

    private void getFields(Object o) throws IllegalAccessException {
        try {
            Class claz = o.getClass();

            while (claz != Object.class) {
                Field[] fields = claz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    writer.write( tabs() + "<" + field.getName() + ">\n");
                    selectorName.push(tabs() + "</" + field.getName() + ">\n");
                    tabsCount++;
                    if (!field.getType().isPrimitive() && (field.getType() != String.class)) {
                        if (Collection.class.isAssignableFrom(field.getType())) {
                            showCollection(field, o);
                        } else if (field.getType().isArray()) {
                            showArray(field,o);
                        } else getFields(field.get(o));
                        writer.write(selectorName.pop());
                        tabsCount--;
                        continue;
                    }
                    writer.write(tabs() + field.get(o) + "\n");

                    writer.write(selectorName.pop());
                    tabsCount--;
                }
                claz = claz.getSuperclass();
            }
        }
        catch (NullPointerException | IOException e){
            System.out.println("null");
        }

    }

    private String tabs() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tabsCount; i++){
            builder.append("\t");
        }
        return String.valueOf(builder);
    }

    private void showCollection(Field field, Object o) {
        try {
            Collection arr = (Collection) field.get(o);
            for (var el : arr) {
                getFields(el);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void showArray(Field field, Object array) throws IllegalAccessException, IOException {
        Object[] objects = (Object[]) field.get(array);
        for (Object obj : objects) {
          //  getFields(obj);
        }
    }


}
