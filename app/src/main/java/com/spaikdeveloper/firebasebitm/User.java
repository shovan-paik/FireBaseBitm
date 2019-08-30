package com.spaikdeveloper.firebasebitm;

class User {
    private String name;
    private String email;
    private int age;
    private String birthDate;

    public User() {
    }

    public User(String name, String email, int age, String birthDate) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
