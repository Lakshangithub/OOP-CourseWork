package controller;

import DataBase.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Park;
import util.AddToLoadUi;
import util.CloseWindow;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class inParkingController implements AddToLoadUi, CloseWindow {
    public AnchorPane InParkingContext;
    public ComboBox cmbInParking;
    public TableView tblInParking;
    public TableColumn colVehiNo;
    public TableColumn colVehiType;
    public TableColumn colParkinhgSlot;
    public TableColumn colParkingTime;
    public Button btnAddVehicle;
    public Button btnAddDriver;
    public Button btnLogout;

    public void initialize(){
        colVehiNo.setCellValueFactory(new PropertyValueFactory("VehicleNumber"));
        colVehiType.setCellValueFactory(new PropertyValueFactory("VehicleType"));
        colParkinhgSlot.setCellValueFactory(new PropertyValueFactory("Slot"));
        colParkingTime.setCellValueFactory(new PropertyValueFactory("ParkedTime"));


        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("In Parking");
        obList.add("On Delevary");
        cmbInParking.setItems(obList);

        cmbInParking.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String s1 = String.valueOf(newValue);
            if(s1.equals("On Delevary")){
                Stage stage = (Stage) InParkingContext.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/onDelevary.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        loadAllData();

    }

    private void loadAllData() {
        ObservableList obList = FXCollections.observableArrayList();

        for (Park p: Database.parkInTable) {
            Park tm= new Park(p.getVehicleNumber(),p.getVehicleType(), p.getSlot(),p.getParkedTime());
            obList.add(tm);
        }
        tblInParking.setItems(obList);

    }

    public void AddVehiOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(InParkingContext);
        loadUi("addVehicle");
    }

    public void AddDriverOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(InParkingContext);
        loadUi("AddDriver");
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)) {
            InParkingContext.getChildren().clear();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ParkingVehicle.fxml"));
            InParkingContext.getChildren().add(parent);
        }
    }

    @Override
    public void loadUi(String location) throws IOException {
        URL resource = getClass().getResource("../view/"+location+".fxml");
        Parent  load =FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1= new Stage();
        stage1.setScene(scene);
        stage1.centerOnScreen();
        stage1.show();
    }

    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage= (Stage)x.getScene().getWindow();
        stage.close();

    }
}
