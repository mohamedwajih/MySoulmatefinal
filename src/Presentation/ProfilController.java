/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import Services.SAmi;
import Services.SMessage;
import Services.SRdv;
import Util.amiaffichage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ProfilController implements Initializable {

    @FXML
    private TextField buttonRecherche;
   
    @FXML
    private AnchorPane buttonPhoto;
    @FXML
       private Label txtPrenomNom;
    @FXML
    private ListView<Fos_user> listeamis;
    @FXML
    private ImageView newlike;
    @FXML
    private ImageView newmsg;
    @FXML
    private ImageView newrdv;
SMessage sm= new SMessage();
SRdv sr=new SRdv();
amiaffichage af=new amiaffichage();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            // TODO
        
        
         List<Fos_user> l1=new ArrayList<Fos_user>();
         l1=af.listerall(Presentation.NewFXMain.idu1);
         ObservableList<Fos_user> listm = FXCollections.observableArrayList();
         listm.addAll(l1); 
         
        if(!sm.verif(Presentation.NewFXMain.idu1))
            newmsg.setVisible(false);
      
         if(af.listerconfirmer(Presentation.NewFXMain.idu1).size()==0)
             newlike.setVisible(false);
        
          if (sr.listid(Presentation.NewFXMain.idu1).size()==0)
              newrdv.setVisible(false);
       
        ////////////////////////////////////////////////LISTE VIEW
        
        listeamis.setCellFactory(new Callback<ListView<Fos_user>, ListCell<Fos_user>>(){
                   
                    @Override
                    public ListCell<Fos_user> call(ListView<Fos_user> arg0) {
                        
                        ListCell<Fos_user> cell=new ListCell<Fos_user>(){
                            @Override
                            protected void updateItem(Fos_user p, boolean bll){
                               
                                super.updateItem(p,bll);
                                if(p!=null) {
                                    
                                    Image pic=new Image(p.getPhoto_de_profil(),80,95, bll, bll);
                                    
                                    ImageView imageview=new ImageView(pic);
                                    setGraphic(imageview);
                                    setText("    "+p.getPrenom());
                                    
                                }
                            }
                        };
                        return cell;
                    }
                });
        
        

        listeamis.setItems(listm);
        //////////////////////////////////////////////////////LISTE VIEW
            
     listeamis.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
           
        Fos_user p = listeamis.getSelectionModel().getSelectedItem();
       
     
        }
    }
});
         
    }
////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////
    @FXML
    private void listermsg(MouseEvent event) throws IOException {
        
        if(!sm.verif(Presentation.NewFXMain.idu1)){
             Parent  root = FXMLLoader.load(getClass().getResource("loading.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();   

                     Parent  root2 = FXMLLoader.load(getClass().getResource("Historiquemsg.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setScene(scene2);
                     
                     
         Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {    
                try {
                     Thread.sleep(800);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
               stage.hide();
               stage2.show(); 
            }
        });
        new Thread(sleeper).start();

       
        } else {
            newmsg.setVisible(false);
            System.out.println(sm.listeid(Presentation.NewFXMain.idu1));
            List<Integer> listid=new ArrayList<Integer>();
            listid=sm.listeid(Presentation.NewFXMain.idu1);
            for (int i = 0; i < listid.size() ; i++) {
                Presentation.NewFXMain.idu2=listid.get(i);
                Parent  root2 = FXMLLoader.load(getClass().getResource("Envoyermsg.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setScene(scene2);
                     stage2.show();
            }
            sm.lu(Presentation.NewFXMain.idu1);
        }
        
    }
///////////////////////////////////////////////////////////////////////
    @FXML
    private void listerrdv(MouseEvent event) throws IOException {
        if (sr.listid(Presentation.NewFXMain.idu1).size()!=0){
        newrdv.setVisible(false);
        System.out.println(sr.listid(Presentation.NewFXMain.idu1));
        List<Integer> lid=new ArrayList<Integer>();
        lid=sr.listid(Presentation.NewFXMain.idu1);
        for (int i = 0; i < lid.size() ; i++) {
            Presentation.NewFXMain.idu2=lid.get(i);
                     Parent  root2 = FXMLLoader.load(getClass().getResource("Confirmerrdv.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setScene(scene2);
                     stage2.show();
        }
                  }
                  else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); 
            alert.setTitle("Notice");
            alert.setHeaderText(null); 
            alert.setContentText("Vous n'avez pas des propositions des rendez_vous !");
            alert.showAndWait(); 
                    Parent  root = FXMLLoader.load(getClass().getResource("Listerdv.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show(); 
                  }
    }
////////////////////////////////////////////////////////////////////////////
    @FXML
    private void listerlike(MouseEvent event) throws IOException {
        newlike.setVisible(false);
        amiaffichage af=new amiaffichage();
        if(af.listerconfirmer(Presentation.NewFXMain.idu1).size()!=0){
        
        Parent  root = FXMLLoader.load(getClass().getResource("loading.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();   

                     Parent  root2 = FXMLLoader.load(getClass().getResource("Consulterlike.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setScene(scene2);
                     
                     
         Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {    
                try {
                     Thread.sleep(800);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
               stage.hide();
               stage2.show(); 
            }
        });
        new Thread(sleeper).start();

        
        }else {
     Parent  root = FXMLLoader.load(getClass().getResource("loading.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();   

                     Parent  root2 = FXMLLoader.load(getClass().getResource("Consultermsg.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setScene(scene2);
                     
                     
         Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {    
                try {
                     Thread.sleep(800);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
               stage.hide();
               stage2.show(); 
            }
        });
        new Thread(sleeper).start();
}
    }
/////////////////////////////////////////////////////////////////////////////
    @FXML
    private void envoyermsg(MouseEvent event) throws IOException {
        if (listeamis.getSelectionModel().getSelectedItem() != null) {
        Fos_user u = listeamis.getSelectionModel().getSelectedItem();           
             Presentation.NewFXMain.idu2=u.getId();

                    Parent  root = FXMLLoader.load(getClass().getResource("loading.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();   

                     Parent  root2 = FXMLLoader.load(getClass().getResource("Envoyermsg.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setScene(scene2);
                     
                     
         Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {    
                try {
                     Thread.sleep(800);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
               stage.hide();
               stage2.show(); 
            }
        });
        new Thread(sleeper).start();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR); 
            alert.setTitle("Error");
            alert.setHeaderText(null); 
            alert.setContentText("Il faut choisir une personne de la liste !");
            alert.showAndWait();
        }
       
    }

    @FXML
    private void match(MouseEvent event) throws IOException {
        
                    Parent  root = FXMLLoader.load(getClass().getResource("loading.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();   

                     Parent  root2 = FXMLLoader.load(getClass().getResource("MesMatchs.fxml"));
                     Scene scene2 = new Scene(root2);
                     Stage stage2 = new Stage();
                     stage2.setScene(scene2);
                     
                     
         Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {    
                try {
                     Thread.sleep(800);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
               stage.hide();
               stage2.show(); 
            }
        });
        new Thread(sleeper).start();

        } 
    }

   
    

