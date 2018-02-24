/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import Services.SAmi;
import Util.amiaffichage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConsulterlikeController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label age;
    @FXML
    private Label adresse;
    @FXML
    private ImageView pdp;
    @FXML
    private Label comp;
    
    /**
     * Initializes the controller class.
     */
    
    
    List<Fos_user> listec =new ArrayList<Fos_user>();
    amiaffichage af= new amiaffichage();
    SAmi sa=new SAmi();
           int compteur=0; 
           
    public void ref(){
            comp.setText("Vous avez"+String.valueOf(listec.size()-compteur)+" Likes encore ...");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    listec.addAll(af.listerconfirmer(NewFXMain.idu1));
     if (listec.size()!=0){
         ref();
        System.out.println("size="+listec.size());
        Image image=new Image(listec.get(0).getPhoto_de_profil());
        pdp.setImage(image);
        username.setText(listec.get(0).getUsername());
        age.setText(String.valueOf(listec.get(0).getAge()));
        adresse.setText(listec.get(0).getAdresse());
     } else {
         System.out.println("wfew");
     }
    }    

    @FXML
    private void like(MouseEvent event) {
       
        
       if(listec.size()>compteur) {
           
       sa.confirmerami(NewFXMain.idu1,listec.get(compteur).getId());
       compteur++;
             ref();  
       }
        if(listec.size()!=compteur){
        Image image=new Image(listec.get(compteur).getPhoto_de_profil());
        pdp.setImage(image);
        username.setText(listec.get(compteur).getUsername());
        age.setText(String.valueOf(listec.get(compteur).getAge()));
        adresse.setText(listec.get(compteur).getAdresse()); 
        
    } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Vous n'avez pas des autres LIKES");
            alert.showAndWait();
        } 
    }

    @FXML
    private void deslike(MouseEvent event) {
         if(listec.size()>compteur) {
           
       sa.deleteami(NewFXMain.idu1, listec.get(compteur).getId());
       compteur++;
            ref();   
       }
        if(listec.size()!=compteur){
        Image image=new Image(listec.get(compteur).getPhoto_de_profil());
        pdp.setImage(image);
        username.setText(listec.get(compteur).getUsername());
        age.setText(String.valueOf(listec.get(compteur).getAge()));
        adresse.setText(listec.get(compteur).getAdresse());  
    } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Vous n'avez pas des autres LIKES");
            alert.showAndWait();
        } 
        
    }
    
}
