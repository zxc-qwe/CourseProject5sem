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

import static service.UserService.GetBranches;

public class RatioCount8 {

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
            int sk1 = Integer.parseInt(field1.getText());
            int sk2 = Integer.parseInt(field2.getText());
            if (sk1 < 0 || sk2 <= 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Недопустимое значение.");
                alert.showAndWait();
            } else {
                double koef8 = ((double) sk2) / sk1;
                DecimalFormat df = new DecimalFormat("###.##");
                Ratio.rat8s = (String) df.format(koef8);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Коэффициент сохранности собственного\nкапитала равен " +
                        df.format(koef8) +
                        "\n\nОптимальное значение коэффициента больше или равно 1.");
                alert.showAndWait();
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/SetCompanyName.fxml"));
                Main.primaryStage.setScene(new Scene(root));
                Main.primaryStage.show();
            }
        }
    }
}
