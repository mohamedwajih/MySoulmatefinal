/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import Entities.Place;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import Services.SAmi;
import Util.amiaffichage;

/**
 * FXML Controller class
 *
 * @author asus
 */


public class ConsultermsgController implements Initializable {


    @FXML
    private Label nomami;
    @FXML
    private ListView<Fos_user> listamis;
    
  
   
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        amiaffichage af=new amiaffichage();
         List<Fos_user> l1=new ArrayList<Fos_user>();
         l1=af.listerall(NewFXMain.idu1);
         ObservableList<Fos_user> listm = FXCollections.observableArrayList();
         listm.addAll(l1);   
      
        
       
        ////////////////////////////////////////////////LISTE VIEW
        
        listamis.setCellFactory(new Callback<ListView<Fos_user>, ListCell<Fos_user>>(){
                   
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
                                    setText(p.getUsername());
                                    
                                }
                            }
                        };
                        return cell;
                    }
                });
        
        

        listamis.setItems(listm);
        //////////////////////////////////////////////////////LISTE VIEW
            
     listamis.setOnMousePressed(new EventHandler<MouseEvent>() {
    @Override 
    public void handle(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
           
        Fos_user p = listamis.getSelectionModel().getSelectedItem();
       
     nomami.setText("Avec "+p.getUsername());
        }
    }
});
         
    }
////////////////////////////////////////////////////////////////
    

    @FXML
    private void createrdv(MouseEvent event) throws IOException {
        
        if (listamis.getSelectionModel().getSelectedItem() != null) {
        Fos_user u = listamis.getSelectionModel().getSelectedItem();           
             NewFXMain.idu2=u.getId();

                    Parent  root = FXMLLoader.load(getClass().getResource("loading.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();   

                     Parent  root2 = FXMLLoader.load(getClass().getResource("RDV.fxml"));
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
    private void suppami(MouseEvent event) throws IOException {
        if (listamis.getSelectionModel().getSelectedItem() != null) {
        Fos_user u = listamis.getSelectionModel().getSelectedItem();           
        NewFXMain.idu2=u.getId();
        Fos_user fu=new Fos_user();
        amiaffichage af=new amiaffichage();
        fu=af.getphotouserbyid(NewFXMain.idu2);
            SAmi a= new SAmi();
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation de la suppression");
alert.setHeaderText(null);
alert.setContentText("etes-vous sur de vouloir supprimer "+fu.getUsername()+" de votre liste d'amis ?");

ButtonType buttonTypeOne = new ButtonType("Oui");

ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

Optional<ButtonType> result = alert.showAndWait();
if (result.get()==buttonTypeOne){
            a.deleteami(NewFXMain.idu1,NewFXMain.idu2);
            Stage stagecourant = (Stage) nomami.getScene().getWindow();
            stagecourant.close();
            System.out.println("fermé");
            
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
    }
 } 
    

