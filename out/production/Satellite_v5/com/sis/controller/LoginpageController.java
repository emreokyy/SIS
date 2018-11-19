package com.sis.controller;

import com.sis.util.Winsize;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.Event;

import javafx.stage.Stage;
import com.sis.util.MySceneLoader;
import com.sis.model.User;

import java.io.IOException;

public class LoginpageController extends ParentController{



    @FXML
    Button btnlogin;
    @FXML
    TextField txtusername;
    @FXML
    TextField txtpassword;
    @FXML
    public void initialize(){
        btnlogin.setOnAction(this::loginButtonAction);
    }



    public void loginButtonAction(Event event){
        User user= new User();
        MySceneLoader loader=new MySceneLoader();
        user.setUsername(txtusername.getText());
        user.setPassword(txtpassword.getText());
        if (user.getUsername().equals("admin") && user.getPassword().equals("123")) {

            try {
                loader.load(getMainStage(), "/resources/Table.fxml"/*,Winsize.TABLEWIDTH,Winsize.TABLEHEIGHT*/);

                /*Stage stage =new Stage();
                stage.show(); */

            } catch (IOException exception) {

            }
        }
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid user name or password. Please try again !");
           // errorAlert.setContentText(" Please try again !");
            errorAlert.showAndWait();

        }


    }

}
