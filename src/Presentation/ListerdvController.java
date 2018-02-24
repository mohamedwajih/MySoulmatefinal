/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import Entities.Place;
import Entities.Rdv;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Services.SPlace;
import Services.SRdv;
import Util.amiaffichage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ListerdvController implements Initializable {

    @FXML
    private ImageView pdp;
    @FXML
    private ImageView lieu;
    @FXML
    private Label place;
    @FXML
    private Label date;
    @FXML
    private Label nom;
    @FXML
    private Label age;
    
    SRdv sr=new SRdv();
    amiaffichage af=new amiaffichage();
    SPlace sp = new SPlace();
    Place p=new Place();
    Fos_user fu=new Fos_user();
    List<Rdv> l1=new ArrayList<Rdv>();
    int compteur=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
        l1.addAll(sr.listerAll());
         if (l1.size()!=0){
             if(l1.get(0).getId1()==NewFXMain.idu1){
                 fu=af.getphotouserbyid(l1.get(0).getId2());
             } else {
                 fu=af.getphotouserbyid(l1.get(0).getId1());
             }
                 
         p=sp.chercher(l1.get(0).getLieu());
        Image imagel=new Image(p.getPhoto());
        Image image=new Image(fu.getPhoto_de_profil());
        pdp.setImage(image);
        lieu.setImage(imagel);
        place.setText(p.getAdr()+","+p.getLibelle());
        nom.setText(fu.getPrenom());
        age.setText(String.valueOf(fu.getAge())+" Ans");
        date.setText(l1.get(0).getDate());
     } else {
         System.out.println("wfew");
     }
    }    
        

    @FXML
    private void next(ActionEvent event) {
        if( compteur==l1.size()-1) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Reculez !");
            alert.showAndWait();
        } else {
            
        
        if(l1.size()>compteur) {
           
       
       compteur++;
              
       }
        if(l1.size()!=compteur){
       if(l1.get(compteur).getId1()==NewFXMain.idu1){
                 fu=af.getphotouserbyid(l1.get(compteur).getId2());
             } else {
                 fu=af.getphotouserbyid(l1.get(compteur).getId1());
             }
                 
         p=sp.chercher(l1.get(compteur).getLieu());
        Image imagel=new Image(p.getPhoto());
        Image image=new Image(fu.getPhoto_de_profil());
        pdp.setImage(image);
        lieu.setImage(imagel);
        place.setText(p.getAdr());
        nom.setText(fu.getPrenom());
        age.setText(String.valueOf(fu.getAge()));
        date.setText(l1.get(compteur).getDate());
        
    } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Vous n'avez pas des autres rendez-vous !");
            alert.showAndWait();
        }
    }}

    @FXML
    private void previous(ActionEvent event) {
        if( compteur==0) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Avancez !");
            alert.showAndWait();
        } else {
        
        
        
        if(l1.size()>compteur) {
           
       
       compteur--;
              
       }
        if(l1.size()!=compteur){
       if(l1.get(compteur).getId1()==NewFXMain.idu1){
                 fu=af.getphotouserbyid(l1.get(compteur).getId2());
             } else {
                 fu=af.getphotouserbyid(l1.get(compteur).getId1());
             }
                 
         p=sp.chercher(l1.get(compteur).getLieu());
        Image imagel=new Image(p.getPhoto());
        Image image=new Image(fu.getPhoto_de_profil());
        pdp.setImage(image);
        lieu.setImage(imagel);
        place.setText(p.getAdr());
        nom.setText(fu.getPrenom());
        age.setText(String.valueOf(fu.getAge()));
        date.setText(l1.get(compteur).getDate());
        
    } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Vous n'avez pas des autres rendez-vous !");
            alert.showAndWait();
        }
    }
    }   
}
