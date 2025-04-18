package com.dihu.menu;

import com.dihu.classes.Player;
import com.dihu.data.Database;
import com.dihu.IO.Color;
import com.dihu.IO.Console;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SearchClubsMenu {
    // Options
    private final static int PLAYERS_WITH_MAX_SALARY = 1;
    private final static int PLAYERS_WITH_MAX_AGE = 2;
    private final static int PLAYERS_WITH_MAX_HEIGHT = 3;
    private final static int TOTAL_YEARLY_SALARY = 4;
    private final static int BACK_TO_MAIN_MENU = 5;

    public static void view() {
        boolean loop = true;
        while (loop) {
            loop = showOption();
        }
    }

    public static boolean showOption() {
        System.out.println(
                "╔═════════════════════════════════════════════════╗" + "\n" +
                        "║             " + Color.CYAN + "Main Menu > Search Clubs" + Color.RESET + "            ║"
                        + "\n" +
                        "╠═════════════════════════════════════════════════╣" + "\n" +
                        "║     What information do you want of a club?     ║" + "\n" +
                        "║ (1) Player(s) with the maximum salary of a club ║" + "\n" +
                        "║ (2) Player(s) with the maximum age of a club    ║" + "\n" +
                        "║ (3) Player(s) with the maximum height of a club ║" + "\n" +
                        "║ (4) Total yearly salary of a club               ║" + "\n" +
                        "║ (5) Back to Main Menu                           ║" + "\n" +
                        "╚═════════════════════════════════════════════════╝");

        int option = Console.getOption(PLAYERS_WITH_MAX_SALARY, BACK_TO_MAIN_MENU);
        switch (option) {
            case PLAYERS_WITH_MAX_SALARY:
                getMaxSalaryPlayer();
                Console.pauseScanner();
                return true;

            case PLAYERS_WITH_MAX_AGE:
                getMaxAgePlayer();
                Console.pauseScanner();
                return true;

            case PLAYERS_WITH_MAX_HEIGHT:
                getMaxHeightPlayer();
                Console.pauseScanner();
                return true;

            case TOTAL_YEARLY_SALARY:
                getTotalYearlySalary();
                Console.pauseScanner();
                return true;

            case BACK_TO_MAIN_MENU:
                return false;

            default:
                Console.printError("Select between " + PLAYERS_WITH_MAX_SALARY + " to " + BACK_TO_MAIN_MENU);
                return true;
        }
    }

    public static void getMaxSalaryPlayer() {
        System.out.println("╔═════════════════════════════════════════════════╗" + "\n" +
                "║      " + Color.CYAN + "Main Menu > Search Clubs > Max Salary" + Color.RESET + "      ║" + "\n" +
                "╠═════════════════════════════════════════════════╣" + "\n" +
                "║          What is the name of the club?          ║");
        String club = Console.getClubName();
        System.out.println("╚═════════════════════════════════════════════════╝" + "\n");
        try {
            List<Player> searchedPlayers = Database.searchClubByName(club).getMaxSalaryPlayer();
            Console.printSuccess("Club Found");
            System.out.printf("╔═════════════════════════════════════════════════╗" + "\n" +
                    "║  " + Color.BLUE + "Players with max salary of %-20s" + Color.RESET + "║" + "\n" +
                    "╚═════════════════════════════════════════════════╝" + "\n", club);
            Console.printPlayers(searchedPlayers);
        } catch (Exception e) {
            Console.printError("No such club with this name");
        }
    }

    public static void getMaxAgePlayer() {
        System.out.println("╔═════════════════════════════════════════════════╗" + "\n" +
                "║        " + Color.CYAN + "Main Menu > Search Clubs > Max Age" + Color.RESET + "       ║" + "\n" +
                "╠═════════════════════════════════════════════════╣" + "\n" +
                "║          What is the name of the club?          ║");
        String club = Console.getClubName();
        System.out.println("╚═════════════════════════════════════════════════╝" + "\n");
        try {
            List<Player> searchedPlayers = Database.searchClubByName(club).getMaxAgePlayer();
            Console.printSuccess("Club Found");
            System.out.printf("╔═════════════════════════════════════════════════╗" + "\n" +
                    "║  " + Color.BLUE + "Players with max age of %-20s" + Color.RESET + "   ║" + "\n" +
                    "╚═════════════════════════════════════════════════╝" + "\n", club);
            Console.printPlayers(searchedPlayers);

        } catch (Exception e) {
            Console.printError("No such club with this name");
        }
    }

    public static void getMaxHeightPlayer() {
        System.out.println("╔═════════════════════════════════════════════════╗" + "\n" +
                "║      " + Color.CYAN + "Main Menu > Search Clubs > Max Height" + Color.RESET + "      ║" + "\n" +
                "╠═════════════════════════════════════════════════╣" + "\n" +
                "║          What is the name of the club?          ║");
        String club = Console.getClubName();
        System.out.println("╚═════════════════════════════════════════════════╝" + "\n");
        try {
            List<Player> searchedPlayers = Database.searchClubByName(club).getMaxHeightPlayer();
            Console.printSuccess("Club Found");
            System.out.printf("╔═════════════════════════════════════════════════╗" + "\n" +
                    "║  " + Color.BLUE + "Players with max height of %-20s" + Color.RESET + "║" + "\n" +
                    "╚═════════════════════════════════════════════════╝" + "\n", club);
            Console.printPlayers(searchedPlayers);
        } catch (Exception e) {
            Console.printError("No such club with this name");
        }
    }

    public static void getTotalYearlySalary() {
        System.out.println("╔═════════════════════════════════════════════════╗" + "\n" +
                "║     " + Color.CYAN + "Main Menu > Search Clubs > Yearly Salary" + Color.RESET + "    ║" + "\n" +
                "╠═════════════════════════════════════════════════╣" + "\n" +
                "║          What is the name of the club?          ║");
        String club = Console.getClubName();
        System.out.println("╚═════════════════════════════════════════════════╝" + "\n");

        try {
            double total = Database.searchClubByName(club).getTotalYearlySalary();
            Console.printSuccess("Club Found");
            System.out.printf("╔═════════════════════════════════════════════════╗" + "\n" +
                    "║  %sTotal Yearly Salary of %-24s%s║" + "\n" +
                    "╠═════════════════════════════════════════════════╣" + "\n" +
                    "║  %-20f                           ║\n" +
                    "╚═════════════════════════════════════════════════╝\n", Color.BLUE, club, Color.RESET, total);
        } catch (Exception e) {
            Console.printError("No such club with this name");
        }
    }

}
