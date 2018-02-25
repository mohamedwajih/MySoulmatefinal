/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Personnalite;
import Entities.Preference;
import Entities.Question;
import Services.SPersonnalite;
import Services.SPreference;
import Services.SQuestion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class TestPreferenceController implements Initializable {

    @FXML
    private Label labelquestion;
    @FXML
    private Button boutonok;
    @FXML
    private RadioButton reponse1;
    @FXML
    private ToggleGroup reponses;
    @FXML
    private RadioButton reponse2;
    @FXML
    private RadioButton reponse3;
    @FXML
    private Label nbquestion;
int id=2;
    SPreference sp= new SPreference();
    Preference preference= new Preference(id, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    
    
     SQuestion sq= new SQuestion();
    List<Question> listeq= new ArrayList<>(sq.TestPreference());
    int compteur=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        sp.ajouter(preference);
        reponse1.setVisible(false);
        reponse2.setVisible(false);
        reponse3.setVisible(false);
        nbquestion.setVisible(false);
        labelquestion.setText("Testons maintenant vos préférences...");
        reponse1.setToggleGroup(reponses);
        reponse2.setToggleGroup(reponses);
        reponse3.setToggleGroup(reponses);
    }    

    @FXML
    private void enregistrerreponse(ActionEvent event) throws IOException {
          reponse1.setVisible(true);
        reponse2.setVisible(true);
        reponse3.setVisible(true);
        nbquestion.setVisible(true);
        if (compteur==0)
        {
               labelquestion.setText(listeq.get(compteur).getQuestion());
       reponse1.setText(listeq.get(compteur).getReponse1());
       reponse2.setText(listeq.get(compteur).getReponse2());
       reponse3.setText(listeq.get(compteur).getReponse3());
       nbquestion.setText("Question N°"+(compteur+1)+"/40");
       
        compteur++;
        }
        else if (compteur<=39)
        {          
            
            String reponse = ((RadioButton) this.reponses.getSelectedToggle()).getText();
        System.out.println("La reponse est="+reponse);
      if(reponse.compareTo(reponse1.getText())==0)
      {
          sp.ajouterPourcentage(listeq.get(compteur-1).getTrait(),25, id);
      }
       if(reponse.compareTo(reponse2.getText())==0)
      {
          sp.ajouterPourcentage(listeq.get(compteur-1).getTrait(),50, id);
      }
        if(reponse.compareTo(reponse3.getText())==0)
      {
          sp.ajouterPourcentage(listeq.get(compteur-1).getTrait(),0, id);
      }
                labelquestion.setText(listeq.get(compteur).getQuestion());
       reponse1.setText(listeq.get(compteur).getReponse1());
       reponse2.setText(listeq.get(compteur).getReponse2());
       reponse3.setText(listeq.get(compteur).getReponse3());
       nbquestion.setText("Question N°"+(compteur+1));
   compteur++;
        }
      else if(compteur==40)
        {
            reponse1.setVisible(false);
        reponse2.setVisible(false);
        reponse3.setVisible(false);
        nbquestion.setVisible(false);
        labelquestion.setText("Vos réponses ont bien été enregistrées! ");
        boutonok.setText("Voir les résultats");
        compteur++;
        }
      else if(compteur==41)
      {
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ResultatsTest.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage currentStage= (Stage) boutonok.getScene().getWindow();
        currentStage.close();
      }
        
    }
    }
    

