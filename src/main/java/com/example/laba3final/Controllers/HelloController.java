package com.example.laba3final.Controllers;

import com.example.laba3final.models.Serialization.ReflectionDemo;
import com.example.laba3final.models.UniversityRelatedHuman;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;

public class HelloController {

    @FXML
    private ListView<String> objectList;
    @FXML
    private ListView<String> fieldList;
    @FXML
    private Button deleteObjectButton;
    @FXML
    private TextField inputValueTextArea;
    @FXML
    private ChoiceBox<String> objectClassChoiseBox;
    @FXML
    private TextField fieldValue;

    private HashMap<String,UniversityRelatedHuman> objectMap;
    private ArrayList<String> objectNamesList;

    @FXML
    void onCreateButtonClick(ActionEvent event) {
        if (!objectMap.containsKey(inputValueTextArea.getText())) {
            createObject(inputValueTextArea.getText(), objectClassChoiseBox.getValue());
        }
    }

    private void createObject(String objectName, String className) {
        Class clazz = null;
        try {
            clazz = Class.forName("com.example.laba3final.models." + className);
            Object obj = clazz.newInstance();
            objectMap.put(objectName, (UniversityRelatedHuman) obj);
            objectNamesList.add(objectName);
            objectList.setItems(FXCollections.observableList(objectNamesList));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

        }
    }

    @FXML
    void initialize() {
        objectNamesList = new ArrayList<>();
        objectClassChoiseBox.setItems(FXCollections.observableArrayList("Assistant","Lecturer","Staff","Student"));
        objectClassChoiseBox.setValue("Assistant");
        deleteObjectButton.setDisable(true);
        objectMap = new HashMap<>();
        objectList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String itemName = objectList.getSelectionModel().getSelectedItem();
            if (itemName != null) {
                fieldList.setItems(FXCollections.observableList(ReflectionDemo.getfieldNames(objectMap.get(itemName))));
            }
        });

        fieldList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            String itemName = fieldList.getSelectionModel().getSelectedItem();
            if (itemName != null) {
                fieldList.setItems(FXCollections.observableList(ReflectionDemo.getfieldNames(objectMap.get(itemName))));
            }
        });
    }



}