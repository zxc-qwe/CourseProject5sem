package controller;

import DTO.Ratio;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;

public class RatioCount7 {

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
        }
        else {
            int sk = Integer.parseInt(field1.getText());
            int DO = Integer.parseInt(field2.getText());
            int va = Integer.parseInt(field3.getText());
            int zap = Integer.parseInt(field4.getText());
            int zatr = Integer.parseInt(field5.getText());
            int av = Integer.parseInt(field6.getText());
            if (sk < 0 || DO < 0 || va < 0 || zap < 0 || zatr < 0 || av < 0 || (zap+zatr+av) <= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            } else {
                double koef7 = ((double) (sk + DO - va)) / (zap + zatr + av);
                DecimalFormat df = new DecimalFormat("###.##");
                Ratio.rat7s = (String) df.format(koef7);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Коэффициент обеспеченности запасов\nсобственными средствами равен " +
                        df.format(koef7) +
                        "\n\nНормативное значение коэффициента лежит\nв диапазоне от 0,6 до 0,8.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/RatioCount8.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }
}
