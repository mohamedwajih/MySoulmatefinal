/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Event;
import Entities.Notification;
import Services.EventService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;


public class ConsulteListEventRespnsableController implements Initializable {
    @FXML
    private Button Ajouter;
    @FXML
    private ListView<Event> list;
    @FXML
    private Button archive;
    
     Image IMAGE_TWITTER ;
    @FXML
    private Button pass;
    @FXML
    private ImageView logo;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image im= new Image("css/l.png");
        logo.setImage(im);
         
        EventService es = new EventService();  
        ArrayList<Event> lA= es.getArchive(1);
        if(lA.size()==0){
         archive.setDisable(true);
        
        }
         ArrayList<Event> listE=es.getListEventsUser(1);
         Collections.sort(listE, new Comparator<Event>() {
    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDate_Event().compareTo(
                o2.getDate_Event());
    }
});
       ObservableList<Event> items =FXCollections.observableArrayList(listE) ;
        list.setItems(items);
         list.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {

            @Override
            public ListCell<Event> call(ListView<Event> param) {
                return new ListCell<Event>() {
                    private final ImageView imageView = new ImageView();
                    @Override
                    public void updateItem(Event name, boolean empty) {
                        super.updateItem(name, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                           IMAGE_TWITTER = new Image(name.getImage());
                            imageView.setImage(IMAGE_TWITTER);
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            setText(name.toString());
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });
    }    

    @FXML
    private void getAjoutInterface(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterEvennement.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet1.css");
            Stage   primaryStage = new Stage();
            primaryStage.setTitle("Event");
            primaryStage.setScene(scene);
            primaryStage.show();
            Stage stage = (Stage) Ajouter.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Logger.getLogger(ConsulteListEventRespnsableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getevent(MouseEvent event) {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            try {
                Event e=list.getSelectionModel().getSelectedItem();
           
                
        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventResponsable.fxml"));
        Parent root1 = (Parent) loader.load();
        st.setTitle("My Event");
        Scene scene1 = new Scene(root1);
        scene1.getStylesheets().add("css/stylesheet1.css");
        st.setScene(scene1);

        EventResponsableController mainController = loader.<EventResponsableController>getController();
        mainController.setId(e.getId_Event());

        st.show();
         Stage stage = (Stage) list.getScene().getWindow();
        stage.close();
            } catch (IOException ex) {
                Logger.getLogger(ConsulterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    }

    @FXML
    private void getArchive(ActionEvent event) {
        
        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Archve.fxml"));
            Parent root1 = (Parent) loader.load();
            st.setTitle("Archive");
            Scene scene1 = new Scene(root1);
            scene1.getStylesheets().add("css/stylesheet1.css");
            st.setScene(scene1);
            
            ArchveController mainController = loader.<ArchveController>getController();
            mainController.setId(1);
            Stage stage = (Stage) archive.getScene().getWindow();
            stage.close();
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulteListEventRespnsableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void passAction(ActionEvent event) {
          try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventPassRespList.fxml"));
            Parent root1 = (Parent) loader.load();
            st.setTitle("Evennements Passés");
            Scene scene1 = new Scene(root1);
            scene1.getStylesheets().add("css/stylesheet1.css");
            st.setScene(scene1);
            Stage stage = (Stage) pass.getScene().getWindow();
            stage.close();
            
            st.show();
        } 
          catch (IOException ex) {
            Logger.getLogger(ConsulterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    
}
