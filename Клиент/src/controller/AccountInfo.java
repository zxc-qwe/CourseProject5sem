package controller;


import static service.UserService.showInfoUser;

import DTO.Company;
import DTO.User;
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



public class AccountInfo  implements Initializable{

    @FXML
    TextField login;
    @FXML
    TextField password;
    @FXML
    TextField name;
    @FXML
    TextField position;
    @FXML
    TextField role;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            User user = showInfoUser();
            login.setText(user.getLogin());
            password.setText(user.getPassword());
            name.setText(user.getName());
            position.setText(user.getPosition());
            role.setText("Пользователь");
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
