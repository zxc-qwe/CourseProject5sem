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

public class RatioCount5 {

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
            int oa = Integer.parseInt(field1.getText());
            int va = Integer.parseInt(field2.getText());
            if (oa < 0 || va <= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            } else {
                double koef5 = ((double) oa) / va;
                DecimalFormat df = new DecimalFormat("###.##");
                Ratio.rat5s = (String) df.format(koef5);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Коэффициент соотношения мобильных\nи иммобилизованных активов равен " + df.format(koef5) +
                        "\n\nДля данного показателя нормативных значений\nне установлено.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/RatioCount6.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }
}
