package com.example.demo;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User(){};

    public User(long id, String name, String lastName, Byte age) {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
