/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import Util.FTTS;
import Entities.Commentaire;
import Entities.Publication;
import Entities.PublicationLike;
import Entities.Upload;
import Services.PublicationLikeService;
import Services.PublicationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author feriel
 */

public class ActualiteController implements Initializable {
 private final Image likebutton = new Image("/images/like.png");
  ImageView imageviewlike = new ImageView(likebutton);
     @FXML
    private TextArea tfpub;
@FXML
    private Label lbvideo;
    @FXML
    private Button publier;
    @FXML
    private JFXButton btvideo;

   @FXML
    private AnchorPane pass;

    @FXML
    private Button CommentaireButton;
@FXML
    private Label lbpub;
    @FXML
    private Button supprimer;
  private File file;
   String vid;

    private TableView<Commentaire> tvcommentaire;

    @FXML
    private JFXButton BTNupload;
  private Image image;
    String pic;
    @FXML
    private ScrollPane scrollPanePublication;
    static int recupid;                                                                    
     int iduser;
     int idpub;

@FXML
    private AnchorPane anchorPaneA;

            private ObservableList<Publication> data = FXCollections.observableArrayList();

        FilteredList<Publication> filteredData = new FilteredList<>(data);
    @FXML
    private JFXTextField tfRechercher;
    @FXML
    private JFXButton btnspeak;

public static Publication pp;
       Label l = new Label();

        PublicationService a = new PublicationService();
private FileChooser.ExtensionFilter extFilterJPG;
    private FileChooser.ExtensionFilter extFilterjpg;
     private Upload up;
      private File file1 = new File("");

      public ActualiteController()
      {
      iduser=2;
      }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Publication> PublicationData = FXCollections.observableArrayList();
        PublicationService sp= new PublicationService();
        List<Publication> listep= new ArrayList<Publication>();
       listep=sp.consulterPublication();
       PublicationData.addAll(listep);
       afficherlist();
 } 
    
    public void afficherlist()
    {
      
        PublicationService evt = new PublicationService();

          Image[] images;
        
            AnchorPane p = new AnchorPane();
            GridPane grid = new GridPane();
           
        
            data = FXCollections.observableArrayList();
         
            
            data.addAll(a.consulterPublication());

            int k = 0;
            grid.setHgap(1);
            grid.setVgap((data.stream().count()) + 1);
            grid.setPadding(new Insets(50, 50, 50, 50));
            for (int i = 0; i < (data.stream().count() / 1) + 1; i++) {
                for (int j = 0; j < 1; j++) {
                    if (k < data.stream().count()) {
                        String urli = data.get(k).getImagePublication();
                          Group root = new Group();
                            ImageView im= new ImageView(new Image("http://localhost/uimg/"+urli));
                                im.setFitWidth(250);

                        im.setFitHeight(200);
                          
                
  Label visit = new Label("");

visit.setOnMouseEntered(event -> {
                    visit.setUnderline(true);
                });
                visit.setOnMouseExited(event -> {
                    visit.setUnderline(false);
                });
                visit.setOnMouseClicked(event -> {
                });
                
                        root.getChildren().add(im);
                         root.setAccessibleText(Integer.valueOf(data.get(k).getId_pub()).toString());
//css for design
                        l.setStyle("-fx-font-size: 20px;"
                                + " -fx-font-weight: bold;"
                                + "-fx-text-fill: #818181;"
                                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
                                + "-fx-font-family: Arial Black;"
                        );
                        Label id = new Label(String.valueOf(data.get(k).getId_pub()));
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
                  
                        PublicationLikeService publikeservice=new PublicationLikeService();
                        int nblike=publikeservice.getNumberLike(data.get(k).getId_pub());
           Label l1 = new Label("");
          // l.setText(Integer.toString(nblike));
            Label l3 = new Label("");
           Label contenue = new Label("Publication: ");
          contenue.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
              Label contenupub = new Label(data.get(k).getContenu_pub());
                 contenupub.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
            
                 
             
                       Label datePublication = new Label("Date: ");
                     datePublication.setStyle("-fx-text-fill: #7f171f;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                   Label datePublicationPub = new Label(data.get(k).getDate_pub());
                      datePublicationPub.setStyle("-fx-text-fill:  #27313a;-fx-font-weight: bold; -fx-font: 20px Tahoma;");
                       
                         
               
                 JFXButton supprimer = new JFXButton();
                        
                              supprimer.setText("Supprimer ");
                supprimer.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
                supprimer.prefWidth(80);
                supprimer.setAccessibleHelp("Bouton");
                supprimer.setAccessibleText(Integer.toString(data.get(0).getId_pub()));
                        
                 JFXButton likeBtn = new JFXButton();
                
                likeBtn.setStyle("-fx-text-fill: hite;-fx-font: 15 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-text-color:red;");
                 likeBtn.setText(Integer.toString(nblike));
                likeBtn.setGraphic(imageviewlike);
                likeBtn.setAccessibleText(Integer.toString(data.get(0).getId_pub()));
                        
                 
                   
                      supprimer.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                a.supprimerPublication(data.get(0));
                                                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer votre publication ?");
        alert.show();
        
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/FXML/actualite.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(ActualiteController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                   
                                                   
                                                   
                                                }
                                            });
                
                                                        /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous supprimer votre publication ?");
        alert.show();*/
                      
                      
                      
                      JFXButton affCom = new JFXButton();
                            
                              affCom.setText("ajouter un commentaire ");
                affCom.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
                affCom.prefWidth(120);
                
                   affCom.setAccessibleText(Integer.valueOf(data.get(k).getId_pub()).toString());
                      affCom.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                                     recupid=Integer.valueOf(affCom.getAccessibleText());
                                                                        System.out.println(recupid+"jjjj");
      ;
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/FXML/Commentaire.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Presentation.ActualiteController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                                   
                                                   
                                                   
                                                }
                                            });
                      JFXButton affvideo = new JFXButton();
                            
                              affvideo.setText("Voir video ");
                affvideo.setStyle("-fx-text-fill: hite;-fx-font: 11 'system'; -fx-background-radius: 5px; -padding-left: 50px;-fx-background-color:#c49e56;");
                affvideo.prefWidth(120);
                
                   affvideo.setAccessibleText(Integer.valueOf(data.get(k).getId_pub()).toString());

                        
                        
                
                      affvideo.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                                      recupid=Integer.valueOf(affCom.getAccessibleText());
                                                                        System.out.println(recupid+"jjjj");
                                          pp=new Publication();
                                         pp=a.rechercherPublicationsById(recupid);
                                                    System.out.println(pp.getVideoPublication()+"kkkk");
                                        
                                                               try {
                                    
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/FXML/VideoPublication.fxml"));
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Presentation.ActualiteController.class.getName()).log(Level.SEVERE, null, ex);
                                    }  
                                                }
                                            });
                      
                 likeBtn.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                           idpub=Integer.valueOf(likeBtn.getAccessibleText());
                                         // pp=new Publication(); 
                                         //pp=a.rechercherPublicationsById(recupid);
                                            PublicationLikeService  pls=new PublicationLikeService();
                                            PublicationLike pl=new PublicationLike(iduser,idpub);  
                                            if(!pls.getUserLike(pl))
                                            {
                                            pls.ajouterLike(pl);
                                            
                                           afficherlist();
                                                }
                                            
                                                }
                                                
                                            });
                
                
                   grid.add(new VBox(root, id, l, p1), j, i);
                   vv.getChildren().addAll(l,l1,contenupub,datePublicationPub);
                     v1.getChildren().addAll(likeBtn,contenue,datePublication,supprimer ,affvideo,affCom);
       grid.add((v1), 1, i);
         grid.add((vv), 2, i);                

                   k++;
                        scrollPanePublication.setContent(grid);
                      
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
                                        // recupid = Integer.valueOf(node1.getAccessibleText());
                                        
                                        
                                     //   recupid = Integer.valueOf(node1.getAccessibleText());
                                      System.out.println(recupid+"-------------------");
                                        Pane newLoadedPaneExp = FXMLLoader.load(getClass().getResource("/FXML/actualite.fxml"));
                                        //   a.findMyannonce(recupid);
                                        pass.getChildren().clear();
                                        pass.getChildren().add(newLoadedPaneExp);
                                        //      System.out.println(recupid);
                                        //        System.out.println(annonce);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Presentation.ActualiteController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                  
                                });
                            }
                        }
                    }
                });


            }
    
    }

    @FXML
    private void AjoutAction(ActionEvent event) {
        
         boolean ajoute = true;
        Publication e = new Publication();
        if (file1.isFile()) {
                    try {
                          
             vid=up.upload(file1, "video");                                

                    } catch (IOException ex) {
                        Logger.getLogger(ActualiteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
       
        
        PublicationService sp1= new PublicationService();
        String champpub= tfpub.getText();
        Publication p= new Publication(champpub,pic,vid);
        sp1.ajouterPublication(p);
        ObservableList<Publication> PublicationData = FXCollections.observableArrayList();
        PublicationService sp= new PublicationService();
        List<Publication> listep= new ArrayList<Publication>();
       listep=sp.consulterPublication();
       PublicationData.addAll(listep);
           String pub = tfpub.getText();
       //tts
           FTTS freeTTSPub = new FTTS(pub);
                freeTTSPub.speak();
//notif
          TrayNotification tray = new TrayNotification("Notification !", "Publication ajoutée avec succée", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(6));
       afficherlist();
     
    
       
    }

    @FXML
    private void commenter(ActionEvent event) throws IOException
    {

    }

    @FXML
    private void supprimer(ActionEvent event) {

    }
    private void Modifier(ActionEvent event) {
        
    }

    private void ajoutCommentaire(ActionEvent event) {
 
    }
    private void supcom(ActionEvent event) {

 
}

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void speak(ActionEvent event) {
  
          String recherchetxt = tfRechercher.getText();

        FTTS freeTTS = new FTTS(recherchetxt);
        freeTTS.speak();
    }
  @FXML
    void upload(ActionEvent event) throws IOException {
        
  FileChooser fileChooser = new FileChooser();
            file= fileChooser.showOpenDialog(null);
             FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

            //pic=(file.toURI().toString());
            pic=new Upload().upload(file,"img");
            System.out.println(pic);
            image= new Image("http://localhost/uimguimg/"+pic);
            lbpub.setText(pic);
    }
     @FXML
    void uploadvideo(ActionEvent event) {
FileInputStream input = null;
            FileChooser fileChooser = new FileChooser();
            //Set extension filter
            extFilterJPG
                    = new FileChooser.ExtensionFilter("mp4 files (*.MP4)", "*.mp4");
            extFilterjpg
                    = new FileChooser.ExtensionFilter("mkv files (*.MKV)", "*.mkv");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg);
            up = new Upload();
            file1 = fileChooser.showOpenDialog(null);

            lbvideo.setText(file1.getPath());
            
            try {
                input = new FileInputStream(file1.getPath());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ActualiteController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }


   
