/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Matching;
import Entities.Question;
import Services.SQuestion;
import Util.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import static javax.management.Query.value;


/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class QuestionsController implements Initializable {

    
    private TableColumn question;
    private TableColumn type;
    private TableColumn trait;
    private TableColumn reponse1;
    private TableColumn reponse2;
    private TableColumn reponse3;
    @FXML
    private Button ajouter;
    @FXML
    private TextField texterecherche;
    @FXML
    private Button supprimer;
    @FXML
    private Label nbquestions;
    @FXML
    private Label labelquestion;
    @FXML
    private Pane pane;
    @FXML
    private TextField question1;
    @FXML
    private TextField trait1;
    @FXML
    private TextField reponse11;
    @FXML
    private TextField reponse21;
    @FXML
    private TextField reponse31;
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
    private Button modifier;
    @FXML
    private TextField type1;
    @FXML
    private Label typelabel;
    @FXML
    private Label labelselection;
    @FXML
    private ListView<Question> lvquestions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refresh();
        pane.setVisible(false);
        labelselection.setVisible(true);

        lvquestions.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    Question q = lvquestions.getSelectionModel().getSelectedItem();
                    pane.setVisible(true);
                    question1.setText(q.getQuestion());
                    type1.setText(q.getType());
                    trait1.setText(q.getTrait());
                    reponse11.setText(q.getReponse1());
                    reponse21.setText(q.getReponse2());
                    reponse31.setText(q.getReponse3());
                    labelselection.setVisible(false);
                }
            }
        });

    }

    @FXML
    private void ajoutQuestion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ajoutQuestion.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void rechercheQuestion(ActionEvent event) {
        String champsrecherche = texterecherche.getText();
        ObservableList<Question> QuestionData = FXCollections.observableArrayList();
        SQuestion sq = new SQuestion();
        List<Question> listeq = new ArrayList<Question>();
        listeq = sq.rechercher(champsrecherche);
        QuestionData.addAll(listeq);
         lvquestions.setCellFactory(new Callback<ListView<Question>, ListCell<Question>>() {
          @Override 
          public ListCell<Question> call (ListView<Question> arg0)
              {
                  ListCell<Question> cell = new ListCell <Question>()
              {
                  @Override
                  protected void updateItem(Question q, boolean bll)
              {
                  super.updateItem(q, bll);
                  if(q!=null)
              {
                  setText("Question: "+q.getQuestion()+"\n Type: "+q.getReponse1()+" \n Trait: "+q.getTrait()+" \nReponse 1: "
                          +q.getReponse1()+"\n Reponse 2: "+q.getReponse2()+"\n Reponse 3: "+q.getReponse3());
                  Image photo1= new Image("file:/C:/wamp64/www/Img/coeur.png", 50,50,bll,bll);
                  ImageView iv1= new ImageView(photo1);
                  setGraphic(iv1);
                  
                  
                  
              }
              }
              };
                return cell;          
              }
          
      });
lvquestions.setItems(QuestionData);
        nbquestions.setText(listeq.size() + " Questions");
        pane.setVisible(false);
        labelselection.setVisible(true);

    }

    @FXML
    private void supprimerQuestion(ActionEvent event) {
        if (lvquestions.getSelectionModel().getSelectedItem() != null) {
            Question selectedQuestion = lvquestions.getSelectionModel().getSelectedItem();
            SQuestion sq = new SQuestion();
            sq.supprimer(selectedQuestion.getQuestion());
            refresh();
            bullesupprimer();

        }
    }

    private void bullesupprimer() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Question supprimée");
        alert.setHeaderText("Question supprimée");
        alert.setContentText("Votre question a été supprimée avec succès!");

        alert.showAndWait();
    }
private void bullemodifier() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Question modifiée");
        alert.setHeaderText("Question modifiée");
        alert.setContentText("Votre question a été modifiée avec succès!");

        alert.showAndWait();
    }
private void bullenonmodifiiee() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Question non modifiée");
        alert.setHeaderText("Question non modifiée");
        alert.setContentText("Votre question n'a pas été modifiée!");

        alert.showAndWait();
    }
    private void refresh() {
        SQuestion sq= new SQuestion();
         ObservableList<Question> DataQuestion= FXCollections.observableArrayList();
        DataQuestion.addAll(sq.listerAll());
        lvquestions.setCellFactory(new Callback<ListView<Question>, ListCell<Question>>() {
          @Override 
          public ListCell<Question> call (ListView<Question> arg0)
              {
                  ListCell<Question> cell = new ListCell <Question>()
              {
                  @Override
                  protected void updateItem(Question q, boolean bll)
              {
                  super.updateItem(q, bll);
                  if(q!=null)
              {
                  setText("Question: "+q.getQuestion()+"\n Type: "+q.getType()+" \n Trait: "+q.getTrait()+" \nReponse 1: "
                          +q.getReponse1()+"\n Reponse 2: "+q.getReponse2()+"\n Reponse 3: "+q.getReponse3());
                  Image photo1= new Image("file:/C:/wamp64/www/Img/coeur.png", 50,50,bll,bll);
                  ImageView iv1= new ImageView(photo1);
                  setGraphic(iv1);
                  
                  
                  
              }
              }
              };
                return cell;          
              }
          
      });
lvquestions.setItems(DataQuestion);
 nbquestions.setText(sq.listerAll().size() + " Questions");
    }

    @FXML
    private void modifierQuestion(ActionEvent event) {
        Question q= new Question();
        q.setQuestion(question1.getText());
        q.setType(type1.getText());
        q.setTrait(trait1.getText());
        q.setReponse1(reponse11.getText());
        q.setReponse2(reponse21.getText());
        q.setReponse3(reponse31.getText());
        SQuestion sq = new SQuestion();
        sq.modifier(q, lvquestions.getSelectionModel().getSelectedItem().getQuestion());
        refresh();
    }

    @FXML
    private void rechercheAjax(KeyEvent event) {
         String champsrecherche = texterecherche.getText();
        ObservableList<Question> QuestionData = FXCollections.observableArrayList();
        SQuestion sq = new SQuestion();
        List<Question> listeq = new ArrayList<Question>();
        listeq = sq.rechercher(champsrecherche);
        QuestionData.addAll(listeq);
         lvquestions.setCellFactory(new Callback<ListView<Question>, ListCell<Question>>() {
          @Override 
          public ListCell<Question> call (ListView<Question> arg0)
              {
                  ListCell<Question> cell = new ListCell <Question>()
              {
                  @Override
                  protected void updateItem(Question q, boolean bll)
              {
                  super.updateItem(q, bll);
                  if(q!=null)
              {
                  setText("Question: "+q.getQuestion()+"\n Type: "+q.getReponse1()+" \n Trait: "+q.getTrait()+" \nReponse 1: "
                          +q.getReponse1()+"\n Reponse 2: "+q.getReponse2()+"\n Reponse 3: "+q.getReponse3());
                  Image photo1= new Image("file:/C:/wamp64/www/Img/coeur.png", 50,50,bll,bll);
                  ImageView iv1= new ImageView(photo1);
                  setGraphic(iv1);
                  
                  
                  
              }
              }
              };
                return cell;          
              }
          
      });
lvquestions.setItems(QuestionData);
        nbquestions.setText(listeq.size() + " Questions");
        pane.setVisible(false);
        labelselection.setVisible(true);
    }
    

}
