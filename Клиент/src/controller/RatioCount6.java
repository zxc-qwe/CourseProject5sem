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

public class RatioCount6 {

    @FXML
    TextField field1;
    @FXML
    TextField field2;
    @FXML
    TextField field3;


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
            int va = Integer.parseInt(field2.getText());
            int oa = Integer.parseInt(field3.getText());
            if (sk < 0 || va < 0 || oa <= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            }else {
                double koef6 = ((double) (sk - va)) / oa;
                DecimalFormat df = new DecimalFormat("###.##");
                Ratio.rat6s = (String) df.format(koef6);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Коэффициент обеспеченности оборотного\nкапитала собственными источниками\nфинансирования равен " +
                        df.format(koef6) +
                        "\n\nПредприятие обеспечено собственными\nисточниками финансирования оборотного капитала\nпри значении коэффициента ≥0,1.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/RatioCount7.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }
}
