package com.dihu.controller;

import com.dihu.classes.Club;
import com.dihu.classes.Player;
import com.dihu.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionPlayerList extends Controller{
    private List<Player> playerList;
    @FXML
    private AnchorPane playerListPane;
    private Map<String,Player> buttons;

    public AuctionPlayerList() {

        buttons = new HashMap<>();
    }

    public void printPlayers(List<Player> searchedPlayers){
        float height= 45;


        int i=0;
        for(Player p:searchedPlayers){
            if(p.getClub().equals(client.getClub().getName())) continue;

            Button b = new Button(p.getName());
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    try {
                        Player p = buttons.get(((Button)e.getSource()).getId());
                        client.getNetworkUtil().write(new Pair<>(client.getClub().getName(),p.getName()));
                        p.setClub(client.getClub().getName());
                        client.getClub().addPlayer(p);
                        System.out.println("Send");
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scene/MainMenu.fxml"));
                        Parent root = loader.load();
                        Controller controller = loader.getController();
                        controller.setClient(client);
                        client.getScene().setRoot(root);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });


            b.setPrefHeight(height);
            b.setPrefWidth(280);
            b.setId("player_button"+i);
            buttons.put(b.getId(),searchedPlayers.get(i));

            ImageView country = new ImageView();
            country.setImage(new Image(getClass().getResource("../images/Player/"+searchedPlayers.get(i).getName()+".png").toExternalForm()));
            country.setFitHeight(50);
            country.setPreserveRatio(true);

            //playerListPane.getChildren().add(b);
            HBox row = new HBox(country,b);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setSpacing(5);
            AnchorPane.setLeftAnchor(row, 5.0);
            AnchorPane.setTopAnchor(row, i*(height+20.0));
            playerListPane.getChildren().add(row);
            i++;
            //playerListPane.getChildren().add(country);
        }
        playerListPane.setPrefHeight(i*(height+20)+20);
    }

    @FXML
    public void back(MouseEvent mouseEvent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scene/AddPlayer.fxml"));
        Parent root = loader.load();

        Controller controller = (Controller)loader.getController();
        controller.setClient(client);

        ((Node)mouseEvent.getSource()).getScene().setRoot(root);
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
        client.setFileName("AuctionPlayerList.fxml");
        try{
            playerList= client.getOnSell();
            printPlayers(playerList);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
