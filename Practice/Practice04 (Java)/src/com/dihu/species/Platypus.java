package com.dihu.species;

import com.dihu.Venomous;
import com.dihu.genus.Mammal;

public class Platypus extends Mammal implements Venomous {
    private String bloodType;

    public Platypus(String name, int age) {
        super(name, age, "Warm-Blooded");
    }

    @Override
    public boolean isLethalToAdultHumans() {
        return false;
    }

}
