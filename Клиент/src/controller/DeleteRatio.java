package controller;

import DTO.Company;
import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static service.UserService.*;

public class DeleteRatio implements Initializable {

    public TableView<Company> table;
    public TableColumn<Company, String> company;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Company> companies = null;
        try{
            companies = FXCollections.observableArrayList(getCompanies());
            company.setCellValueFactory(new PropertyValueFactory<Company, String>("companyName"));
        } catch (IOException e){
            e.printStackTrace();
        }
        table.setItems(companies);
    }


    public void ReadyButton (ActionEvent event) throws IOException {
        Company selectedCompany = table.getSelectionModel().getSelectedItem();
        if (selectedCompany == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Нужно выбрать компанию.");
            alert.showAndWait();
        } else {
            DeleteCompany(selectedCompany.getCompanyName());
        }
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/DeleteRatio.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
