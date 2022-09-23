package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;

public class AddVehicleOnDelevaryController {
    public TextField txtVehiNo;
    public TextField txtPsengers;
    public TextField txtWeight;
    public ComboBox cmbVehicleType;

    public void initialize(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Van");
        obList.add("Bus");
        obList.add("Cargo Lorry");
        cmbVehicleType.setItems(obList);
    }

    public void AddVehicleOnAction(ActionEvent actionEvent) throws IOException {
    }
}
