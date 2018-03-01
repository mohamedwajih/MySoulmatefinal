          /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Event;
import Entities.Participation;
import Services.EventService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class ConsulterEvennementController implements Initializable {
    @FXML
    private ListView<Event> listEvent;

  Image IMAGE_TWITTER ;
    @FXML
    private Button passée;
    @FXML
    private TextField recherche;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EventService es = new EventService();  
     
       ArrayList<Event> listE=es.getListEvents();
         Collections.sort(listE, new Comparator<Event>() {
    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDate_Event().compareTo(
                o2.getDate_Event());
    }
});
         for(Event e:listE){
         
               listEvent.getItems().add(e);
          }
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
    private void getevent(MouseEvent event) throws IOException {
        listEvent.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            try {
                Event e=listEvent.getSelectionModel().getSelectedItem();
              /*  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText(e.toString());
                alert.showAndWait();*/
              /*  Parent root = FXMLLoader.load(getClass().getResource("Event.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add("css/stylesheet1.css");
                Stage   primaryStage = new Stage();
                primaryStage.setTitle("Event");
                primaryStage.setScene(scene);
                primaryStage.show();
                
               */ 
                
        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
        Parent root1 = (Parent) loader.load();
        st.setTitle("Event");
        Scene scene1 = new Scene(root1);
        scene1.getStylesheets().add("css/stylesheet1.css");
        st.setScene(scene1);

        EventController mainController = loader.<EventController>getController();
        mainController.setId(e.getId_Event());

        st.show();
            } catch (IOException ex) {
                Logger.getLogger(ConsulterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
   /*  Parent root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/stylesheet1.css");
        Stage   primaryStage = new Stage();
        primaryStage.setTitle("Event");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    @FXML
    private void passeAction(ActionEvent event) {
        
        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventPasséUserList.fxml"));
            Parent root1 = (Parent) loader.load();
            st.setTitle("Evennements Passés");
            Scene scene1 = new Scene(root1);
            scene1.getStylesheets().add("css/stylesheet1.css");
            st.setScene(scene1);
            
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    @FXML
    private void rechercheAction(ActionEvent event) {
        
        
         String champsrecherche = recherche.getText();
         if(champsrecherche== null || champsrecherche.length() == 0 ){
           try {
                Stage stage = (Stage) recherche.getScene().getWindow();
            stage.close();
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterEvennement.fxml"));
            Parent root1 = (Parent) loader.load();
            st.setTitle("Evennements Passés");
            Scene scene1 = new Scene(root1);
            scene1.getStylesheets().add("css/stylesheet1.css");
            st.setScene(scene1);
            
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsulterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
        }}
         else{
          ObservableList<Event> eventlist = FXCollections.observableArrayList();
        EventService es = new EventService();
         List<Event> listE = new ArrayList<Event>();
         listE = es.rechercher(champsrecherche);
      
           for(Event e:listE){
         
               listEvent.getItems().add(e);
          }
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
    }
    
    
}
