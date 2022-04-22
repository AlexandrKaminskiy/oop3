package com.example.laba3final.models.Serialization;

public class ShowInfoModel {
    private String fieldName;
    private String value;

    public String getFieldName() { return fieldName; }

    public String getValue() {
        return value;
    }

    public ShowInfoModel(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }
}
