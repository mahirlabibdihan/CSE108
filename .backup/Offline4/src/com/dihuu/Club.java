package com.dihuu;

import java.lang.String;
public class Club {
    private int id;
    private String name;
    private int points;
    public Club()
    {
        points=0;
    }

    // Getters
    public int getId(){return id;}
    public int getPoints(){return points;}
    public String getName()
    {
        return name;
    }

    // Setter
    public void setPoints(int points){this.points=points;}
    public void setName(String name)
    {
        this.name=name;
    }
    public void setId(int id)
    {
        this.id=id;
    }

}
