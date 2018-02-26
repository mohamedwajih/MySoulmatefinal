/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import Entities.Message;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JButton;
import Services.SMessage;
import Util.amiaffichage;
import javafx.beans.binding.Bindings;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class HistoriquemsgController implements Initializable {

    @FXML
    private GridPane listmsg;

    
    Fos_user fu=new Fos_user();
    amiaffichage af=new amiaffichage();
    SMessage sm=new SMessage();
    ObservableList<Fos_user> datauser=FXCollections.observableArrayList();
    ObservableList<Message> lastmsg=FXCollections.observableArrayList();
    @FXML
    private ScrollPane panemsg;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        
        List<Fos_user> l1=new ArrayList<Fos_user>();
        l1=af.listerall(NewFXMain.idu1);
        datauser.addAll(l1);
        for (int i = 0; i < datauser.size() ; i++) {
           Image pic=new Image(datauser.get(i).getPhoto_de_profil(),95,180,true,true);
           ImageView im=new ImageView(pic);
           Image pic2=new Image("file:/C:/wamp64/www/Img/Check.png",80,60,true,true);
           ImageView im2=new ImageView(pic2);
            
            ListView<Message> list=new ListView<Message>();
            System.out.println(sm.chercherlast(datauser.get(i).getId()));
            
            //////////////////////////////////////////
             list.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>(){
                   
                    @Override
                    public ListCell<Message> call(ListView<Message> arg0) {
                        
                        ListCell<Message> cell=new ListCell<Message>(){
                            @Override
                            protected void updateItem(Message p, boolean bll){
                               
                                super.updateItem(p,bll);
                                if(p!=null) {
                                    setText("...");
                                    setText(p.getText());
                                    setPrefHeight(75);
                                    
                                }
                            }
                        };
                        return cell;
                    }
                });
            //////////////////////////////////////////
            list.getItems().add(sm.chercherlast(datauser.get(i).getId()));
            list.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                try {
                    if(NewFXMain.idu1==list.getSelectionModel().getSelectedItems().get(0).getId_emet()){
                        NewFXMain.idu2=list.getSelectionModel().getSelectedItems().get(0).getId_dest();
                    }else {
                        NewFXMain.idu2=list.getSelectionModel().getSelectedItems().get(0).getId_emet();
                    }
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
                } catch (IOException ex) {
                    Logger.getLogger(HistoriquemsgController.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                
            }
            
            });
            

            
            listmsg.add(list, 1, i);
            listmsg.add(im, 0, i);
            listmsg.add(im2, 2, i);
           
            
        }
        
       

    }    

    
}
