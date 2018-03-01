/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Event;
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

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class ArchveController implements Initializable {
    @FXML
    private ListView<Event> listA;

   int id;
  Image  IMAGE_TWITTER;
    @FXML
    private Button returnB;
    
     public void setId(int id_user) {
       id=id_user;
    
      
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EventService es = new EventService();  
         ArrayList<Event> listE=es.getArchive(1);
           Collections.sort(listE, new Comparator<Event>() {
    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDate_Event().compareTo(
                o2.getDate_Event());
    }
});
           ObservableList<Event> items =FXCollections.observableArrayList(listE) ;
        listA.setItems(items);
         listA.setCellFactory(new Callback<ListView<Event>, ListCell<Event>>() {

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
    private void getArchiv(MouseEvent event) {
        
          listA.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            try {
                Event e=listA.getSelectionModel().getSelectedItem();
                
        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventArchve.fxml"));
        Parent root1 = (Parent) loader.load();
        st.setTitle("Event Archivé");
        Scene scene1 = new Scene(root1);
        scene1.getStylesheets().add("css/stylesheet1.css");
        st.setScene(scene1);

        EventArchveController mainController = loader.<EventArchveController>getController();
        mainController.setId(e.getId_Event());
         Stage stage = (Stage) listA.getScene().getWindow();
        stage.close();

        st.show();
            } catch (IOException ex) {
                Logger.getLogger(ConsulterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
        
    }

    @FXML
    private void retA(ActionEvent event) {
         try {
            Stage stage = (Stage)returnB.getScene().getWindow();
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("ConsulteListEventRespnsable.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet1.css");
            Stage   primaryStage = new Stage();
            primaryStage.setTitle("Event");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(EventArchveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
