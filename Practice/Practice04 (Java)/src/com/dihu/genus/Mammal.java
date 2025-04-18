package com.dihu.genus;

import com.dihu.Animal;

public class Mammal extends Animal {
    private String bloodType;

    public Mammal(String name, int age, String bloodType) {
        super(name, age);
        this.bloodType = bloodType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void printBloodType() {
        System.out.print(", " + bloodType + "!");
    }
}
