package com.dihu.menu;

import com.dihu.classes.Player;
import com.dihu.data.Database;
import com.dihu.IO.Color;
import com.dihu.IO.Console;

import java.util.List;
import java.util.Map;

public class SearchPlayersMenu {
    // Options
    private final static int BY_PLAYER_NAME = 1;
    private final static int BY_CLUB_AND_COUNTRY = 2;
    private final static int BY_POSITION = 3;
    private final static int BY_SALARY_RANGE = 4;
    private final static int COUNTRY_WISE_PLAYER_COUNT = 5;
    private final static int BACK_TO_MAIN_MENU = 6;

    public static void view() {
        boolean loop = true;
        while (loop) {
            loop = showOption();
        }
    }

    public static boolean showOption() {
        System.out.println( "╔═════════════════════════════════════════════════╗" + "\n" +
                            "║           "+ Color.CYAN +"Main Menu > Search Players"+ Color.RESET +"            ║" + "\n" +
                            "╠═════════════════════════════════════════════════╣" + "\n" +
                            "║    How would you like to search the player?     ║" + "\n" +
                            "║        (1) By Player Name                       ║" +"\n" +
                            "║        (2) By Club and CountryName              ║" +"\n" +
                            "║        (3) By Position                          ║" +"\n" +
                            "║        (4) By Salary Range                      ║" +"\n" +
                            "║        (5) Country-wise player count            ║" +"\n" +
                            "║        (6) Back to Main Menu                    ║" +"\n" +
                            "╚═════════════════════════════════════════════════╝"
        );

        int option = Console.getOption(BY_PLAYER_NAME, BACK_TO_MAIN_MENU);
        switch (option) {
            case BY_PLAYER_NAME:
                getPlayerName();
                Console.pauseScanner();
                return true;

            case BY_CLUB_AND_COUNTRY:
                getClubAndCountry();
                Console.pauseScanner();
                return true;

            case BY_POSITION:
                getPlayerPosition();
                Console.pauseScanner();
                return true;

            case BY_SALARY_RANGE:
                getSalaryRange();
                Console.pauseScanner();
                return true;

            case COUNTRY_WISE_PLAYER_COUNT:
                showCountryWisePlayerCount();
                Console.pauseScanner();
                return true;

            case BACK_TO_MAIN_MENU:
                return false;

            default:
                Console.printError("Select between " + BY_PLAYER_NAME + " to " + BACK_TO_MAIN_MENU);
                return true;
        }
    }

    public static void getSalaryRange()
    {
        System.out.println( "╔═════════════════════════════════════════════════╗"+ "\n" +
                            "║    "+ Color.CYAN +"Main Menu > Search Players > Salary Range"+ Color.RESET +"    ║" + "\n" +
                            "╠═════════════════════════════════════════════════╣");
        double start = -1, end = -1;

        System.out.println( "║    What is the starting range of the salary?    ║" );
        while (start == -1) {
            System.out.print("Enter starting range: ");
            start = Console.getPositiveDouble();
        }

        System.out.println( "║     What is the ending range of the salary?     ║" );
        while (end == -1) {
            System.out.print("Enter ending range: ");
            end = Console.getPositiveDouble();
            if(end<start){
                Console.printError("Ending range must be greater than or equal to starting range");
                end=-1;
            }
        }

        System.out.println( "╚═════════════════════════════════════════════════╝"+"\n");

        List<Player> searchedPlayers = Database.searchPlayerBySalaryRange(start, end);

        if(searchedPlayers.size()>0) {
            Console.printSuccess("Player Found");
            System.out.printf(  "╔═══════════════════╦═════════════════════════════╗" + "\n" +
                                "║  "+Color.BLUE+"Salary"+Color.RESET+"           ║ %12.2f<->%-12.2f ║" + "\n" +
                                "╚═══════════════════╩═════════════════════════════╝" + "\n",start,end);
            Console.printPlayers(searchedPlayers);
        }
        else Console.printError("No such player with this salary range");
    }

    public static void getPlayerPosition()
    {
        System.out.println( "╔═════════════════════════════════════════════════╗"+ "\n" +
                            "║      "+ Color.CYAN +"Main Menu > Search Players > Position"+ Color.RESET +"      ║" + "\n" +
                            "╠═════════════════════════════════════════════════╣" + "\n" +
                            "║       What is the position of the player?       ║" );

        String position = Console.getPlayerPosition();
        System.out.println( "╚═════════════════════════════════════════════════╝"+"\n");
        List<Player> searchedPlayers = Database.searchPlayerByPosition(position);
        if(searchedPlayers.size()>0) {
            Console.printSuccess("Player Found");
            System.out.printf(  "╔═══════════════════╦═════════════════════════════╗" + "\n" +
                                "║  "+Color.BLUE+"Position"+Color.RESET+"         ║  %-27s║" + "\n" +
                                "╚═══════════════════╩═════════════════════════════╝" + "\n",position);
            Console.printPlayers(searchedPlayers);
        }
        else Console.printError("No such player with this position");
    }

    public static void getPlayerName()
    {
        System.out.println( "╔═════════════════════════════════════════════════╗"  + "\n" +
                            "║        "+ Color.CYAN +"Main Menu > Search Players > Name"+ Color.RESET +"        ║" + "\n" +
                            "╠═════════════════════════════════════════════════╣" + "\n" +
                            "║         What is the name of the player?         ║" );

        String name = Console.getPlayerName();
        System.out.println( "╚═════════════════════════════════════════════════╝"+"\n");
        Player p = Database.searchPlayerByName(name);

        if(p!=null) {
            Console.printSuccess("Player Found");
            System.out.printf(  "╔═══════════════════╦═════════════════════════════╗" + "\n" +
                                "║  "+Color.BLUE+"Name"+Color.RESET+"             ║  %-27s║" + "\n" +
                                "╚═══════════════════╩═════════════════════════════╝" + "\n",name);
            System.out.println(p);
        }
        else {
            Console.printError("No such player with this name");
        }
    }

    public static void getClubAndCountry()
    {
        System.out.println( "╔═════════════════════════════════════════════════╗" + "\n" +
                            "║   "+ Color.CYAN +"Main Menu > Search Players > Country & Club"+ Color.RESET +"   ║" + "\n" +
                            "╠═════════════════════════════════════════════════╣" + "\n" +
                            "║          What is the name of the country?       ║"  );
        String country = Console.getCountryName();
        System.out.println( "║          What is the name of the club?          ║"+"\n"+
                            "║  (ANY for any player under the above country)   ║");
        String club = Console.getClubName();
        System.out.println( "╚═════════════════════════════════════════════════╝"+"\n");
        List<Player> searchedPlayers = Database.searchPlayerByClubAndCountry(country,club);

        if(searchedPlayers.size()>0) {
            Console.printSuccess("Player Found");
            System.out.printf(  "╔═══════════════════╦═════════════════════════════╗" + "\n" +
                                "║  "+Color.BLUE+"Country"+Color.RESET+"          ║  %-27s║" + "\n" +
                                "╠═══════════════════╬═════════════════════════════╣" + "\n"+
                                "║  "+Color.BLUE+"Club"+Color.RESET+"             ║  %-27s║" + "\n" +
                                "╚═══════════════════╩═════════════════════════════╝" + "\n",country,club);
            Console.printPlayers(searchedPlayers);
        }
        else Console.printError("No such player with this country and club");
    }

    public static void showCountryWisePlayerCount() {
        Map<String,Integer> count = Database.getCountryWisePlayerCount();
        if(count.size()>0) {
            // Printing header
            System.out.println( "╔═══════════════════════════╦═════════════════════╗" + "\n" +
                                "║  " + Color.BLUE + "Country               " + Color.RESET + "   ║    " + Color.BLUE + "Total Players" + Color.RESET + "    ║" + "\n" +
                                "╠═══════════════════════════╬═════════════════════╣"
                        );

            // Printing country wise player count
            for (Map.Entry<String, Integer> m : count.entrySet()) {
                System.out.printf("║  %-25s║         %2d          ║\n", m.getKey(), m.getValue());
            }
            System.out.println( "╚═══════════════════════════╩═════════════════════╝\n");
        }
        else Console.printError("No player in the database");
    }

}
