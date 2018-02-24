/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import Entities.Message;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Services.SMessage;
import Util.amiaffichage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class EnvoyermsgController implements Initializable {

    @FXML
    private TextArea msgenvoi;
    
    @FXML
    private TextArea allmsg;
    
    @FXML
    private ImageView ami;
    
    @FXML
    private ImageView logo;
    
    @FXML
    private Button show;
    @FXML
    private Label nomami;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        allmsg.setEditable(false);
       
        amiaffichage af=new amiaffichage();
        Fos_user fs=new Fos_user();
        fs=af.getphotouserbyid(Presentation.NewFXMain.idu2);
        
        Image image = new Image(fs.getPhoto_de_profil());
        ami.setImage(image);
        nomami.setText(fs.getPrenom());
        refreshmsg();
        
    }
       
        
      private void refreshmsg(){
            List<Message> l1 = new ArrayList<>();
             SMessage sm=new SMessage();
        l1=sm.lister(Presentation.NewFXMain.idu2,Presentation.NewFXMain.idu1);
        String s="";
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).getId_emet()==Presentation.NewFXMain.idu2){
                s+="       "+l1.get(i).getText()+"\n";
            }
            
            else {
                
                s+="you: "+l1.get(i).getText()+"\n";
            }
        }
       allmsg.setText(s);
       msgenvoi.setText("");
        
      }  

    @FXML
    private void btnenvoyer(ActionEvent event) {
        if(msgenvoi.getText().compareTo("")==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Notice");
alert.setHeaderText(null);
alert.setContentText("Ecrivez quelque chose !");
alert.showAndWait();
        }else {
            Message msg=new Message(Presentation.NewFXMain.idu1,Presentation.NewFXMain.idu2, msgenvoi.getText());
        SMessage sm=new SMessage();
        sm.ajouter(msg);
         refreshmsg();
        }
  
    }
    
    
    @FXML
    private void btnshow(ActionEvent event) {
       
        allmsg.setText("");
        SMessage sm=new SMessage();
        List<Message> l1 = new ArrayList<>();
        l1=sm.lister(Presentation.NewFXMain.idu1,Presentation.NewFXMain.idu2);
        String s="";
        
        if (show.getText().compareTo("Afficher la date")==0){
   
        for (int i = 0; i < l1.size(); i++) {
            String dure;
            if (l1.get(i).getDuree()==0){
               dure="                                                   -Ajourdhui\n"; 
            } else {
                dure="                                                   -Depuis "+l1.get(i).getDuree()+" Jours\n";
            }
            
            if (l1.get(i).getId_emet()==Presentation.NewFXMain.idu2){
                s+="       "+l1.get(i).getText()+dure;
            }
            else    {
                s+="you: "+l1.get(i).getText()+dure;
            }
        }
       allmsg.setText(s);
       show.setText("Masquer la date");
        } 
        else {
       
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i).getId_emet()==Presentation.NewFXMain.idu2){
                s+="       "+l1.get(i).getText()+"\n";
            }
            else    {
                s+="you: "+l1.get(i).getText()+"\n";
            }
        }
       
       allmsg.setText(s);
            show.setText("Afficher la date");
        }
        
    }
    
}
