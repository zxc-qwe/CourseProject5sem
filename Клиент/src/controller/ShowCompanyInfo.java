package controller;

import DTO.Company;
import DTO.Ratio;
import Main.Main;
import javafx.beans.Observable;
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


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import static service.UserService.InfoCompanyRecv;
import static service.UserService.showRatioCompany;

public class ShowCompanyInfo implements Initializable {

    @FXML
    TextField name;
    @FXML
    TextField ownership;
    @FXML
    TextField workersNum;
    @FXML
    TextField market_percent;
    @FXML
    TextField branch;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Company info = InfoCompanyRecv();
            //ObservableList<Company> companyInfo = FXCollections.observableArrayList(info);
            name.setText(info.getCompanyName());
            ownership.setText(info.getOwnership());
            workersNum.setText(info.getWorkersNum());
            market_percent.setText(info.getMarket_percent());
            branch.setText(info.getBranch());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ReadyButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/CompanyShowRatio.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
