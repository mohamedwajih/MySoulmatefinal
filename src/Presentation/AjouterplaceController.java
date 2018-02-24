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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.SPlace;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterplaceController implements Initializable {

    @FXML
    private TextField p_libelle;
    @FXML
    private TextField p_adresse;
    
    @FXML
    private ImageView icon;
    
    @FXML
    private TextField file;
    
    @FXML
    private ComboBox<String> type;
    @FXML
    private ProgressBar progress = new ProgressBar();;
    @FXML
    private Button ajoutbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image("file:/C:/wamp64/www/Img/ok.png");
        icon.setImage(image);
        type.getItems().addAll("Sport","Art","Musique","Nature","Culturel","Fête");
        file.setEditable(false);
     
                 
           
      
    }    

    @FXML
    private void choose(ActionEvent event) {
        Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
        Image image = new Image("file:/C:/wamp64/www/Img/notok.png");
        icon.setImage(image);
        file.setText("file:\\" + fil.toString());
 

    }
    @FXML
    private void ajouterplace(ActionEvent event) throws IOException {
        if (p_libelle.getText().compareTo("")==0 ||p_adresse.getText().compareTo("")==0 || file.getText().compareTo("")==0 || type.getValue().toString().compareTo("")==0){
            Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ADD");
alert.setHeaderText(null);
alert.setContentText("Ooops, il faut remplir tous les champs");

alert.showAndWait();
        } else {
            
        
        Place p=new Place(p_libelle.getText(), p_adresse.getText(), file.getText(),type.getValue());
        SPlace sp=new SPlace();
        sp.ajouter(p);
        p_libelle.setText("");
        p_adresse.setText("");
        file.setText("");
        type.setValue("");
         Image image = new Image("file:/C:/wamp64/www/Img/ok.png");
        icon.setImage(image);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes");
           alert.setHeaderText(null);
         alert.setContentText("New Place Addes");
         alert.showAndWait();
         Stage stage=(Stage) p_libelle.getScene().getWindow();
         stage.close();
        }
       
    }
private void progressbar(){
     double x=0;
        if (p_libelle.getText().compareTo("")!=0){
            x+=0.25;
            progress.setProgress(x);
        } else {
            x-=0.25;
            progress.setProgress(x);
        }
        
         if (p_adresse.getText().compareTo("")!=0){
            x+=0.25;
            progress.setProgress(x);
        } else {
            x-=0.25;
            progress.setProgress(x);
        }
                
         if (file.getText().compareTo("")!=0){
            x+=0.25;
            progress.setProgress(x);
        } else {
            x-=0.25;
            progress.setProgress(x);
        }
         
          if (type.getValue().compareTo("")!=0){
            x+=0.25;
            progress.setProgress(x);
        } else {
            x-=0.25;
            progress.setProgress(x);
        }
        
}
    @FXML
    private void prog1(MouseEvent event) {
       progressbar();
    }

    @FXML
    private void prog2(MouseEvent event) {
        progressbar();
    }

    @FXML
    private void prog3(MouseEvent event) {
        progressbar();
    }

    @FXML
    private void prog4(MouseEvent event) {
        progressbar();
    }

   

   
}
