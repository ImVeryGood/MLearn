package com.example.mlearn;

public class ModelBean {
    private String name;
    private int age;
    private int type;

    public int getType() {
        return type;

    }

    public ModelBean setType(int type) {
        this.type = type;
        return this;
    }

    public int getAge() {
        return age;
    }

    public ModelBean setAge(int age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelBean setName(String name) {
        this.name = name;
        return this;
    }
}
