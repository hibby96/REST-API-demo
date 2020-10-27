package com.revature.Objects;

public class Person {
    private String name;
    private int id;

    public Person() {
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public Person id(int id) {
        this.id = id;
        return this;
    }
}
