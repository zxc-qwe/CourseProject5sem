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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static service.UserService.*;

public class Authorization implements Initializable {

    @FXML
    TextField loginfield;
    @FXML
    TextField passwordfield;
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

    public void AuthButton(ActionEvent event) throws IOException{
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
            if (role.getValue() == "Администратор") {
                String answer = AuthAdmin(loginfield.getText(), passwordfield.getText());
                if ("Администратор".equals(answer)) {
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminMenu.fxml"));
                    Main.primaryStage.setScene(new Scene(root));
                    Main.primaryStage.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Неверно введены логин или пароль!");
                    alert.showAndWait();
                }
            }
            else if (role.getValue() == "Пользователь") {
                String answer = AuthUser(loginfield.getText(), passwordfield.getText());
                User.log = loginfield.getText();
                User.pass = passwordfield.getText();
                if ("Пользователь".equals(answer)) {
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/UserMenu.fxml"));
                    Main.primaryStage.setScene(new Scene(root));
                    Main.primaryStage.show();
                } else {
                    if ("Блокировка".equals(answer)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Пользователь заблокирован.");
                    alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Неверно введены логин или пароль!");
                        alert.showAndWait();
                    }
                }
            }
        }
    }





}
