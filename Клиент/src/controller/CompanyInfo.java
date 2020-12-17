package controller;

import DTO.Company;
import DTO.Ratio;
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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static DTO.Company.comp_name;

import static service.UserService.*;

public class CompanyInfo implements Initializable {

        @FXML
        TextField companyName;

        public void initialize(URL url, ResourceBundle resourceBundle) {
            ObservableList<String> positions = FXCollections.observableArrayList("Компания");
            ToggleGroup group = new ToggleGroup();
        }

        public void NextButton (ActionEvent event) throws IOException {
            String empty = "";
            if (empty.equals(companyName.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Поля должны быть заполнены!");
                alert.showAndWait();
            }
            else {
                infoCompany(companyName.getText());
                comp_name = companyName.getText();
                Company info = InfoCompanyRecv();
                Ratio ratio = showRatioCompany();
                if (info.getCompanyName().equals("Error")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Такой компании не существует.");
                    alert.showAndWait();
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/CompanyInfo.fxml"));
                    Main.primaryStage.setScene(new Scene(root));
                    Main.primaryStage.show();
                } else {
                    infoCompany(companyName.getText());
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/ShowCompanyInfo.fxml"));
                    Main.primaryStage.setScene(new Scene(root));
                    Main.primaryStage.show();
                }
            }
        }

    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UserMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
