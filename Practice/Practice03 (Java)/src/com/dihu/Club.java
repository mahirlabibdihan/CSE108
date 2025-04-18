package com.dihu;

public class Club {
    private int id;
    private String name;
    private Player[] players;

    // add your code here
    private int playerCount;

	// you are not allowed to write any other constructor
    public Club() {
        playerCount=0;
        this.players = new Player[11];
    }

	public double getSalary() {
        double total = 0;
        for (int i = 0; i < playerCount; i++) {
            total += players[i].getSalary();
        }
        return total;
    }
    public void setId(int id) { this.id=id; }

	// add your code here
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }

    // Setters
    public void setName(String name) { this.name = name; }

    public void addPlayer(Player player)
    {
        players[playerCount]=player;
        playerCount++;
    }
    public Player getMaxSalaryPlayer()
    {
        int max=0;
        for(int i=0;i<playerCount;i++) {
            if(players[i].getSalary()>players[max].getSalary()) {
                max=i;
            }
        }
        return players[max];
    }
}