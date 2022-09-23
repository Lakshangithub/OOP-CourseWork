package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static DataBase.Database.belowUp;


public class LoginFormController {

    public AnchorPane loginFormContext;
    public TextField txtName;
    public TextField pwdPassword;
    public Button LoginBtn;
    public Button cancelBtn;
    int attemptsLogAdmin = 0;


    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        CloseWindowUi(loginFormContext);

        attemptsLogAdmin++;
        if (attemptsLogAdmin < 5) {  // attempts calculate

            if (txtName.getText().equals("lakshan") & pwdPassword.getText().equals("123")) {
                new Alert(Alert.AlertType.CONFIRMATION,"Want to move on ?...").show();


                //call to interface loadUi
                try{
                    loadUitwo("inParking");
                }catch (Exception e){
                    System.out.println(e);
                }


            } else {
                // error warning information
                new Alert(Alert.AlertType.WARNING, "Please Try Again...").showAndWait();


                loadUi( "loginForm"); //call to interface loadUi
            }
        } else {
            //  number of wrong input visible false option
            txtName.setVisible(false);
            pwdPassword.setVisible(false);

        }
    }

    public void loadUi(String location) throws IOException {
        URL resources = getClass().getResource("../view/"+location+".fxml");
        Parent load =FXMLLoader.load(resources);
        Scene scene = new Scene(load);
        Stage stage= new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    public void CloseWindowUi(AnchorPane x) throws IOException {
        Stage stage = (Stage) x.getScene().getWindow();
        stage.close();
    }

    private void loadUitwo(String location) throws IOException {
        Stage stage = (Stage)belowUp.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));

    }

    public void canzelOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are You Sure Cancel?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)) {
            CloseBtn(cancelBtn);
        }
    }

    public void CloseBtn(Button btn) throws IOException {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

}
