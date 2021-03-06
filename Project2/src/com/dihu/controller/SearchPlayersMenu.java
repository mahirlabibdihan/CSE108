package com.dihu.controller;

import com.dihu.IO.Console;
import com.dihu.classes.Club;
import com.dihu.classes.Player;
import com.dihu.client.Client;
import com.dihu.controller.Controller;
import com.dihu.controller.PlayerDetails;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchPlayersMenu extends Controller {
    @FXML
    public ComboBox<String> SEARCH_OPTION;
    @FXML
    public TextField SEARCH_FIELD;

    private String[] options = {"Name","Country","Position","Salary"};

    private int i;
    @FXML
    private AnchorPane playerListPane;

    @FXML
    private HBox filterInput;

    private SpinnerValueFactory<String> filterValueFactory;
    @FXML
    private Spinner filterSpinner;
    private List<Player> playerList;
    private Map<Button,Player> playerMap;
    private List<Button> buttons;
    public SearchPlayersMenu(){
        playerMap = new HashMap<>();
        buttons = new ArrayList<Button>();
        SEARCH_OPTION = new ComboBox<>();
        i=0;
    }

    @FXML
    public void back(MouseEvent mouseEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scene/MainMenu.fxml"));
        Parent root = loader.load();

        Controller controller = (Controller)loader.getController();
        controller.setClient(client);

        ((Node)mouseEvent.getSource()).getScene().setRoot(root);
    }


    public void printPlayers(List<Player> searchedPlayers){
        float height= 45;
        playerListPane.setPrefHeight(searchedPlayers.size()*(height+20));
        for(int i=0;i<searchedPlayers.size();i++){
            Button b = new Button(searchedPlayers.get(i).getName());

            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scene/PlayerDetails.fxml"));
                        Player p = client.getClub().searchPlayerByName(((Button)e.getSource()).getText());
                        Player.player = p;
                        Parent root = loader.load();
                        PlayerDetails controller = loader.getController();
                        controller.setClient(client);
                        ((Button)e.getSource()).getScene().setRoot(root);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });


            AnchorPane.setLeftAnchor(b, 60.0);
            AnchorPane.setTopAnchor(b, i*(height+20.0));
            b.setPrefHeight(height);
            b.setPrefWidth(280);
            buttons.add(b);
            b.setId("player-button");
            playerListPane.getChildren().add(b);
        }
    }
    @FXML
    public void initialize() {
        filterValueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(
                FXCollections.observableArrayList(
                                "",
                                "NAME",
                                "COUNTRY",
                                "POSITION",
                                "SALARY",
                                "MAX SALARY",
                                "MAX AGE",
                                "MAX HEIGHT"
                        )
        );
        filterSpinner.setValueFactory(filterValueFactory);
        filterSpinner.valueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observableValue, Object o, Object t1) {
                String currentFilter = (String) filterSpinner.getValue();
                if(currentFilter.equals("NAME")){
                    filterInput.getChildren().clear();
                    TextField nameInput = new TextField();
                    nameInput.setPromptText("Enter player's name");
                    nameInput.setId("nameInputField");
                    filterInput.getChildren().add(nameInput);
                    filterInput.setAlignment(Pos.CENTER);
                }else if(currentFilter.equals("COUNTRY")){
                    filterInput.getChildren().clear();
                    TextField nameInput = new TextField();
                    nameInput.setPromptText("Enter country's name");
                    nameInput.setId("nameInputField");
                    filterInput.getChildren().add(nameInput);
                    filterInput.setAlignment(Pos.CENTER);
                }else if(currentFilter.equals("POSITION")){
                    filterInput.getChildren().clear();
                    TextField nameInput = new TextField();
                    nameInput.setPromptText("Enter player's position");
                    nameInput.setId("nameInputField");
                    filterInput.getChildren().add(nameInput);
                    filterInput.setAlignment(Pos.CENTER);
                }else if(currentFilter.equals("SALARY")){
                    filterInput.getChildren().clear();
                    TextField nameInput = new TextField();
                    nameInput.setPromptText("Enter salary range");
                    nameInput.setId("nameInputField");
                    filterInput.getChildren().add(nameInput);
                    filterInput.setAlignment(Pos.CENTER);
                }else{
                    filterInput.getChildren().clear();
                }
            }
        });
    }

    public void reset(ActionEvent actionEvent){
        filterValueFactory.setValue("");
    }
    public void search(ActionEvent actionEvent) {
        playerListPane.getChildren().clear();
        buttons.clear();
        playerMap.clear();
        SEARCH_FIELD = (TextField)filterInput.lookup("#nameInputField");

        if(SEARCH_FIELD!=null){
            System.out.println("Found");
            System.out.println(SEARCH_FIELD.getText());
        }
        String option = (String) filterSpinner.getValue();
        System.out.println(option);
        if(option.equals("NAME")) {
            System.out.println("Pressed");
            String name = SEARCH_FIELD.getText();
            Player p = client.getClub().searchPlayerByName(name);
            List<Player> searchedPlayers =  new ArrayList<>();

            if(p!=null) {
                searchedPlayers.add(p);
                Console.printSuccess("Player Found");
                printPlayers(searchedPlayers);
            }
            else Console.printError("No such player with this position");
        }
        else if(option.equals("COUNTRY")){
            String country = SEARCH_FIELD.getText();
            List<Player> searchedPlayers = client.getClub().searchPlayerByCountry(country);
            if(searchedPlayers.size()>0) {
                Console.printSuccess("Player Found");
                printPlayers(searchedPlayers);
            }
            else Console.printError("No such player with this position");
        }
        else if(option.equals("POSITION")){

            System.out.println("Pressed");
            String position = SEARCH_FIELD.getText();
            List<Player> searchedPlayers =  client.getClub().searchPlayerByPosition(position);
            if(searchedPlayers.size()>0) {
                Console.printSuccess("Player Found");
                printPlayers(searchedPlayers);
            }
            else Console.printError("No such player with this position");
        }
        else if(option.equals("SALARY")){
            String[] salaryRange = SEARCH_FIELD.getText().split("-");
            try{
                double start = Double.parseDouble(salaryRange[0]);
                double end = Double.parseDouble(salaryRange[1]);
                List<Player> searchedPlayers = client.getClub().searchPlayerBySalaryRange(start, end);

                if(searchedPlayers.size()>0) {
                    printPlayers(searchedPlayers);
                }
                else Console.printError("No such player with this salary range");
            }catch(Exception e){

            }
        }
        else if(option.equals("MAX SALARY")){
            try{
                List<Player> searchedPlayers = client.getClub().getMaxSalaryPlayer();
                Console.printSuccess("Club Found");
                printPlayers(searchedPlayers);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else if(option.equals("MAX AGE")){
            try{
                List<Player> searchedPlayers = client.getClub().getMaxAgePlayer();
                Console.printSuccess("Club Found");
                printPlayers(searchedPlayers);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else if(option.equals("MAX HEIGHT")){
            try{
                List<Player> searchedPlayers = client.getClub().getMaxHeightPlayer();
                Console.printSuccess("Club Found");
                printPlayers(searchedPlayers);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }else{
            List<Player> searchedPlayers = client.getClub().getPlayerList();
            if(searchedPlayers.size()>0) {
                Console.printSuccess("Player Found");
                printPlayers(searchedPlayers);
            }
            else Console.printError("No player in the club");
        }
    }
    @Override
    public void setClient(Client client) {
        this.client = client;
        Club c = client.getClub();
        try{
            playerList= c.getPlayerList();
            printPlayers(playerList);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
