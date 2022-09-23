package controller;

import DataBase.Database;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Deliver;
import model.Park;
import util.AddToLoadUi;
import util.CloseWindow;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;

public class ParkingVehicleController implements AddToLoadUi, CloseWindow {
    public AnchorPane loginContextAnchor;
    public ComboBox cmbSelectVehicle;
    public TextField txtVehicleType;
    public TextField txtSlot;
    public ComboBox cmbDriver;
    public Button btnManagerLogin;
    public TextField txtDateAndTime;
    public Button btnDelivery;
    public Button btnPark;

    boolean b=false;
    boolean deliver=false;

    public void initialize(){
        try {
            cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                vehicleType(newValue);
            });
        }catch (Exception e){
            System.out.println("error");
        }


        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("Sumith Kumara");
        obList.add("Amila Pathirana");
        obList.add("Jithmal Perera");
        obList.add("Sumith Dissanayaka");
        obList.add("Sumanasiri Herath");
        obList.add("Awantha Fernando");
        obList.add("Charith Sudara");
        obList.add("Prashan Dineth");
        obList.add("Chethiya Dilan");
        obList.add("Dushantha Perera");
        obList.add("Sumith Udayanga");
        obList.add("Dinesh Udara");
        obList.add("Udana Chathuranga");
        obList.add("Mohommad Riaz");
        obList.add("Sadun Kumara");
        obList.add("Priyanga Perera");
        cmbDriver.setItems(obList);

        ObservableList<String> obList2 = FXCollections.observableArrayList();
        obList2.add("NA-3434");
        obList2.add("KA-4563");
        obList2.add("58-3567");
        obList2.add("GF-4358");
        obList2.add("CCB-3568");
        obList2.add("LM-6679");
        obList2.add("QA-3369");
        obList2.add("KB-3668");
        obList2.add("JJ-9878");
        obList2.add("GH-5772");
        obList2.add("XY-4456");
        obList2.add("YQ-3536");
        obList2.add("CBB-3566");
        obList2.add("QH-3444");
        cmbSelectVehicle.setItems(obList2);

        currentDateTime();
    }


    private void vehicleType(Object newValue) {
        String s1 = String.valueOf((newValue));
        for(int i=0; i<Database.vehicleTable.size(); i++){
            String num = Database.vehicleTable.get(i).getVehicleNo();
                    if(s1.equals(num)){
                        txtVehicleType.setText(Database.vehicleTable.get(i).getVehicleType());
                    }
        }
        parkingSlot();
    }

    private void parkingSlot() {
        switch (txtVehicleType.getText()){
            case "Van": {
                setSlot("Van");
            }break;

            case "Bus": {
                setSlot("Bus");
            }break;

            case "Cargo Lorry": {
                setSlot("Cargo Lorry");
            }break;
        }

    }

    private void setSlot(String vehicleType) {
        for(int i=0; i<Database.slotTable.size(); i++){
            for (int j=0; j<Database.slotTable.size(); j++){
                if(vehicleType.equals(Database.slotTable.get(i).getVehicleType()) && Database.slotTable.get(i).getStatus().equals("notUse")) {
                    txtSlot.setText(Database.slotTable.get(i).getSlot());
                    return;
                }
            }
        }
        clearLabel();
    }

    private void clearLabel() {
        for(int i= 0; i<Database.slotTable.size(); i++){
            if(Database.vehicleTable.get(i).getVehicleNo().equals(cmbSelectVehicle.getValue())){
                txtSlot.setText("");
            }
        }
    }


    private void currentDateTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            txtDateAndTime.setText(currentDate.getYear()+"-"+currentDate.getMonthValue()+"-"+currentDate.getDayOfMonth()+
                    "    "+ currentTime.getHour() + ":" + currentTime.getMinute() + ":"+ currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    public void ManagementLogOnAction(ActionEvent actionEvent) throws IOException {
        Database.belowUp = loginContextAnchor;

       loadUi("loginForm");
    }

    private void setnotuse(String slotnmbr) {
        for (int i=0; i<Database.slotTable.size(); i++){
            if (Database.slotTable.get(i).getSlot().equals(slotnmbr)){
                Database.slotTable.get(i).setStatus("notUse");
            }
        }
    }


    public void OnDeleveryrOnAction(ActionEvent actionEvent) throws IOException {

        try{
            for(int k=0; k<Database.parkInTable.size();k++){
                if(cmbSelectVehicle.getValue().equals(Database.parkInTable.get(k).getVehicleNumber())){
                    setnotuse(Database.parkInTable.get(k).getSlot());
                }
            }
        }catch (Exception e){

        }
        deliveryTest();
    }

    private void deliveryTest() {
        cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String temp2=String.valueOf(newValue);
            btnDelivery.setDisable(false);

            for (int i=0;i<Database.deliverTable.size();i++){
                deliver= Database.deliverTable.get(i).getVehicleNo().contains(temp2);

                if(deliver==false){
                    btnDelivery.setDisable(true);
                    // btnPark.setVisible(false);
                }
            }
        });

        if(deliver==false){

            if(cmbSelectVehicle.getValue()!=null && cmbDriver.getValue()!=null) {
                String data = (String.valueOf(cmbSelectVehicle.getValue()));
                for (int i = 0; i < Database.parkInTable.size(); i++) {
                    if (Database.parkInTable.get(i).getVehicleNumber().contains(data)) {
                        Database.parkInTable.remove(i);
                    }
                }
                Deliver d = new Deliver(String.valueOf(cmbSelectVehicle.getValue()), txtVehicleType.getText(), String.valueOf(cmbDriver.getValue()), txtDateAndTime.getText());
                Database.deliverTable.add(d);
            }

        }
        clearFields();
    }


    public void ParkVehicleOnAction(ActionEvent actionEvent) throws IOException {


            for(int k=0;k<Database.slotTable.size();k++){
                if(txtSlot.getText().equals(Database.slotTable.get(k).getSlot())){
                    Database.slotTable.get(k).setStatus("Use");
                }
            }

        parkTest();
    }

    private void parkTest() {
        cmbSelectVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            btnPark.setDisable(false);
            String temp=String.valueOf(newValue);
            for (int i=0;i<Database.parkInTable.size();i++){
                b= Database.parkInTable.get(i).getVehicleNumber().contains(temp);
                if(b==true){
                    btnPark.setDisable(true);
                    //  btnPark.setDisable(b);
                }
            }
        });
        if(b==false){
            if(cmbSelectVehicle.getValue()!=null){
                String data= (String.valueOf(cmbSelectVehicle.getValue())) ;
                Park p = new Park(String.valueOf(cmbSelectVehicle.getValue()),txtVehicleType.getText(),txtSlot.getText(),txtDateAndTime.getText());
                Database.parkInTable.add(p);

                for(int i=0; i<Database.deliverTable.size(); i++){
                    if(Database.deliverTable.get(i).getVehicleNo().contains(data)){
                        Database.deliverTable.remove(i);

                    }

                }
            }
        }
        clearFields();
    }

    private void clearFields() {
        txtVehicleType.clear();
        cmbSelectVehicle.getSelectionModel().clearSelection();
        cmbDriver.getSelectionModel().clearSelection();

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
