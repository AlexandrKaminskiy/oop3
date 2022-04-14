package com.example.laba3final.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class HelloController {


    @FXML
    private ListView<String> objectList;

    @FXML
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python");
        objectList.setItems(langs);
    }

}