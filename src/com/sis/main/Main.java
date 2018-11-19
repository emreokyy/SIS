package com.sis.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.sis.controller.LoginpageController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/resources/Loginpage.fxml"));
        Parent login=(Parent)loader.load();
        LoginpageController controller=loader.getController();
        controller.setMainStage(primaryStage);
        //Parent login = FXMLLoader.load(getClass().getResource("Loginpage.fxml"));

        primaryStage.setTitle("Satellite Information System");
        //Scene scene1 = new Scene(login);
        primaryStage.setScene(new Scene(login,300,300));
        primaryStage.show();
       // if(lo)


       /* Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
        primaryStage.setTitle("Satellite Information System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show(); */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
