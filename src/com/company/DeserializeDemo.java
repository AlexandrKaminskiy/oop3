package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class DeserializeDemo {
    private ArrayList<UniversityRelatedHuman> humanArrayList;
    private String path;
    private BufferedReader bufferedReader;
    DeserializeDemo(String path) {
        humanArrayList = new ArrayList<>();
        this.path = path;
    }

    public ArrayList<UniversityRelatedHuman> deserialize() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        humanArrayList.clear();
        try (FileReader fileReader = new FileReader(path)) {
            bufferedReader = new BufferedReader(fileReader);

            String s;
            bufferedReader.readLine();
            Class clazz;
            Field[] fields;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.startsWith("<object")){
                    UniversityRelatedHuman human = (UniversityRelatedHuman) createObject(s.substring(15, s.length()-2));
                    clazz = human.getClass();
                    fields = clazz.getDeclaredFields();
                    System.out.println(human.getClass());
                }
            }
        }
        return null;
    }

    private Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Class clazz = Class.forName(className);
        Object result = clazz.newInstance();
        String fieldName;
        while (clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (var field : fields) {
                field.setAccessible(true);
                while (!(fieldName = bufferedReader.readLine()).replace("\t","").contains("<" + field.getName()))
                    System.out.println(fieldName);
                String value = bufferedReader.readLine().replace("\t","");
                if (!field.getType().isPrimitive() && (field.getType() != String.class)) {
                    if (Collection.class.isAssignableFrom(field.getType())) {
                        setCollection(field,result);
                    } else if (field.getType().isArray()) {
//                        showArray(field,o);
                    } else field.set(result, createObject(String.valueOf(field.getType()).substring(6)));
                    continue;
                }
                System.out.println(field.getType() == int.class);
                if (int.class.equals(field.getType())) {
                    field.set(result, Integer.valueOf(value));
                } else if(double.class.equals(field.getType())) {
                    field.set(result, Double.valueOf(value));
                } else if(boolean.class.equals(field.getType())) {
                    field.set(result, Boolean.valueOf(value));
                } else field.set(result, value);

                bufferedReader.readLine();
                System.out.println(field.getName());
            }
            clazz = clazz.getSuperclass();
        }

        return result;
    }

    private Collection setCollection(Field field, Object result) {
        try {
            Collection arr = (Collection) field.get(result);
            for (var el : arr) {

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
