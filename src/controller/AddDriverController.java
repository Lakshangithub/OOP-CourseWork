package controller;

import DataBase.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;
import util.CloseWindow;

import java.io.IOException;
import java.net.URL;

public class AddDriverController implements CloseWindow {
    public TextField txtName;
    public TextField txtNic;
    public TextField txtAddress;
    public TextField txtLicence;
    public TextField txtContact;
    public AnchorPane AddDriverContext;

    public void AddDriverOnAction(ActionEvent actionEvent) {
        Database.driverTable.add(new Driver(txtName.getText(),txtNic.getText(),
                txtLicence.getText(),txtAddress.getText(),txtContact.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Added Driver In To Database");
        alert.show();
        ClearFields();

    }

    private void ClearFields() {
        txtName.clear();
        txtNic.clear();
        txtLicence.clear();
        txtAddress.clear();
        txtContact.clear();
    }


    @Override
    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage= (Stage) x.getScene().getWindow();
        stage.close();

    }

    public void CancelAddDriver(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(AddDriverContext);

        URL resource = getClass().getResource("../view/inParking.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage1= new Stage();
        stage1.setScene(scene);
        stage1.centerOnScreen();
        stage1.show();

    }
}
