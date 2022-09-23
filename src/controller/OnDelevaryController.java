package controller;

import DataBase.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Deliver;
import util.AddToLoadUi;
import util.CloseWindow;

import java.io.IOException;
import java.net.URL;

public class OnDelevaryController implements AddToLoadUi, CloseWindow {
    public ComboBox cmbOnDelevary;
    public TableView tblOnDelevary;
    public TableColumn colVehiNo;
    public TableColumn colVehiType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;
    public AnchorPane OnDelevaryContext;
    public Button btnAddVehicle;
    public Button btnAddDriver;

    public void initialize(){
        colVehiNo.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
        colVehiType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTime"));



        cmbOnDelevary.getItems().add("In Parking");
      cmbOnDelevary.getItems().add("On Delevary");

        cmbOnDelevary.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String s1 = String.valueOf(newValue);
            if(s1.equals("In Parking")){
                Stage stage = (Stage) cmbOnDelevary.getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/inParking.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        loadAllData();
    }

    private void loadAllData() {
        ObservableList obList = FXCollections.observableArrayList();
        for (Deliver d: Database.deliverTable) {


            Deliver tm= new Deliver(d.getVehicleNo(),d.getVehicleType(),d.getDriverName(),d.getLeftTime());
            obList.add(tm);

        }
        tblOnDelevary.setItems(obList);

    }

    public void AddVehiOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(OnDelevaryContext);
        loadUi("addVehicle");
    }

    public void AddDriverOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(OnDelevaryContext);
        loadUi("AddDriver");
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
