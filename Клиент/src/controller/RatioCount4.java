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

public class RatioCount4 {

    @FXML
    TextField field1;
    @FXML
    TextField field2;


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
            int sOS = Integer.parseInt(field1.getText());
            int sk = Integer.parseInt(field2.getText());
            if (sOS < 0 || sk <= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            } else {
                double koef4 = ((double) sOS) / sk;
                DecimalFormat df = new DecimalFormat("###.##");
                Ratio.rat4s = (String) df.format(koef4);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Коэффициент маневренности собственных\nоборотных средств равен " + df.format(koef4) +
                        "\n\nРекомендуемое значение коэффициента 0,2 – 0,5.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/RatioCount5.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }
}
