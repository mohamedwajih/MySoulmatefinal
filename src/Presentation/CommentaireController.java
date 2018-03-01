/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Util.FTTS;
import Entities.Commentaire;
import Entities.Publication;
import static Presentation.ActualiteController.pp;
import Services.CommentaireService;
import Services.PublicationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author feriel
 */
public class CommentaireController implements Initializable {

    @FXML
    private AnchorPane pass;
    @FXML
    private AnchorPane anchorPaneA;
    @FXML
    private ScrollPane scrollPaneCommentaire;
            private ObservableList<Commentaire> data = FXCollections.observableArrayList();
        CommentaireService a = new CommentaireService();
       Label l = new Label();
             private File file1 = new File("");
int idpub ;
static int recupid;
    @FXML
    private JFXTextArea TACommentaire;
    @FXML
    private JFXButton btnCommentaire;
    @FXML
    private JFXButton btnrechercher;
            Publication pub = ActualiteController.pp;
    @FXML
    private JFXButton commenter;
    @FXML
    private JFXButton btnback;


    /**
     * Initializes the controller class.
     */
            
    public CommentaireController() {
        idpub = ActualiteController.recupid;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Commentaire> PublicationData = FXCollections.observableArrayList();
        CommentaireService sp= new CommentaireService();
        List<Commentaire> listep= new ArrayList<Commentaire>();
        Commentaire c =new Commentaire();
        System.out.println("    iddd"+idpub);
        c.setId_pub(recupid);
        listep=sp.consulterComCom(c);
        PublicationData.addAll(listep); 
        afficherlist();
        System.out.println("ghhhhh"+listep);
    }

    public void afficherlist()
    {
      
        CommentaireService evt = new CommentaireService();

        
            AnchorPane p = new AnchorPane();
            GridPane grid = new GridPane();
           
        
            data = FXCollections.observableArrayList();
         
            
            data.addAll(a.consulterCommentaireTest());

            int k = 0;
            grid.setHgap(1);
            grid.setVgap((data.stream().count()) + 1);
            grid.setPadding(new Insets(50, 50, 50, 50));
            for (int i = 0; i < (data.stream().count() / 1) + 1; i++) {
                for (int j = 0; j < 1; j++) {
                    if (k < data.stream().count()) {
                       
                          
                
  Label visit = new Label("");

visit.setOnMouseEntered(event -> {
                    visit.setUnderline(true);
                });
                visit.setOnMouseExited(event -> {
                    visit.setUnderline(false);
                });
                visit.setOnMouseClicked(event -> {
                });
                
                     
                        l.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        Label id = new Label(String.valueOf(data.get(k).getId_com()));
                        id.setVisible(false);
                        id.setAccessibleText("id");
                        Pane p1 = new Pane();
                        p1.setStyle("-fx-background-color: white;"
                                + "-fx-background-radius: 10px;"
                                + "-fx-border-color: black;"
                                + "-fx-border-radius:10px;"
                                + "-fx-opacity: 0.6;");
                        Label l2 = new Label("clik me !!!");
                        l2.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        p1.setVisible(false);
                        l2.setVisible(true);
                        l.setOnMouseEntered((MouseEvent event) -> {
                        p1.setVisible(true);

                        });
                        l.setOnMouseExited((MouseEvent event) -> {
                            p1.setVisible(false);
                        });
                        p1.getChildren().add(l2);
                        l.setAlignment(Pos.CENTER);
                
VBox vv = new VBox();
          vv.setSpacing(5);

          
          
         VBox v1 = new VBox();
          v1.setSpacing(5);
          
                  Label l = new Label("");
           Label l1 = new Label("");
           Label contenue = new Label("Commentaire ");
          contenue.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 10px Tahoma;");
              Label contenupub = new Label(data.get(k).getContenu_com());
                 contenupub.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
            
                 
             
                       Label datePublication = new Label("Date ");
                     datePublication.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 10px Tahoma;");
                   Label datePublicationPub = new Label(data.get(k).getDate_com());
                      datePublicationPub.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                       
                         
   
                      
                         
               
                 JFXButton supprimer = new JFXButton();
                        
                              supprimer.setText("Supprimer ");
                supprimer.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
         //       supprimer.setStyle("-fx-text-fill:#077F6F;-fx-font-weight: bold; -fx-font: 15px Tahoma;-fx-background-color: #C4CDCC;");
                supprimer.prefWidth(80);
                supprimer.setAccessibleHelp("Bouton");
                supprimer.setAccessibleText(Integer.toString(data.get(0).getId_com()));
                        
                                                                           // System.out.println("    lololo"+data.get(0).toString());

                        
                
                      supprimer.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                a.supprimerCommentaire(data.get(0));
                                                   
                                                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer votre publication ?");
        alert.show();
        
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/FXML/Commentaire.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                   
                                                   
                                                   
                                                }
                                            });
                
                      
                      
                      
                      
                      
                      JFXButton affCom = new JFXButton();
                            
                             
                
                   grid.add(new VBox( id, l, p1), j, i);
                   vv.getChildren().addAll(l,contenupub,datePublicationPub,affCom);
                     v1.getChildren().addAll(l1,contenue,datePublication,supprimer );
       grid.add((v1), 1, i);
         grid.add((vv), 2, i);                

                   k++;
                        scrollPaneCommentaire.setContent(grid);
                      
                    }

                }

                for (Node node : grid.getChildren()) {
                    if (node instanceof VBox) {
                        for (Node node1 : ((VBox) node).getChildren()) {
                            if (node1 instanceof ImageView) {
                                node1.setOnMouseClicked((MouseEvent E) -> {
                                });
                            }

                        }
                    }
                }

                grid.setOnMouseClicked((MouseEvent E) -> {
                    for (Node node : grid.getChildren()) {

                        for (Node node1 : ((VBox) node).getChildren()) {
                            if (node1 instanceof Group) {
                                node1.setOnMouseClicked((MouseEvent E1) -> {

                                    try {
                                         recupid = Integer.valueOf(node1.getAccessibleText());
                                        
                                        
                                     //   recupid = Integer.valueOf(node1.getAccessibleText());
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/Presentation/Commentaire.fxml"));
                                        //   a.findMyannonce(recupid);
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                        
  
                                    } catch (IOException ex) {
                                        Logger.getLogger(Presentation.CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                  
                                });
                            }
                        }
                    }
                });


            }
    
    }    

    @FXML
    private void AjouterCommentaire(ActionEvent event) {
             boolean ajoute = true;
        Commentaire e = new Commentaire();

        CommentaireService sp1= new CommentaireService();
        String champpub= TACommentaire.getText();
        Commentaire p= new Commentaire(champpub);
        p.setId_pub(idpub);
        sp1.ajouterCommentaire(p);
        ObservableList<Commentaire> CommentaireData = FXCollections.observableArrayList();
        CommentaireService sp= new CommentaireService();
        List<Commentaire> listeCom= new ArrayList<Commentaire>();
       listeCom=sp.consulterCommentaireTest();
       CommentaireData.addAll(listeCom);
           String pu = TACommentaire.getText();
        FTTS freeTTSPub = new FTTS(pu);
                freeTTSPub.speak();

          TrayNotification tray = new TrayNotification("Notification !", "Publication ajoutée avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(6));
       afficherlist();
     
    
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
              try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/FXML/actualite.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(CommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                   
                                                   
                                                   
                                                }
                                            }
    
    

