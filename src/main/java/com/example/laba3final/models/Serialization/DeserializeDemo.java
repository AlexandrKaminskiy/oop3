package com.example.laba3final.models.Serialization;

import com.example.laba3final.models.UniversityRelatedHuman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class DeserializeDemo {
    private ArrayList<UniversityRelatedHuman> humanArrayList;
    private String path;
    private BufferedReader bufferedReader;
    private String file = new String();
    public DeserializeDemo(String path) {
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
        }
        humanArrayList = getList();
        return humanArrayList;
    }

    private ArrayList<UniversityRelatedHuman> getList() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Pattern pat = Pattern.compile("(?<=>).*");
        Matcher mat = pat.matcher(file);
        if (mat.find()){
            file = file.substring(mat.start(),mat.end());
        }
        while (!file.isBlank()) {
            Pattern pattern = Pattern.compile("<object.*?>");
            Matcher matcher = pattern.matcher(file);
            if (matcher.find()) {
                String s = matcher.group(0);
                System.out.println(s);
                String objectInfo = extractElement("object", file);

                UniversityRelatedHuman human = (UniversityRelatedHuman) createObject(s.substring(15, s.length() - 2), objectInfo);
                humanArrayList.add(human);
                file = deleteInfo("object",file);
            }
        }
        return humanArrayList;
    }

    private Object createObject(String className,String objectInfo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Class clazz = Class.forName(className);
        Object result = clazz.newInstance();

        while (clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (var field : fields) {
                field.setAccessible(true);
                String value = extractElement(field.getName(), objectInfo);
                if (!value.isEmpty()) {
                    if (!field.getType().isPrimitive() && (field.getType() != String.class)) {
                        if (Collection.class.isAssignableFrom(field.getType())) {
                            field.set(result, setCollection(field, value));
                            System.out.println(field.getType());
                        } else if (field.getType().isArray()) {
//                        showArray(field,o);
                        } else {
                            field.set(result, createObject(String.valueOf(field.getType()).substring(6), value));
                        }
                        continue;
                    }

                    if (int.class.equals(field.getType())) {
                        field.set(result, Integer.valueOf(value));
                    } else if (double.class.equals(field.getType())) {
                        field.set(result, Double.valueOf(value));
                    } else if (boolean.class.equals(field.getType())) {
                        field.set(result, Boolean.valueOf(value));
                    } else field.set(result, value);

                    System.out.println(field.getName());
                } else field.set(result, null);
            }
            clazz = clazz.getSuperclass();
        }

        return result;
    }

    private Collection setCollection(Field field, String value) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {

        Class clazz = Class.forName(String.valueOf(field.getType()).substring(6));
        Collection arr = (Collection) clazz.newInstance();
        while (!value.equals("")) {
            Pattern pattern = Pattern.compile("(?<=\").*?(?=\")");
            Matcher matcher = pattern.matcher(value);
            if (matcher.find()) {
                String className = matcher.group(0);
                Object elem = createObject(className, extractElement("element", value));
                arr.add(elem);
                value = deleteInfo("element", value);
                System.out.println(arr);
            }
        }
        return arr;
    }

    private String extractElement(String fieldName, String file) {
        String result = new String();
        String myRegexp = String.format("(?<=%s.{0,100}?>).*?(?=%s)", "<"+fieldName,"</"+fieldName + ">");

        Pattern pattern = Pattern.compile(myRegexp);
        Matcher matcher = pattern.matcher(file);
        if (matcher.find()) {
            result = matcher.group(0);
            System.out.println(matcher.group(0));
        }
        return result;
    }
    private String deleteInfo(String fieldName, String file) {

        String myRegexp;
        myRegexp = String.format("<%s.*?>.*?</%s>", fieldName, fieldName);
        Pattern pattern = Pattern.compile(myRegexp);
        Matcher matcher = pattern.matcher(file);
        if (matcher.find()) {
            file = file.replace(matcher.group(0),"");
        }
        return file;
    }


}
