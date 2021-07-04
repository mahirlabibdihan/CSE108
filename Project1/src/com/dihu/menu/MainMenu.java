package com.dihu.menu;

import com.dihu.IO.Color;
import com.dihu.IO.Console;

public class MainMenu {
    // Options
    private final static int SEARCH_PLAYERS = 1;
    private final static int SEARCH_CLUBS = 2;
    private final static int ADD_PLAYER = 3;
    private final static int EXIT_SYSTEM = 4;

    public static void view(){
        boolean loop = true;
        while(loop){
            loop =  showOption();
        }
    }

    public static boolean showOption()
    {
        System.out.println( "╔═════════════════════════════════════════════════╗" + "\n" +
                            "║                    "+ Color.CYAN +"Main Menu"+ Color.RESET +"                    ║" + "\n" +
                            "╠═════════════════════════════════════════════════╣" + "\n" +
                            "║                Choose what to do:               ║" + "\n" +
                            "║                (1) Search Players               ║" + "\n" +
                            "║                (2) Search Clubs                 ║" + "\n" +
                            "║                (3) Add Player                   ║" + "\n" +
                            "║                (4) Exit System                  ║" + "\n" +
                            "╚═════════════════════════════════════════════════╝"
        );

        int option = Console.getOption(SEARCH_PLAYERS,EXIT_SYSTEM);
        switch(option)
        {
            case SEARCH_PLAYERS:
                SearchPlayersMenu.view();
                return true;

            case SEARCH_CLUBS:
                SearchClubsMenu.view();
                return true;

            case ADD_PLAYER:
                AddPlayerMenu.view();
                return true;

            case EXIT_SYSTEM:
                System.out.println(Color.PURPLE+"   ✋Thank you for staying with us"+Color.RESET);
                return false;

            default:
                Console.printError("Select between "+SEARCH_PLAYERS+" to "+EXIT_SYSTEM);
                return true;
        }
    }
}
