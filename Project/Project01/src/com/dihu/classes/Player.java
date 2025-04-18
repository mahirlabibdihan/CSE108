package com.dihu.classes;

import com.dihu.IO.Color;

import java.lang.String;

public class Player {
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String position;
    private int number;
    private double weeklySalary;

    public Player() {

    }

    public Player(String name, String country, int age, double height, String club, String position, int number, double weeklySalary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.club = club;
        this.position = position;
        this.number = number;
        this.weeklySalary = weeklySalary;
    }

    // Make a copy of player
    public static Player copy(Player p) {
        return new Player(
                p.getName(),
                p.getCountry(),
                p.getAge(),
                p.getHeight(),
                p.getClub(),
                p.getPosition(),
                p.getNumber(),
                p.getWeeklySalary()
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }


    @Override
    public String toString() {

        return String.format(
                        "╔═════════════════════════════════════════════════╗\n"+
                        "║  %s%-33s%s              ║\n"+
                        "╠═══════════════════╦═════════════════════════════╣\n"+
                        "║  Country          ║  %-27s║\n" +
                        "║  Age (years)      ║  %-27d║\n" +
                        "║  Height (meters)  ║  %-27.2f║\n" +
                        "║  Club             ║  %-27s║\n" +
                        "║  Position         ║  %-27s║\n" +
                        "║  Number           ║  %-27d║\n" +
                        "║  Weekly Salary    ║  %-27.1f║\n" +
                        "╚═══════════════════╩═════════════════════════════╝\n",Color.YELLOW,name,Color.RESET,country,age,height,club,position,number,weeklySalary
        );
    }
}
