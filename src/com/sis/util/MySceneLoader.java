package com.sis.util;

import com.sis.controller.LoginpageController;
import com.sis.controller.ParentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MySceneLoader {

    public  void  load(Stage primaryStage, String fxmlFileName/*int width,int height*/)throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(fxmlFileName));
        Parent root=(Parent)loader.load();
        ParentController controller=loader.getController();
        primaryStage.setScene(new Scene(root/*,width,height)*/));
        controller.setMainStage(primaryStage);
    }
}
