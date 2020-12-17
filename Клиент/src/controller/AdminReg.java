package controller;

import DTO.Admin;
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

import static service.UserService.addAdmin;

public class AdminReg {
    @FXML
    TextField loginfield;
    @FXML
    TextField passwordfield;



    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Registration.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void SaveButton(ActionEvent event) throws IOException {
        String empty = "";
        if (empty.equals(loginfield.getText()) || empty.equals(passwordfield.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Поля должны быть заполнены!");
            alert.showAndWait();
            //loginfield.clear();
            //passwordfield.clear();
        }
        else {
            addAdmin(new Admin(loginfield.getText(), passwordfield.getText(), "Администратор"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешное выполнение!");
            alert.setHeaderText(null);
            alert.setContentText("Администратор добавлен!");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/Main.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
        }
    }
}
