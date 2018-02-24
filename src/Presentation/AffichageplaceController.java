/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Place;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.SPlace;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AffichageplaceController implements Initializable {

    @FXML
    private TableView<Place> tableplace;
    @FXML
    private TableColumn id_place;
    @FXML
    private TableColumn libelle;
    @FXML
    private TableColumn adresse;
    @FXML
    private TableColumn photo;
    @FXML
    private TableColumn type;
    @FXML
    private TextField recherche;
     @FXML
    private TextField editid;
      @FXML
    private TextField editlibelle;
       @FXML
    private TextField editphoto;
        @FXML
    private TextField editadresse;
         @FXML
    private ComboBox edittype;
          @FXML
    private ImageView icon;
          @FXML
    private GridPane pane;
          
         
        
         
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Image image = new Image("file:/C:/wamp64/www/Img/ok.png");
        icon.setImage(image);
        pane.setVisible(false);
        edittype.getItems().addAll("Sport","Art","Musique","Nature","Culturel","Fête");
        refresh();
  
     tableplace.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
           
        Place p = tableplace.getSelectionModel().getSelectedItem();
        pane.setVisible(true);
      editphoto.setVisible(false);
     editlibelle.setText(p.getLibelle());
     editadresse.setText(p.getAdr());
     editid.setVisible(false); 
     editid.setText(String.valueOf(p.getId_place()));
        }
    }
});
                
    }   
     @FXML
    private void supprimerplace(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation de la suppression");
alert.setHeaderText(null);
alert.setContentText("etes-vous sur de vouloir supprimer cette place ?");

ButtonType buttonTypeOne = new ButtonType("Oui");

ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeOne){
     if (tableplace.getSelectionModel().getSelectedItem() != null) {
        Place selectedQuestion = tableplace.getSelectionModel().getSelectedItem();
        SPlace sq= new SPlace();
        sq.supprimer(selectedQuestion);
         refresh();
         pane.setVisible(false);
    }
}   else {
    // ... user chose CANCEL or closed the dialog
    pane.setVisible(false);
}
        
       
         
         
         
     }
    
    @FXML
    private void ajouterplace(ActionEvent event) throws IOException {
        Parent  root = FXMLLoader.load(getClass().getResource("Ajouterplace.fxml"));
       Scene scene = new Scene(root);
       Stage stage = new Stage();
       stage.setScene(scene);
       stage.show();
    }
    
    
    

    

    @FXML
    private void upload(ActionEvent event){
         Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
        
        editphoto.setText("file:\\" + fil.toString());
        
        if (editphoto.getText().compareTo("")==0){
            Image image = new Image("file:/C:/wamp64/www/Img/ok.png");
        icon.setImage(image);
        }else {
            Image image = new Image("file:/C:/wamp64/www/Img/notok.png");
        icon.setImage(image);
        }
        refresh();
        
    }
    
    @FXML
    private void enregistrer(ActionEvent event){
        if (editlibelle.getText().compareTo("")==0 ||editadresse.getText().compareTo("")==0 || editphoto.getText().compareTo("")==0 || edittype.getValue().toString().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Error ADD");
alert.setHeaderText(null);
alert.setContentText("Ooops, il faut remplir tous les champs");
refresh();

alert.showAndWait();
        } else {
            
        
         Place p=new Place(Integer.parseInt(editid.getText()) ,editlibelle.getText(), editadresse.getText(), editphoto.getText(), (String) edittype.getValue());
        SPlace sp=new SPlace();
        sp.modifier(p);
        refresh();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("UPDATE");
alert.setHeaderText(null);
alert.setContentText("Place Updated");
alert.showAndWait();
pane.setVisible(false);
        }
    }
    
   public void refresh(){
        ObservableList<Place> listm = FXCollections.observableArrayList();
      SPlace sp=new SPlace();
        List<Place> l1=new ArrayList<Place>();
        l1=sp.lister();
        listm.addAll(l1);
        
        id_place.setCellValueFactory(new PropertyValueFactory<Place,String>("id_place"));
        libelle.setCellValueFactory(new PropertyValueFactory<Place,String>("libelle"));
        adresse.setCellValueFactory(new PropertyValueFactory<Place,String>("adr"));
        photo.setCellValueFactory(new PropertyValueFactory<Place,String>("photo"));
        type.setCellValueFactory(new PropertyValueFactory<Place,String>("type"));
        
        tableplace.setItems(listm);
   }

    @FXML
    private void ajax(KeyEvent event) {
         List<Place> l1=new ArrayList<Place>();
        SPlace sq= new SPlace();
        l1=sq.rechercher(recherche.getText());
        System.out.println(recherche.getText());
                 ObservableList<Place> listm = FXCollections.observableArrayList();

         listm.addAll(l1);
        
        id_place.setCellValueFactory(new PropertyValueFactory<Place,String>("id_place"));
        libelle.setCellValueFactory(new PropertyValueFactory<Place,String>("libelle"));
        adresse.setCellValueFactory(new PropertyValueFactory<Place,String>("adr"));
        photo.setCellValueFactory(new PropertyValueFactory<Place,String>("photo"));
        type.setCellValueFactory(new PropertyValueFactory<Place,String>("type"));
        tableplace.setItems(listm);
    }

    @FXML
    private void ref(MouseEvent event) {
        refresh();
    }
     
    
}
