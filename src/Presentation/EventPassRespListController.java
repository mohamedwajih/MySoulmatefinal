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

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class EventPassRespListController implements Initializable {
    @FXML
    private ListView<Event> listEvent;

    Image IMAGE_TWITTER ;
    @FXML
    private Button retour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         EventService es = new EventService();
       ArrayList<Event> listE=es.getListEventsUserPassé(1);
          Collections.sort(listE, new Comparator<Event>() {
    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDate_Event().compareTo(
                o2.getDate_Event());
    }
});
          ObservableList<Event> items =FXCollections.observableArrayList(listE) ;
        listEvent.setItems(items);
         listEvent.setCellFactory(param -> new ListCell<Event>() {
            private ImageView imageView = new ImageView();
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
        });
        
    }    

    @FXML
    private void getevent(MouseEvent event) {
          listEvent.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            try {
                Event e=listEvent.getSelectionModel().getSelectedItem();
        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventPass.fxml"));
        Parent root1 = (Parent) loader.load();
        st.setTitle("Event Passé");
        Scene scene1 = new Scene(root1);
        scene1.getStylesheets().add("css/stylesheet1.css");
        st.setScene(scene1);

        EventPassController mainController = loader.<EventPassController>getController();
        mainController.setId(e.getId_Event());

        st.show();
            } catch (IOException ex) {
                Logger.getLogger(ConsulterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });    
    }

    @FXML
    private void retourA(ActionEvent event) {
        
         try {
            Stage stage = (Stage)retour.getScene().getWindow();
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
