package com.example.laba3final.models.Serialization;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReflectionDemo {
    public static ArrayList<String> getfieldNames(Object obj) {
        ArrayList<String> fieldNames = new ArrayList<>();
        Class clazz = obj.getClass();
        while (!clazz.equals(Object.class)){
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                fieldNames.add(field.getName());
            }
            clazz = clazz.getSuperclass();
        }

        return fieldNames;
    }
    public static Object getValue(Object obj, String fieldName) {
        Class clazz = obj.getClass();
        try {
            Field field = clazz.getField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
}
