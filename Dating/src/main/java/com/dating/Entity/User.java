package com.dating.Entity;

import java.util.List;

public class User {

    private Long id;
    private String name;
    private int age;
    private String gender;
    private List<String> interests;

    public User() {
    }

    public User(Long id, String name, int age, String gender, List<String> interests) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.interests = interests;
    }
public User(String name, Gender gender, int age, List<String> interests) {
    this.name = name;
    this.gender = gender.name(); // convert enum to String
    this.age = age;
    this.interests = interests;
}

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getInterests() {
        return interests;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}

