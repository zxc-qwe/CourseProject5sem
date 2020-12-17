package controller;

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
import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static DTO.Company.comp_name;
import static service.UserService.checkStability;
import static service.UserService.showRatioCompany;

public class CompanyShowRatio implements Initializable {

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

        try {

            Ratio ratio = showRatioCompany();
            if (ratio.getRat1().equals("Error")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Ошибка.");
                alert.showAndWait();
            } else {
                koef1.setText(ratio.getRat1());
                koef2.setText(ratio.getRat2());
                koef3.setText(ratio.getRat3());
                koef4.setText(ratio.getRat4());
                koef5.setText(ratio.getRat5());
                koef6.setText(ratio.getRat6());
                koef7.setText(ratio.getRat7());
                koef8.setText(ratio.getRat8());
            }
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

    public void ReportButton(ActionEvent event) throws IOException {
        XWPFDocument document = new XWPFDocument();

        XWPFParagraph reportParagraph = document.createParagraph();
        XWPFRun par = reportParagraph.createRun();
        par.setBold(true);
        par.setTextPosition(10);
        par.addBreak();
        par.setText("Текстовый отчет коэффициенты стабильности");

        XWPFTable tableOne = document.createTable();
        XWPFTableRow tableOneRowOne = tableOne.getRow(0);
        createTableHeaders(document, tableOneRowOne);


        XWPFTableRow newRow = tableOne.createRow();
        newRow.getCell(0).setText(comp_name);
        newRow.getCell(1).setText(koef1.getText());
        newRow.getCell(2).setText(koef2.getText());
        newRow.getCell(3).setText(koef3.getText());
        newRow.getCell(4).setText(koef4.getText());
        newRow.getCell(5).setText(koef5.getText());
        newRow.getCell(6).setText(koef6.getText());
        newRow.getCell(7).setText(koef7.getText());
        newRow.getCell(8).setText(koef8.getText());

        XWPFParagraph resultParagraph = document.createParagraph();
        XWPFRun result = resultParagraph.createRun();
        result.setTextPosition(10);
        result.addBreak();
        String check = checkStability(comp_name);
        if (check.equals("Stable") ) {
            result.setText("На основании рассчитанных коэффициентов компания является финансово стабильной.");
        } else {
            result.setText("На основании рассчитанных коэффициентов компания является финансово нестабильной.");
        }


        String reportName = "report" + java.time.LocalDate.now() + ".doc";
        FileOutputStream outStream = new FileOutputStream("D:\\uni\\3 курс\\Курсовая\\Клиент\\" + reportName);
        document.write(outStream);
        outStream.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("Отчет (" + reportName + ") был создан!");
        alert.showAndWait();
    }

    private void createTableHeaders(XWPFDocument document, XWPFTableRow tableOneRowOne) {
        tableOneRowOne.getCell(0);
        XWPFParagraph company = tableOneRowOne.getCell(0).addParagraph();
        XWPFRun companyPar = company.createRun();
        companyPar.setBold(true);
        companyPar.setText("Компания");

        XWPFParagraph koef1 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef1Par = koef1.createRun();
        koef1Par.setBold(true);
        koef1Par.setText("Коэф автономии");

        XWPFParagraph koef2 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef2Par = koef2.createRun();
        koef2Par.setBold(true);
        koef2Par.setText("Коэф фин зависимости");

        XWPFParagraph koef3 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef3Par = koef3.createRun();
        koef3Par.setBold(true);
        koef3Par.setText("Коэф соотн средств");

        XWPFParagraph koef4 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef4Par = koef4.createRun();
        koef4Par.setBold(true);
        koef4Par.setText("Коэф маневр об средств");

        XWPFParagraph koef5 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef5Par = koef5.createRun();
        koef5Par.setBold(true);
        koef5Par.setText("Коэф соотн активов");

        XWPFParagraph koef6 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef6Par = koef6.createRun();
        koef6Par.setBold(true);
        koef6Par.setText("Коэф обеспеч-ти источниками финанас-ия");

        XWPFParagraph koef7 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef7Par = koef7.createRun();
        koef7Par.setBold(true);
        koef7Par.setText("Коэф обеспеч запасов");

        XWPFParagraph koef8 = tableOneRowOne.addNewTableCell().addParagraph();
        XWPFRun koef8Par = koef8.createRun();
        koef8Par.setBold(true);
        koef8Par.setText("Коэф сохр капитала");

    }
}
