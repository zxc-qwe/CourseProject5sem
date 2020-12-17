package controller;

import DTO.Admin;
import DTO.User;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static service.UserService.addAdmin;
import static service.UserService.addUser;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class Registration implements Initializable {
    @FXML
    ComboBox role;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> positions = FXCollections.observableArrayList("Администратор", "Пользователь");
        role.setItems(positions);
        ToggleGroup group = new ToggleGroup();

    }


    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Main.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void SaveButton(ActionEvent event) throws IOException {
        if (role.getValue() == "Администратор") {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminReg.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
        }
        else if (role.getValue() == "Пользователь") {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/UserReg.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
        }
    }

}
