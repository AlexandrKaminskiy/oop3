package com.example.laba3final.Controllers;

import com.example.laba3final.models.Serialization.ReflectionDemo;
import com.example.laba3final.models.Serialization.SerializeDemo;
import com.example.laba3final.models.Serialization.ShowInfoModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HelloController {

    @FXML
    private ListView<String> objectList;

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
    @FXML
    private TableView<ShowInfoModel> tableView;
    @FXML
    private TableColumn<ShowInfoModel, String> field;
    @FXML
    private TableColumn<ShowInfoModel, String> value;

    private ReflectionDemo reflectionDemo;

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
        tableView.setItems(FXCollections.observableArrayList(new ArrayList<>()));
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
        tableView.setItems(FXCollections.observableList(reflectionDemo.getfieldNames(itemName)));
    }
    @FXML
    void onDeleteObjectButtonClick(ActionEvent event) {
        reflectionDemo.deleteObject(itemName);
        objectList.setItems(FXCollections.observableArrayList(reflectionDemo.getObjectNamesList()));
        inputValueTextArea.setText("");
        tableView.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        deleteObjectButton.setDisable(true);
        setValueButton.setDisable(true);
    }
    @FXML
    void initialize() {
        reflectionDemo = new ReflectionDemo();
        objectClassChoiseBox.setItems(FXCollections.observableList(reflectionDemo.CLASS_NAMES));
        objectClassChoiseBox.setValue("Assistant");
        field.setCellValueFactory(new PropertyValueFactory<ShowInfoModel,String>("fieldName"));
        value.setCellValueFactory(new PropertyValueFactory<ShowInfoModel,String>("value"));
        deleteObjectButton.setDisable(true);
        setValueButton.setDisable(true);
        objectList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            itemName = objectList.getSelectionModel().getSelectedItem();
            if (itemName != null) {
                deleteObjectButton.setDisable(false);
                tableView.setItems(FXCollections.observableList(reflectionDemo.getfieldNames(itemName)));
            }
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            fieldName = tableView.getSelectionModel().selectedItemProperty().get().getFieldName();
            if (itemName != null) {
                fieldValue.setText(tableView.getSelectionModel().selectedItemProperty().get().getValue());
                setValueButton.setDisable(false);
            }
        });
    }
}