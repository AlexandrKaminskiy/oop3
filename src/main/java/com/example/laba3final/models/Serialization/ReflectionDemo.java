package com.example.laba3final.models.Serialization;

import com.example.laba3final.models.UniversityRelatedHuman;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReflectionDemo {
    final public List<String> CLASS_NAMES = List.of("Assistant","Lecturer","Staff","Student");
    private HashMap<String, UniversityRelatedHuman> objectMap;
    private ArrayList<String> objectNamesList;
    private Object currentObject;

    public ReflectionDemo() {
        this.objectMap = new HashMap<>();
        this.objectNamesList = new ArrayList<>();
    }

    public ArrayList<String> getObjectNamesList() {
        return objectNamesList;
    }

    public ArrayList<String> getfieldNames(String itemName) {
        ArrayList<String> fieldNames = new ArrayList<>();

        Object obj = objectMap.get(itemName);
        currentObject = obj;
        List<Field> fields = getFields(obj);
        for (var field : fields) {
            fieldNames.add(field.getName());
        }

        return fieldNames;
    }
    private List<Field> getFields(Object obj) {
        Class clazz = obj.getClass();
        ArrayList<Field> fieldList = new ArrayList<>();
        while (!clazz.equals(Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            fieldList.addAll(Arrays.asList(fields));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }
    public Object getValue(String fieldName) {
        Class clazz = currentObject.getClass();
        while (!clazz.equals(Object.class)) {
            try {

                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                System.out.println(field.get(currentObject));
                return field.get(currentObject);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }

    public void createObject(String objectName, String className) {
        if (objectName != null) {
            if (!objectMap.containsKey(objectName)) {
                Class clazz = null;
                try {
                    clazz = Class.forName("com.example.laba3final.models." + className);
                    Object obj = clazz.newInstance();
                    objectMap.put(objectName, (UniversityRelatedHuman) obj);
                    objectNamesList.add(objectName);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

                }
            }
        }
    }


    public void setValue(String fieldName,String value) {
        Class clazz = currentObject.getClass();
        while (!clazz.equals(Object.class)) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                System.out.println(field.get(currentObject));
                setValue(field,currentObject,value);
                return;

            } catch (IllegalAccessException | NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
    }

    public static void setValue(Field field, Object currentObject, String value) {
        try{
            if (int.class.equals(field.getType())) {
                field.set(currentObject, Integer.valueOf(value));
            } else if (double.class.equals(field.getType())) {
                field.set(currentObject, Double.valueOf(value));
            } else if (boolean.class.equals(field.getType())) {
                field.set(currentObject, Boolean.valueOf(value));
            } else if (String.class.equals(field.getType()))
                field.set(currentObject, value);
        } catch (Exception e){
            if (field.getType().equals(String.class)) {
                try {
                    field.set(currentObject,"");
                } catch (IllegalAccessException ex) {}
            }
        }
    }
    public void deleteObject(String itemName) {
        objectNamesList.remove(itemName);
        objectMap.remove(itemName);
    }

    public void serialize() {
        ArrayList<UniversityRelatedHuman> humanArrayList = new ArrayList<>();
        for (var val : objectMap.values()) {
            humanArrayList.add(val);
        }
        SerializeDemo serializeDemo = new SerializeDemo(humanArrayList);
        try {
            serializeDemo.serialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void deserialize() {
        DeserializeDemo deserializeDemo = new DeserializeDemo("out.xml");
        try {
            var arr = deserializeDemo.deserialize();
            objectMap.clear();
            objectNamesList.clear();
            int c = 0;
            for (var el : arr) {
                objectMap.put("elem" + c, el);
                objectNamesList.add("elem" + c);
                c++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}