package com.company;

import java.io.Serializable;

public class TestClass implements Serializable {
    private int testInt;

    private String testString;

    public int getTestInt() {
        return testInt;
    }

    public void setTestInt(int testInt) {
        this.testInt = testInt;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    public TestClass() {}

    public TestClass(int i, String c) {
        this.testInt = i;
        this.testString = c;

    }
}
