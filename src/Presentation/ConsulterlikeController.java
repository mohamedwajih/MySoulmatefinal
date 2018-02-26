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
import javafx.stage.Stage;

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
    @FXML
    private Label numtel;
    @FXML
    private Label pourcent;
           
    public void ref(){
            comp.setText(String.valueOf(compteur+1)+"/"+String.valueOf(listec.size()));
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
         System.out.println(listec.get(0));
        username.setText(listec.get(0).getPrenom()+" "+listec.get(0).getNom());
        age.setText(String.valueOf(listec.get(0).getAge())+" Ans");
        adresse.setText(listec.get(0).getAdresse());
        numtel.setText(String.valueOf(listec.get(0).getNum_tel()));
        pourcent.setText(String.valueOf(af.getpourcent(NewFXMain.idu1, listec.get(0).getId()))+" %");
     } else {
         System.out.println("wfew");
         Stage s=(Stage) age.getScene().getWindow();
            s.close();
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
        username.setText(listec.get(compteur).getPrenom()+" "+listec.get(compteur).getNom());
        age.setText(String.valueOf(listec.get(compteur).getAge())+" Ans");
        adresse.setText(listec.get(compteur).getAdresse()); 
       numtel.setText(String.valueOf(listec.get(compteur).getNum_tel()));
        pourcent.setText(String.valueOf(af.getpourcent(NewFXMain.idu1, listec.get(compteur).getId()))+" %");

    } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Vous n'avez pas des autres LIKES");
            alert.showAndWait();
            Stage s=(Stage) age.getScene().getWindow();
            s.close();
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
        username.setText(listec.get(compteur).getPrenom()+" "+listec.get(compteur).getNom());
        age.setText(String.valueOf(listec.get(compteur).getAge())+" Ans");
        adresse.setText(listec.get(compteur).getAdresse());
        numtel.setText(String.valueOf(listec.get(compteur).getNum_tel()));
        pourcent.setText(String.valueOf(af.getpourcent(NewFXMain.idu1, listec.get(compteur).getId()))+" %");

    } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Vous n'avez pas des autres LIKES");
            alert.showAndWait();
            Stage s=(Stage) age.getScene().getWindow();
            s.close();
        } 
        
    }
    
}
