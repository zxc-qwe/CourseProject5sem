package controller;

import DTO.User;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static service.UserService.getUnblockUsers;
import static service.UserService.unblockUser;


public class UnblockUsers implements Initializable{

    public TableView<User> users;
    public TableColumn<User, String> userLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<User> userLogs = null;
        try {
            userLogs = FXCollections.observableArrayList(getUnblockUsers());
            userLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        users.setItems(userLogs);

    }

    public void UnblockUser(ActionEvent event) throws IOException {
        User selectedUser = users.getSelectionModel().getSelectedItem();
        if (selectedUser == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Нужно выбрать пользователя.");
            alert.showAndWait();
        } else {
            unblockUser(selectedUser.getLogin());
            users.getItems().removeAll(selectedUser);
        }
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UnblockUsers.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ManageUsers.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
