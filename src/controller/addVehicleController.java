package controller;

import DataBase.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Vehicle;
import util.CloseWindow;

import java.io.IOException;
import java.net.URL;

public class addVehicleController implements CloseWindow {
    public AnchorPane addVehicleContext;
    public TextField txtVehiNo;
    public ComboBox cmbVehicleType;
    public TextField txtPsengers;
    public TextField txtWeight;

    public void initialize(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Van");
        obList.add("Bus");
        obList.add("Cargo Lorry");
        cmbVehicleType.setItems(obList);
    }

    public void AddVehicleOnAction(ActionEvent actionEvent) {
        try {
            Database.vehicleTable.add
                    (new Vehicle(String.valueOf(txtVehiNo.getText()), (String) cmbVehicleType.getValue(),Double.parseDouble(txtWeight.getText()),
                            Integer.parseInt(txtPsengers.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Added In To Database");
            alert.show();
            clearFields();

        }catch(Throwable e){
            Alert warning =  new Alert(Alert.AlertType.WARNING,"Maximum Weight should be Double Type");
            warning.showAndWait();
            txtWeight.clear();
        }
    }

    private void clearFields() {
        txtVehiNo.clear();
        txtWeight.clear();
        txtPsengers.clear();
        cmbVehicleType.getSelectionModel().clearSelection();

    }

    public void CancelAddVehicle(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(addVehicleContext);   //CLOSE WINDOW IN CloseWindowUi

        URL resource = getClass().getResource("../view/inParking.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1= new Stage();
        stage1.setScene(scene);
        stage1.centerOnScreen();
        stage1.show();

    }

    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage= (Stage) x.getScene().getWindow();
        stage.close();

    }
}
