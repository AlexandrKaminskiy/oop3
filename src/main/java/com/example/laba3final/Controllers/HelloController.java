package com.example.laba3final.Controllers;

import com.example.laba3final.models.Serialization.ReflectionDemo;
import com.example.laba3final.models.Serialization.SerializeDemo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

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
    @FXML
    private Button setValueButton;
    ReflectionDemo reflectionDemo;
    private String itemName;
    private String fieldName;
    @FXML
    void onCreateButtonClick(ActionEvent event) {
        reflectionDemo.createObject(inputValueTextArea.getText(), objectClassChoiseBox.getValue());
        objectList.setItems(FXCollections.observableArrayList(reflectionDemo.getObjectNamesList()));
    }

    @FXML
    void onClickDeserializeButton(ActionEvent event) {
        reflectionDemo.deserialize();
        objectList.setItems(FXCollections.observableArrayList(reflectionDemo.getObjectNamesList()));
        inputValueTextArea.setText("");
        fieldList.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        deleteObjectButton.setDisable(true);
        setValueButton.setDisable(true);
    }

    @FXML
    void onClickSerializeButton(ActionEvent event) {
        reflectionDemo.serialize();
    }

    @FXML
    void onClickSetValueButton(ActionEvent event) {
        reflectionDemo.setValue(fieldName, fieldValue.getText());
    }
    @FXML
    void onDeleteObjectButtonClick(ActionEvent event) {
        reflectionDemo.deleteObject(itemName);
        objectList.setItems(FXCollections.observableArrayList(reflectionDemo.getObjectNamesList()));
        inputValueTextArea.setText("");
        fieldList.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        deleteObjectButton.setDisable(true);
        setValueButton.setDisable(true);
    }
    @FXML
    void initialize() {
        System.err.println("ААААААААААААААА Я ХОЧУ ПИЦЦЦЦЦЦЦЦЦЦЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ");
        reflectionDemo = new ReflectionDemo();
        objectClassChoiseBox.setItems(FXCollections.observableList(reflectionDemo.CLASS_NAMES));
        objectClassChoiseBox.setValue("Assistant");
        deleteObjectButton.setDisable(true);
        setValueButton.setDisable(true);
        objectList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            itemName = objectList.getSelectionModel().getSelectedItem();
            if (itemName != null) {
                deleteObjectButton.setDisable(false);
                fieldList.setItems(FXCollections.observableList(reflectionDemo.getfieldNames(itemName)));
            }
        });

        fieldList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            fieldName = fieldList.getSelectionModel().getSelectedItem();
            if (itemName != null) {
                fieldValue.setText(String.valueOf(reflectionDemo.getValue(fieldName)));
                setValueButton.setDisable(false);
            }
        });
    }



}