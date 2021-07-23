package com.dihu;

public class Player {
    private int number;
    private String name;
    private double salary;

	// you are not allowed to write any other constructor
    public Player(int number, String name) {
        setNumber(number);
        setName(name);
    }
	
	// add your code here

    // Getters
    public double getSalary() { return salary; }
    public String getName() { return name; }
    public int getNumber() { return number; }

    // Setters
    public void setSalary(double salary) { this.salary=salary; }
    public void setName(String name) { this.name = name; }
    public void setNumber(int number) { this.number= number; }

}
