package controller;

import DTO.Company;
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

import static service.UserService.*;

public class RatioCompanies implements Initializable {

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

    public void RatioButton(ActionEvent event) throws IOException {
        Company selectedCompany = table.getSelectionModel().getSelectedItem();
        if (selectedCompany == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Нужно выбрать компанию.");
            alert.showAndWait();
        } else {
            getRatio(selectedCompany.getCompanyName());
        }
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ShowRatio.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }

    public void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
