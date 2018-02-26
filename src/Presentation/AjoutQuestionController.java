/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Question;
import Services.SQuestion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class AjoutQuestionController implements Initializable {

    @FXML
    private Label ajouterunequestionlabel;
    @FXML
    private TextField question;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ChoiceBox<String> trait;
    @FXML
    private TextField reponse1;
    @FXML
    private TextField reponse2;
    @FXML
    private TextField reponse3;
    @FXML
    private Label questionlabel;
    @FXML
    private Label traitlabel;
    @FXML
    private Label reponse1label;
    @FXML
    private Label reponse2label;
    @FXML
    private Label reponse3label;
    @FXML
    private Button ajouter;
    @FXML
    private Label typelabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    

    @FXML
    private void ajouterQuestion(ActionEvent event) {
        SQuestion sq= new SQuestion();
        Question q= new Question();
        q.setQuestion(question.getText());
         q.setType(type.getValue());
        q.setTrait(trait.getValue());
        System.out.println(trait.getValue());
        q.setReponse1(reponse1.getText());
        q.setReponse2(reponse2.getText());
        q.setReponse3(reponse3.getText());
       if(sq.ajouter(q)){
        bulleajouttrue();
          question.setText("");
       
        reponse1.setText("");
        reponse2.setText("");
        reponse3.setText("");
       }
       else
           bulleajoutfalse();
      
        
    }
    private void bulleajouttrue() {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Question ajoutée");
        alert.setHeaderText("Question ajoutée");
        alert.setContentText("Votre question a été ajoutée avec succès!");
 
        alert.showAndWait();
    }
     private void bulleajoutfalse() {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur ajout");
        alert.setHeaderText("Erreur ajout");
        alert.setContentText("Votre question n'a pas été ajoutée veuillez vérifier vos champs!");
 
        alert.showAndWait();
    }
    
}
