/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Ami;
import Entities.Fos_user;
import Entities.Matching;
import Entities.Question;
import Services.SMatching;
import Services.SQuestion;
import Services.Fos_userServices;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import Services.SAmi;
import Util.amiaffichage;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class MesMatchsController implements Initializable {

    @FXML
    private Label labelmatch;
    @FXML
    private ImageView photo;
    @FXML
    private Label nom;
    @FXML
    private Label age;
    @FXML
    private Label adresse;
    @FXML
    private Label pourcent;
    int id = NewFXMain.idu1;
    @FXML
    private Label prenom;
   
    SMatching sm = new SMatching();
    Fos_userServices su = new Fos_userServices();
 
    List<Matching> matchs = new ArrayList<>(sm.listerOne(id));
    int compteur=0;
    @FXML
    private ImageView like;
    Image poucegris = new Image("file:/C:/wamp64/www/Img/poucegris.png");
    Image poucerouge = new Image("file:/C:/wamp64/www/Img/poucerouge.png");
    Image coeur = new Image("file:/C:/wamp64/www/Img/coeur.png");
    Image imageparchemin = new Image("file:/C:/wamp64/www/Img/parchemin.gif");
    Image droite = new Image("file:/C:/wamp64/www/Img/droite.png");
    Image gauche = new Image("file:/C:/wamp64/www/Img/gauche.png");
    @FXML
    private Label labelselection;
    @FXML
    private ImageView parchemin;
    @FXML
    private ImageView imagecoeur;
    @FXML
    private ImageView flechegauche;
    @FXML
    private ImageView flechedroite;

    /**
     * Initializes the controller class.
     */
    public void verif(){
        SAmi sa=new SAmi();
        if(sa.chercherami(id,matchs.get(compteur).getIdUser2())==false){
             like.setImage(poucegris);
        }else {
                    like.setImage(poucerouge);
        }
           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Path="+su.getUserById(matchs.get(compteur).getIdUser2()).getPhoto_de_profil());
        nom.setText(su.getUserById(matchs.get(0).getIdUser2()).getNom());
        prenom.setText(su.getUserById(matchs.get(0).getIdUser2()).getPrenom());
        age.setText(su.getUserById(matchs.get(0).getIdUser2()).getAge() + " ans");
        adresse.setText(su.getUserById(matchs.get(0).getIdUser2()).getAdresse());
       Image i = new Image(su.getUserById(matchs.get(0).getIdUser2()).getPhoto_de_profil());
       photo.setImage(i);
pourcent.setText(matchs.get(0).getPourcentage()+" %");
verif();
flechegauche.setVisible(false);
flechedroite.setVisible(true);
        System.out.println("compteur="+compteur);
    }

@FXML
    private void Next(MouseEvent event) {
        System.out.println("Clicked!");
      System.out.println("compteur next entree="+compteur);
       
        if(compteur<matchs.size()-1){compteur++;
         nom.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getNom());
        prenom.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getPrenom());
        age.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getAge() + " ans");
        adresse.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getAdresse());
            
        Image i = new Image(su.getUserById(matchs.get(compteur).getIdUser2()).getPhoto_de_profil());
        photo.setImage(i);
        pourcent.setText(matchs.get(compteur).getPourcentage()+" %");
          flechedroite.setVisible(true);
          flechegauche.setVisible(true);
            System.out.println("boucle1 next");
         if(compteur==matchs.size()-1)  {
            flechedroite.setVisible(false);
            System.out.println("boucle2 next");
        }
        }
       
        System.out.println("compteur next ="+compteur);
      
        verif();
    }



    @FXML
    private void Previous(MouseEvent event) {
         System.out.println("Clicked!");
       System.out.println("compteur prev="+compteur);
       
        if(compteur<matchs.size()){ compteur--;
         nom.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getNom());
        prenom.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getPrenom());
        age.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getAge() + " ans");
        adresse.setText(su.getUserById(matchs.get(compteur).getIdUser2()).getAdresse());
        Image i = new Image(su.getUserById(matchs.get(compteur).getIdUser2()).getPhoto_de_profil());
        photo.setImage(i);
        pourcent.setText(matchs.get(compteur).getPourcentage()+" %");
      flechedroite.setVisible(true);
            System.out.println("boucle1 prev");
             if(compteur==0)  {
            flechegauche.setVisible(false);
            System.out.println("boucle2 next");
        }
        }
        verif();
    }

    @FXML
    private void liker(MouseEvent event) {
        SAmi sa=new SAmi();
        if(like.getImage()==poucegris){
        Ami ami=new Ami(id, matchs.get(compteur).getIdUser2());
        System.out.println(ami);
        sa.ajouter(ami);
        verif();
        } else {
            System.out.println("deja ami");

        }
        
        
    }
}
