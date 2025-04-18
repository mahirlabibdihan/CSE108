package com.dihu.species;

import com.dihu.Venomous;
import com.dihu.genus.Reptile;

public class EasternBrownSnake extends Reptile implements Venomous {
    public EasternBrownSnake(String name, int age) {
        super(name, age);
    }

    @Override
    public boolean isLethalToAdultHumans() {
        return true;
    }
}
