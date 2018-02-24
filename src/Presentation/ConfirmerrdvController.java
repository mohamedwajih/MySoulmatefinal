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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Services.SPlace;
import Services.SRdv;
import Util.amiaffichage;
import Util.sendsms;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConfirmerrdvController implements Initializable {

    @FXML
    private ImageView pdp;
    @FXML
    private TextField date;
    @FXML
    private TextField time;
    @FXML
    private TextField lieu;
    @FXML
    private ImageView imagelieu;
    @FXML
    private Label nomami;
    
        amiaffichage af=new amiaffichage();
        Fos_user fs=new  Fos_user();
        SRdv sr=new SRdv();
                Rdv r=new Rdv();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       
        fs=af.getphotouserbyid(NewFXMain.idu2);
        Image image = new Image(fs.getPhoto_de_profil());
        pdp.setImage(image);
        nomami.setText(fs.getPrenom());
        r=sr.listerOne(NewFXMain.idu2,NewFXMain.idu1);
        
        date.setText(r.getDate());
        Place p =new Place();
        SPlace sp=new SPlace();
        p=sp.chercher(r.getLieu());
        lieu.setText(p.getLibelle());
        Image im=new Image(p.getPhoto());
        imagelieu.setImage(im);
        String s=date.getText().substring(0, 19);
       // System.out.println("new date"+s);
        r.setDate2(s);
       // System.out.println("new rdv="+r);
    }    

    @FXML
    private void confirmer(MouseEvent event) {
                 String s=date.getText().substring(0, 19);
                 r.setDate2(s);
                 System.out.println("confirmé  "+r);
                 sr.confirmerrdv(r);
                  Fos_user fu=new Fos_user();
            amiaffichage af=new amiaffichage();
            fu=af.getphotouserbyid(NewFXMain.idu2);
           String msg=fu.getPrenom()+" a accepté votre demande de rendez-vous, Consultez votre compte Mysoulmate pour plus d'information.";
           sendsms sm=new sendsms();
           //sm.send(fu.getNum_tel(),msg);
            Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Rendez-vous confirmé !");
            alert.showAndWait();
        Stage stage=(Stage) lieu.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void supprimer(MouseEvent event) {
        String s=date.getText().substring(0, 19);
        r.setDate2(s);
        System.out.println("refusé =    "+r);
        sr.supprimer(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Rendez-vous refusé !");
            alert.showAndWait();
         Stage stage=(Stage) lieu.getScene().getWindow();
        stage.close();
    }
    
}
