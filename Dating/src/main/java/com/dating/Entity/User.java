package com.dating.Entity;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private Gender gender;
    private int age;
    private List<String> interests;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }

    public User(Long id, String name, Gender gender, int age, List<String> interests) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.interests = interests;
    }

    public User(String name, Gender gender, int age, List<String> interests) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.interests = interests;
    }

    public User() {}
}
