package com.example.laba3final.models.Serialization;

import com.example.laba3final.models.Hierarchy.UniversityRelatedHuman;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

public class ReflectionDemo {
    final public List<String> CLASS_NAMES = List.of("Assistant","Lecturer","Staff","Student");
    private HashMap<String, UniversityRelatedHuman> objectMap;
    private ArrayList<String> objectNamesList;
    private Object currentObject;
    private String currentName;
    private String lastName;
    public ReflectionDemo() {
        this.objectMap = new HashMap<>();
        this.objectNamesList = new ArrayList<>();
    }

    public ArrayList<String> getObjectNamesList() {
        return objectNamesList;
    }

    public ArrayList<ShowInfoModel> getfieldNames(String itemName) {
        ArrayList<ShowInfoModel> fieldNames = new ArrayList<>();

        Object obj = objectMap.get(itemName);
        currentObject = obj;
        currentName = itemName;
        List<Field> fields = getFields(obj);

        for (var field : fields) {
            try {
                field.setAccessible(true);
                fieldNames.add(new ShowInfoModel(field.getName(), String.valueOf(field.get(obj))));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
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
            } catch (IllegalAccessException | NoSuchFieldException | NullPointerException e) {
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }

    public Object getAllValues(String fieldName) {
        Class clazz = currentObject.getClass();
        while (!clazz.equals(Object.class)) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                System.out.println(field.get(currentObject));
                return field.get(currentObject);
            } catch (IllegalAccessException | NoSuchFieldException | NullPointerException e) {
                clazz = clazz.getSuperclass();
            }
        }

        return null;
    }
    public void createObject(String objectName, String className) {
        if (objectName != null) {
            lastName = objectName;
            int i = 0;
            while (objectMap.containsKey(objectName)) {
                if (i == 0) {
                    objectName += "_";
                    i++;
                }
                objectName += "1";
            }

            Class clazz = null;
            try {
                clazz = Class.forName("com.example.laba3final.models.Hierarchy." + className);
                Object obj = clazz.newInstance();
                Field field = clazz.getField("lastName");
                field.set(obj,lastName);
                objectMap.put(objectName, (UniversityRelatedHuman) obj);
                objectNamesList.add(objectName);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {

            }

        }
    }


    public String setValue(String fieldName,String value) {
        Class clazz = currentObject.getClass();
        String objectName = currentName;
        while (!clazz.equals(Object.class)) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                System.out.println(field.get(currentObject));
                String temp = String.valueOf(field.get(currentObject));
                setValue(field,currentObject,value);
                if (field.getName().equals("lastName")) {
                    int i = 0;
                    while (objectMap.containsKey(value)) {
                        if (i == 0) {
                            value += "_";
                            i++;
                        }
                        value += "1";
                    }
                    objectMap.remove(objectName);
                    objectMap.put(value, (UniversityRelatedHuman) currentObject);
                    objectNamesList.set(objectNamesList.indexOf(currentName), value);
                    objectName = value;
                }
                return objectName;

            } catch (IllegalAccessException | NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }

        }
        return objectName;
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
            JOptionPane.showMessageDialog(null, "Incorrect value");
            if (field.getType().equals(String.class)) {
                try {
                    field.set(currentObject,"");
                    JOptionPane.showMessageDialog(null, "Incorrect value");
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