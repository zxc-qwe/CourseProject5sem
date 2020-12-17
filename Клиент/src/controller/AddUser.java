package controller;

import DTO.User;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

import static service.UserService.addUser;

public class AddUser {

    @FXML
    TextField loginfield;
    @FXML
    TextField passwordfield;
    @FXML
    TextField namefield;
    @FXML
    TextField positionfield;

    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ManageUsers.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void AddButton(ActionEvent event) throws IOException {
        String empty = "";
        if (empty.equals(loginfield.getText()) || empty.equals(passwordfield.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Поля должны быть заполнены!");
            alert.showAndWait();
            //loginfield.clear();
            //passwordfield.clear();
        } else {
            addUser(new User(loginfield.getText(),passwordfield.getText(),"Пользователь", namefield.getText(), positionfield.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешное выполнение!");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь добавлен!");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ManageUsers.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
        }
    }
}
