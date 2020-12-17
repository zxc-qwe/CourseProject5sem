package controller;

import DTO.Branch;
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

import static service.UserService.addBranch;
import static service.UserService.addUser;

public class AddBranch {
    @FXML
    TextField name;
    @FXML
    TextField vvp_percent;
    @FXML
    TextField companiesNum;

    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void AddButton(ActionEvent event) throws IOException {
        String empty = "";
        if (empty.equals(name.getText()) || empty.equals(vvp_percent.getText()) || empty.equals(companiesNum.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Поля должны быть заполнены!");
            alert.showAndWait();
            //loginfield.clear();
            //passwordfield.clear();
        } else {
            addBranch(new Branch(name.getText(),vvp_percent.getText(),companiesNum.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешное выполнение!");
            alert.setHeaderText(null);
            alert.setContentText("Отрасль добавлена!");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminMenu.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
        }
    }
}
