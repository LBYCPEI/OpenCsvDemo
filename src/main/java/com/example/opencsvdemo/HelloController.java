package com.example.opencsvdemo;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    public static final String CSV_FILENAME= "ECE_CpE_Thesis_Groups_2nd_Term_AY2021_2022.csv";

    private final ObservableList<ThesisRecord> database = FXCollections.observableArrayList();

    @FXML
    private TableView<ThesisRecord> tableView;

    @FXML
    TableColumn<ThesisRecord, String> titleColumn;

    @FXML
    TableColumn<ThesisRecord, String> groupColumn;

    @FXML
    TableColumn<ThesisRecord, String> trmColumn;

    @FXML
    TableColumn<ThesisRecord, String> syColumn;

    @FXML
    TableColumn<ThesisRecord, String> noColumn;

    @FXML
    TableColumn<ThesisRecord, String> membersColumn;

    @FXML
    TableColumn<ThesisRecord, String> adviserColumn;

    @FXML
    TableColumn<ThesisRecord, String> chairColumn;

    @FXML
    TableColumn<ThesisRecord, String> panelist1Column;

    @FXML
    TableColumn<ThesisRecord, String> panelist2Column;

    @FXML
    TableColumn<ThesisRecord, String> statusColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //        String filename = "C:\\Users\\ECE\\IdeaProjects\\OpenCsvDemo\\target\\classes\\ECE_CpE_Thesis_Groups_2nd_Term_AY2021_2022.csv";
        String filename = "target/classes/" + CSV_FILENAME; // relative to project root directory
        try(CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).withSkipLines(1).build()) // Skip header or 1st row
        {
            String [] nextLine;

            //Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(Arrays.toString(nextLine));
                ThesisRecord thesisRecord = new ThesisRecord(nextLine);
                database.add(thesisRecord);
            }
        }
        catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        // Associate columns to data
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        trmColumn.setCellValueFactory(new PropertyValueFactory<>("term"));
        syColumn.setCellValueFactory(new PropertyValueFactory<>("sy"));
        noColumn.setCellValueFactory(new PropertyValueFactory<>("grp_number"));
        membersColumn.setCellValueFactory(new PropertyValueFactory<>("members"));
        adviserColumn.setCellValueFactory(new PropertyValueFactory<>("adviser"));
        chairColumn.setCellValueFactory(new PropertyValueFactory<>("chair_panel"));
        panelist1Column.setCellValueFactory(new PropertyValueFactory<>("panelist1"));
        panelist2Column.setCellValueFactory(new PropertyValueFactory<>("panelist2"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.getItems().setAll(database);
    }


}