package com.dihu.species;

import com.dihu.Venomous;
import com.dihu.genus.Arachnid;

public class RedBackSpider extends Arachnid implements Venomous {
    public RedBackSpider(String name, int age) {
        super(name, age);
    }

    @Override
    public boolean isLethalToAdultHumans() {
        return false;
    }
}
