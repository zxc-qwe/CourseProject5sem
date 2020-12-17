package controller;

import DTO.Ratio;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import static service.UserService.GetBranches;

public class RatioCount {


    @FXML
    TextField field1;
    @FXML
    TextField field2;


    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UserMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void NextButton (ActionEvent event) throws IOException {
        String empty = "";
        if (empty.equals(field1.getText()) || empty.equals(field2.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Поля должны быть заполнены!");
            alert.showAndWait();
        }
        else {
            if ((Integer.parseInt(field1.getText()) < 0) || (Integer.parseInt(field2.getText()) <= 0)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            } else {
                int kapital = Integer.parseInt(field1.getText());
                int aktiv = Integer.parseInt(field2.getText());
                double koef1 = ((double) kapital) / aktiv;
                DecimalFormat df = new DecimalFormat("###.##");
                Ratio.rat1s = (String) df.format(koef1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Коэффициент автономии равен " + df.format(koef1) + "\n\nОптимальным значением показателя " +
                        "считается значение \nкоэффициента автономии больше 0,5 но не более 0,7.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/RatioCount2.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }
}
