package controller;

import DTO.CompanyDiagr;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import jdk.jfr.Category;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static service.UserService.getCompanyStat;

public class DiagramStable implements Initializable {

    public BarChart chart;
    public CategoryAxis x;
    public NumberAxis y;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ArrayList<CompanyDiagr> diagr = getCompanyStat();
            XYChart.Series<String, Number> setOfData1 = new XYChart.Series<>();
            XYChart.Series<String, Number> setOfData2 = new XYChart.Series<>();
            for (CompanyDiagr entry : diagr) {
                setOfData1.getData().add(new XYChart.Data(entry.getBranchName(), entry.getStable()));
                setOfData2.getData().add(new XYChart.Data(entry.getBranchName(), entry.getAll()));
            }
            setOfData1.setName("Финансово стабильные компании в базе данных");
            setOfData2.setName("Все компании в базе данных");
            chart.getData().addAll(setOfData1);
            chart.getData().addAll(setOfData2);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void ReadyButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UserMenu.fxml"));
        Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
    }
}
