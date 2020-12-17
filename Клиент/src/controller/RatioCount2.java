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

public class RatioCount2 {

    @FXML
    TextField field1;
    @FXML
    TextField field2;
    @FXML
    TextField field3;
    @FXML
    TextField field4;
    @FXML
    TextField field5;
    @FXML
    TextField field6;



    public void NextButton (ActionEvent event) throws IOException {
        String empty = "";
        if (empty.equals(field1.getText()) || empty.equals(field2.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Поля должны быть заполнены!");
            alert.showAndWait();
        } else {
            int d0 = Integer.parseInt(field1.getText());
            int ko = Integer.parseInt(field2.getText());
            int zu = Integer.parseInt(field3.getText());
            int dbp = Integer.parseInt(field4.getText());
            int r = Integer.parseInt(field5.getText());
            int p = Integer.parseInt(field6.getText());
            if (d0 < 0 || ko < 0 || zu < 0 || dbp < 0 || r < 0 || p <= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            } else {
                double koef2 = ((double) (d0 + ko - zu + dbp + r)) / p;
                DecimalFormat df = new DecimalFormat("###.##");
                Ratio.rat2s = (String) df.format(koef2);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Коэффициент финансовой зависимости равен " + df.format(koef2) + "\n\nОптимальным значением показателя " +
                        "считается значение 0,5.\nРекомендуемое значение должно быть меньше 0,8.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/RatioCount3.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }
}
