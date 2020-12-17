package controller;

import DTO.Branch;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

import static service.UserService.setBranch;

public class SetBranch {

    @FXML
    TextField name;
    @FXML
    TextField vvp_percent;
    @FXML
    TextField companiesNum;

    public void AddButton (ActionEvent event) throws IOException {
        String empty = "";
        if (empty.equals(name.getText()) || empty.equals(vvp_percent.getText()) || empty.equals(companiesNum.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Поля должны быть заполнены!");
            alert.showAndWait();
        }
        else {
            if (Double.parseDouble(vvp_percent.getText()) > 35 || Double.parseDouble(vvp_percent.getText()) <= 0 || Integer.parseInt(companiesNum.getText()) <= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            } else {
                Branch branch = new Branch(name.getText(), vvp_percent.getText(), companiesNum.getText());
                setBranch(branch);
                name.getText();
                vvp_percent.getText();
                companiesNum.getText();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Информация о компании успешно добавлена.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminMenu.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }

    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
