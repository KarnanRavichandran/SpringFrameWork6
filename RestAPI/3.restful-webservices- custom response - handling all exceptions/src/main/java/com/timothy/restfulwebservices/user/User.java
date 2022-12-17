package com.timothy.restfulwebservices.user;

import java.time.LocalDate;

public class User {
    private int id;
    private String Name;
    private LocalDate birthdate;

    public User(int id, String name, LocalDate birthdate) {
        this.id = id;
        Name = name;
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return Name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
