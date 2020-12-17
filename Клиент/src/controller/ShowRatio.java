package controller;

import DTO.Ratio;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static service.UserService.recvName;
import static service.UserService.showRatio;

public class ShowRatio implements Initializable {

    @FXML
    TextField company;
    @FXML
    TextField koef1;
    @FXML
    TextField koef2;
    @FXML
    TextField koef3;
    @FXML
    TextField koef4;
    @FXML
    TextField koef5;
    @FXML
    TextField koef6;
    @FXML
    TextField koef7;
    @FXML
    TextField koef8;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            String compName = recvName();
            Ratio ratio = showRatio();
            company.setText(compName);
            koef1.setText(ratio.getRat1());
            koef2.setText(ratio.getRat2());
            koef3.setText(ratio.getRat3());
            koef4.setText(ratio.getRat4());
            koef5.setText(ratio.getRat5());
            koef6.setText(ratio.getRat6());
            koef7.setText(ratio.getRat7());
            koef8.setText(ratio.getRat8());
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void ReadyButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/RatioCompanies.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
