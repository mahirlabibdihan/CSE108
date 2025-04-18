package com.dihu.data;

import com.dihu.classes.Club;
import com.dihu.classes.Player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static final String INPUT_FILE_NAME = "src/com/dihu/data/players.txt";
    private static final String OUTPUT_FILE_NAME = "src/com/dihu/data/players.txt";
    private static List<Club> clubList;

    public static void writeToFile() throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Club c : clubList) {
            for (Player p : c.getPlayerList()) {
                bw.write(
                        p.getName() + ',' +
                                p.getCountry() + ',' +
                                p.getAge() + ',' +
                                p.getHeight() + ',' +
                                p.getClub() + ',' +
                                p.getPosition() + ',' +
                                p.getNumber() + ',' +
                                p.getWeeklySalary() + '\n'
                );
            }
        }
        bw.close();
    }

    public static void readFromFile() throws Exception {
        clubList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            addPlayerFromString(line);
        }
        br.close();
    }

    // Getting player information from every line of the input file
    public static void addPlayerFromString(String line) {
        String[] tokens = line.split(",");
        Player p = new Player(
                tokens[0],
                tokens[1],
                Integer.parseInt(tokens[2]),
                Double.parseDouble(tokens[3]),
                tokens[4],
                tokens[5],
                Integer.parseInt(tokens[6]),
                Double.parseDouble(tokens[7])
        );
        addPlayer(p);
    }

    public static void addPlayer(Player p) {
        // Searching the clublist for the club of the player
        for (int i = 0; i < clubList.size(); i++) {
            if (clubList.get(i).getName().equalsIgnoreCase(p.getClub())) {
                clubList.get(i).addPlayer(Player.copy(p));   // Add the player to the club
                return;
            }
        }

        // If the club is not already added in the list , add the club in the clublist
        Club c = new Club();
        c.setName(p.getClub());
        c.addPlayer(p); // Add the player to the club
        clubList.add(c);
    }

    public static Map<String, Integer> getCountryWisePlayerCount() {
        Map<String, Integer> count = new HashMap();     // Mapping of country and total players of that country
        for (Club c : clubList) {
            c.getCountryWisePlayerCount(count);
        }
        return count;
    }

    public static List<Player> searchPlayerBySalaryRange(double start, double end) {
        List<Player> searchedPlayers = new ArrayList();
        for (Club c : clubList) {
            c.searchPlayerBySalaryRange(start,end,searchedPlayers);
        }
        return searchedPlayers;
    }

    public static List<Player> searchPlayerByPosition(String position) {
        List<Player> searchedPlayers = new ArrayList();
        for (Club c : clubList) {
            c.searchPlayerByPosition(position,searchedPlayers);
        }
        return searchedPlayers;
    }

    public static List<Player> searchPlayerByClubAndCountry(String country, String club) {
        List<Player> searchedPlayers = new ArrayList();
        for (Club c : clubList) {
            if (c.getName().equalsIgnoreCase(club) || club.equalsIgnoreCase("ANY")) {
                c.searchPlayerByCountry(country,searchedPlayers);
            }
        }
        return searchedPlayers;
    }

    public static Player searchPlayerByName(String name) {
        for (Club c : clubList) {
            Player p=c.searchPlayerByName(name);
            if(p!=null){
                return p;
            }
        }
        return null;
    }

    public static Club searchClubByName(String name) {
        for (Club c : clubList) {
            if(c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
}
