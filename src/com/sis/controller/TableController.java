package com.sis.controller;
import com.sis.model.SimpleFileTreeItem;
import com.sis.util.Winsize;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import com.sis.util.MySceneLoader;

import com.sis.model.Satellite;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.*;
import java.nio.file.Files;
import java.util.Collection;



public class TableController extends ParentController {
    //private Stage mainStage;

    @FXML
    TableView<Satellite> table;
    @FXML
    TableColumn<Satellite, String> clmsatname;
    @FXML
    TableColumn<Satellite, String> clmcountry;
    @FXML
    TableColumn<Satellite, Integer> clmapogee;
    @FXML
    TableColumn<Satellite, Integer> clmperigee;
    @FXML
    TableColumn<Satellite, String> clmpurpose;
    @FXML
    TableColumn<Satellite, String> clmorbit;
    @FXML
    Button btnadd;
    @FXML
    Button btndelete;
    @FXML
    Button btnupdate;
    @FXML
    Button btnLogOut;
    @FXML
    TextField txtname;
    @FXML
    TextField txtcountry;
    @FXML
    TextField txtapogee;
    @FXML
    TextField txtperigee;
    @FXML
    TextField txtpurpose;
    @FXML
    TextField txtorbit;
    @FXML
    MenuBar MenuBar;
    @FXML
    MenuItem menuClose;
    @FXML
    MenuItem menuNew;
    @FXML
    MenuItem menuSave;
    @FXML
    MenuItem menuOpen;
    @FXML
    MenuItem AddMenuItem;
    @FXML
    MenuItem DeleteMenuItem;
    @FXML
    MenuItem UpdateMenuItem;
    @FXML
    TreeView tree;
    @FXML
    BorderPane borderPane;
    @FXML
    MenuItem AboutItem;



    @FXML
    public void initialize() {
        TreeView<File> fileView = new TreeView<File>(
                new SimpleFileTreeItem(new File("C:\\")));
        SplitPane spl = new SplitPane();
        spl.getItems().add(fileView);
        borderPane.setLeft(spl);
        txtname.setPromptText("Satellite Name");
        txtcountry.setPromptText("Country");
        txtapogee.setPromptText("Apogee(km)");
        txtperigee.setPromptText("Perigee(km)");
        txtpurpose.setPromptText("Purpose");
        txtorbit.setPromptText("Class of Orbit");
        btnadd.setOnAction(this::addButtonClicked);
        btndelete.setOnAction(this::deleteButtonClicked);
        btnupdate.setOnAction(this::updateButtonClicked);
        btnLogOut.setOnAction(this::LogOutButtonClicked);
        menuClose.setOnAction(this::CloseButtonClicked);
        menuNew.setOnAction(this::NewButtonClicked);
        menuSave.setOnAction(event1 -> {
            try {
                SaveButtonClicked(event1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        menuOpen.setOnAction(this::OpenButtonClicked);
        AddMenuItem.setOnAction(this::addButtonClicked);
        DeleteMenuItem.setOnAction(this::deleteButtonClicked);
        UpdateMenuItem.setOnAction(this::updateButtonClicked);
        AboutItem.setOnAction(event -> {
            try {
                AboutItemClicked(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getColumn();


        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {


                txtname.setText(newSelection.getSatname());
                txtcountry.setText(newSelection.getCountry());
                txtapogee.setText(newSelection.getApogee());
                txtperigee.setText(newSelection.getPerigee());
                txtpurpose.setText(newSelection.getPurpose());
                txtorbit.setText(newSelection.getOrbit());
              /*  txtcountry.setText(table.getSelectionModel().getSelectedItem().getCountry());
                txtapogee.setText(table.getSelectionModel().getSelectedItem().getApogee());
                txtperigee.setText(table.getSelectionModel().getSelectedItem().getPerigee());
                txtpurpose.setText(table.getSelectionModel().getSelectedItem().getPurpose()); */
              //  table.getSelectionModel().select(-1);
                //table.getSelectionModel().clearSelection();
            }
        });

           /* Satellite selected = new Satellite();
            selected.setSatname(table.getSelectionModel().getSelectedItem().getSatname());
            selected.setCountry(table.getSelectionModel().getSelectedItem().getCountry());
            selected.setApogee(table.getSelectionModel().getSelectedItem().getApogee());
            selected.setPerigee(table.getSelectionModel().getSelectedItem().getPerigee());
            selected.setPurpose(table.getSelectionModel().getSelectedItem().getPurpose());*/

        }



    private void getColumn() {

        clmsatname.setCellValueFactory(new PropertyValueFactory<>("satname"));
        clmcountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        clmapogee.setCellValueFactory(new PropertyValueFactory<>("apogee"));
        clmperigee.setCellValueFactory(new PropertyValueFactory<>("perigee"));
        clmpurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        clmorbit.setCellValueFactory(new PropertyValueFactory<>("orbit"));


    }

    public void addButtonClicked(Event event) {

        Satellite entry = new Satellite();
        entry.setSatname(txtname.getText());
        entry.setCountry(txtcountry.getText());
        entry.setApogee(txtapogee.getText());
        entry.setPerigee(txtperigee.getText());
        entry.setPurpose(txtpurpose.getText());
        entry.setOrbit(txtorbit.getText());
        if( entry.getSatname().equals("") | entry.getCountry().equals("") | entry.getApogee().equals("") | entry.getPerigee().equals("") | entry.getPurpose().equals("")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Please enter all areas!");
            // errorAlert.setContentText(" Please try again !");
            errorAlert.showAndWait();

        } else table.getItems().add(entry);

        txtname.clear();
        txtcountry.clear();
        txtapogee.clear();
        txtperigee.clear();
        txtpurpose.clear();
        txtorbit.clear();


    }

    public void deleteButtonClicked(Event event) {


        if(table.getSelectionModel().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There is no selected item!");
            // errorAlert.setContentText(" Please try again !");
            errorAlert.showAndWait();
        }else  {
            ObservableList<Satellite> productSelected, allProducts;
            allProducts = table.getItems();
            productSelected = table.getSelectionModel().getSelectedItems();
            productSelected.forEach(allProducts::remove);
            table.getSelectionModel().clearSelection();
            txtname.clear();
            txtcountry.clear();
            txtapogee.clear();
            txtperigee.clear();
            txtpurpose.clear();
            txtorbit.clear();
           // table.getSelectionModel().select(-1);
        }

    }

    public void updateButtonClicked(Event event) {

        if(txtorbit.getText().equals("")| txtname.getText().equals("") | txtcountry.getText().equals("") | txtapogee.getText().equals("") | txtperigee.getText().equals("") | txtpurpose.getText().equals("")){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("There is no selected item!");
            // errorAlert.setContentText(" Please try again !");
            errorAlert.showAndWait();

        } else {
            Satellite newentry = new Satellite();
            newentry.setSatname(txtname.getText());
            newentry.setCountry(txtcountry.getText());
            newentry.setApogee(txtapogee.getText());
            newentry.setPerigee(txtperigee.getText());
            newentry.setPurpose(txtpurpose.getText());
            newentry.setOrbit(txtorbit.getText());
       ObservableList<Satellite> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);
        table.getItems().add(newentry);}
        table.getSelectionModel().clearSelection();

        txtname.clear();
        txtcountry.clear();
        txtapogee.clear();
        txtperigee.clear();
        txtpurpose.clear();
        txtorbit.clear();
    }

    public void LogOutButtonClicked(Event event) {
        try {
            MySceneLoader loader = new MySceneLoader();
            loader.load(getMainStage(), "/resources/Loginpage.fxml"/*, Winsize.LOGINWIDTH, Winsize.LOGINHEIGHT*/);
                /*Stage stage =new Stage();
                stage.show(); */

        } catch (IOException exception) {

        }
    }

    public void CloseButtonClicked(Event event) {
        Platform.exit();
        System.exit(0);
    }

    public void NewButtonClicked(Event event) {
        try {
            MySceneLoader loader = new MySceneLoader();
            loader.load(getMainStage(), "/resources/Table.fxml"/*, Winsize.TABLEWIDTH, Winsize.TABLEHEIGHT*/);
        } catch (IOException exception) {
        }

    }

    public void SaveButtonClicked(Event event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose location To Save Satellite Data");
        File selectedFile = null;
        // while(selectedFile== null){
        selectedFile = chooser.showSaveDialog(null);
        //}

        File file = new File(String.valueOf(selectedFile));
        Writer outFile = null;
       
        try {
            outFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }

        for (int i = 0; i < table.getItems().size(); i++)
            outFile.write(table.getItems().get(i).getSatname() + "," + table.getItems().get(i).getCountry() + "," + table.getItems().get(i).getPurpose() +","+ table.getItems().get(i).getOrbit() + "," + table.getItems().get(i).getPerigee() + "," + table.getItems().get(i).getApogee() + "," + "\n");
        outFile.close();
    }

    public void OpenButtonClicked(Event event) {
        table.getItems().clear();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose location To Open Satellite Data");
        File selectedFile = null;
        // while(selectedFile== null){
        selectedFile = chooser.showOpenDialog(null);
        //}
       // File file = new File(String.valueOf(selectedFile));
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(String.valueOf(selectedFile))));
            String line;
            String[] array;

            while ((line = br.readLine()) != null) {
                Satellite load;
                load = new Satellite();
                array = line.split(",");
                load.setSatname(array[0]);
                load.setCountry(array[1]);
                load.setPurpose(array[2]);
                load.setOrbit(array[3]);
                load.setPerigee(array[4]);
                load.setApogee(array[5]);
                table.getItems().add(load);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void AboutItemClicked(Event event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/resources/AboutMessage.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();


        /*
        try {
            scene = new Scene(fxmlLoader.load(),300,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();*/

        }

    private void handle(ActionEvent event) {
        try {
            SaveButtonClicked(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

