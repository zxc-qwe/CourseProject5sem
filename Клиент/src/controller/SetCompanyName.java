package controller;

import DTO.Company;
import DTO.Ratio;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static DTO.Ratio.*;
import static DTO.User.*;
import static service.UserService.*;

public class SetCompanyName implements Initializable {

    @FXML
    TextField companyName;
    @FXML
    TextField ownership;
    @FXML
    TextField workersNum;
    @FXML
    TextField market_percent;
    @FXML
    ComboBox branch;

    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public void ReadyButton (ActionEvent event) throws IOException {
        String empty = "";
        if (empty.equals(companyName.getText()) || empty.equals(ownership.getText()) || empty.equals(workersNum.getText())
        || empty.equals(market_percent.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Поля должны быть заполнены!");
            alert.showAndWait();
        }
        else {
            companyName.getText();
            ownership.getText();
            workersNum.getText();
            market_percent.getText();
            Ratio ratio = new Ratio(rat1s, rat2s, rat3s, rat4s, rat5s, rat6s, rat7s, rat8s);
            Company company = new Company(companyName.getText(), ownership.getText(), workersNum.getText(),
            market_percent.getText(), (String)branch.getValue());
            User user = new User(log, pass, "Пользователь");
            addRatio(ratio, company, user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Информация о компании успешно добавлена.");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/UserMenu.fxml"));
            Main.primaryStage.setScene(new Scene(root));
            Main.primaryStage.show();
        }
    }
}
