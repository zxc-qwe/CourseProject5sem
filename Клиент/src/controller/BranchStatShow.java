package controller;

import DTO.Branch;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.w3c.dom.Text;

import javafx.scene.control.TextField;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static service.UserService.BranchStatCompNum;
import static service.UserService.BranchStatRecv;



public class BranchStatShow implements Initializable {
    @FXML
    TextField name;
    @FXML
    TextField vvp_percent;
    @FXML
    TextField companiesNum;
    @FXML
    TextField companiesBD;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Branch branch = BranchStatRecv();
            String compnum = BranchStatCompNum();
            name.setText(branch.getNameBr());
            vvp_percent.setText(branch.getVvp_percent());
            companiesNum.setText(branch.getCompaniesNum());
            companiesBD.setText(compnum);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ReadyButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UserMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
