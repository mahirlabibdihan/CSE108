package com.dihu.menu;

import com.dihu.classes.Player;
import com.dihu.data.Database;
import com.dihu.IO.Color;
import com.dihu.IO.Console;

public class AddPlayerMenu {
    public static void view(){
        Player p = getPlayerDetails();
        Database.addPlayer(p);      // Add player to the database
        Console.printSuccess("New Player Added to the database");
        System.out.println(p);      // Showing user the added player
        Console.pauseScanner();
    }

    public static Player getPlayerDetails() {
        Player p=new Player();
        System.out.println( "╔═════════════════════════════════════════════════╗" + "\n" +
                            "║              "+ Color.CYAN +"Main Menu > Add Player"+ Color.RESET +"             ║" + "\n" +
                            "╠═════════════════════════════════════════════════╣" );

        while(true)
        {
            System.out.println( "║          What is the name of the player?        ║"  );
            p.setName(Console.getPlayerName());
            if(Database.searchPlayerByName(p.getName())!=null) {
                Console.printError("Player with same name already exists");
            }
            else {
                break;
            }
        }

        System.out.println( "║        What is the country of the player?       ║");
        p.setCountry(Console.getCountryName());
        System.out.println( "║          What is the age of the player?         ║");
        p.setAge(Console.getPlayerAge());
        System.out.println( "║         What is the height of the player?       ║");
        p.setHeight(Console.getPlayerHeight());

        while(true) {
            System.out.println( "║          What is the club of the player?        ║");
            p.setClub(Console.getClubName());
            try{
                if(Database.searchClubByName(p.getClub()).isFull()){
                    Console.printError("No more players can be added to this club");
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                break;
            }
        }
        while(true) {
            System.out.println( "║        What is the position of the player?      ║");
            p.setPosition(Console.getPlayerPosition());
            if(
                    p.getPosition().equalsIgnoreCase("Goalkeeper")||
                    p.getPosition().equalsIgnoreCase("Defender")||
                    p.getPosition().equalsIgnoreCase("Midfielder")||
                    p.getPosition().equalsIgnoreCase("Forward")) {
                    break;
            }
            else{
                Console.printError("Position must be Goalkeeper/Defender/Midfielder/Forward");
            }
        }

        while(true)
        {
            System.out.println( "║         What is the number of the player?       ║");
            p.setNumber(Console.getPlayerNumber());

            try{
                if(Database.searchClubByName(p.getClub()).searchPlayerByNumber(p.getNumber())==null) {
                    Console.printError("Player with same number already exists in "+p.getClub());
                }
                else{
                    break;
                }
            }
            catch(Exception e){
                break;
            }

        }

        System.out.println( "║     What is the weekly salary of the player?    ║");
        p.setWeeklySalary(Console.getPlayerWeeklySalary());
        System.out.println( "╚═════════════════════════════════════════════════╝"+"\n");
        return p;
    }
}
