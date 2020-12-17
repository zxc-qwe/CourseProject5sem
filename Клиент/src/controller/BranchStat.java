package controller;

import DTO.Branch;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static service.UserService.*;
import static service.UserService.BranchStatCompNum;

public class BranchStat implements Initializable {

    @FXML
    ComboBox branch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            ArrayList<String> name = GetBranches();
            ObservableList<String> branches = FXCollections.observableArrayList(name);
            branch.setItems(branches);
            ToggleGroup group = new ToggleGroup();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void NextButton(ActionEvent event) throws IOException {
        BranchStatAsk((String)branch.getValue());
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/BranchStatShow.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
