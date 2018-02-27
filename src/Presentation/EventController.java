/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.CommentaireEvent;
import Entities.Event;
import Entities.Notification;
import Entities.Participation;
import Services.CommentaireEventService;
import Services.EventService;
import Services.ParticipationService;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class EventController implements Initializable ,MapComponentInitializedListener {
    @FXML
    private Label Titre;
    int id;
    @FXML
    private Label date;
    @FXML
    private Label lieu;
    @FXML
    private Text text;
    @FXML
    private GoogleMapView mapView;
    
      LatLong latLong;
      String lie;
     private GoogleMap map;
    
    private GeocodingService geocodingService;

    int i;
    @FXML
    private Button participer;
    @FXML
    private Button listPP;
    @FXML
    private ImageView image;
    @FXML
    private TextField comentaire;
    @FXML
    private ListView<String> comlist;
    public void setId(int id_event) {
      id=id_event;
      EventService es = new EventService();  
      Event e =es.getEvent(id);
      Titre.setText(e.getTitre_Event());
      date.setText(date.getText()+e.getDate_Event());
      lieu.setText(lieu.getText()+e.getLieu_Event());
     lie=e.getLieu_Event();
     i=e.getPart();
      text.setText(e.getTexte_Event());
        Image im= new Image(e.getImage());
      image.setImage(im);
      ParticipationService ps= new ParticipationService();
        ArrayList<Participation> p= ps.getParticipations(id_event);
        if(p.size()==0){
        listPP.setDisable(true);
        }
        for (Participation i:p){
        if(i.getId_user()==1)
            participer.setText("Annuler");
        }
        
         CommentaireEventService cs= new CommentaireEventService();
         ArrayList<CommentaireEvent> lC=cs.getCommentaire(es.getEvent(id));
            Collections.sort(lC, new Comparator<CommentaireEvent>() {
    @Override
    public int compare(CommentaireEvent o1, CommentaireEvent o2) {
        return o1.getDate().compareTo(
                o2.getDate());
    }
});
        for(CommentaireEvent c :lC){
        comlist.getItems().add(c.getText());
        }
      
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mapView.addMapInializedListener(this);
      
        
    }    

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(36.899369, 10.189629))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        
         geocodingService.geocode(lie, (GeocodingResult[] results, GeocoderStatus status) -> {
            
            latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
             map.setCenter(latLong);
             MarkerOptions markerOptions = new MarkerOptions();
             markerOptions.position( latLong )
                .visible(Boolean.TRUE)
                .title("My Marker");

    Marker marker = new Marker( markerOptions );

    map.addMarker(marker);
    
            
            

        });
   
        
    }

    @FXML
    private void participerAction(ActionEvent event) {
         EventService es = new EventService();  
         Event t =es.getEvent(id);
         ParticipationService ps=new ParticipationService();
         int id_user=1;
         Participation p = new Participation(id_user,id);
         
      
        participer.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
          if(participer.getText().equals("Participer")){
              try {
                  t.setPart(t.getPart()+1);
                  es.setParticipation(t);
                  ps.addParticipation(p);
                  participer.setText("Annuler");
                  
                  Stage st = new Stage();
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
                  Parent root1 = (Parent) loader.load();
                  st.setTitle("Event");
                  Scene scene1 = new Scene(root1);
                  scene1.getStylesheets().add("css/stylesheet1.css");
                  st.setScene(scene1);
                  
                  EventController mainController = loader.<EventController>getController();
                  mainController.setId(t.getId_Event());
                  
                  st.show();
                    Stage stage = (Stage) participer.getScene().getWindow();
                       stage.close();
              } catch (IOException ex) {
                  Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
              }
      
        
      } 
           else{
              try {
                  t.setPart(t.getPart()-1);
                  es.setParticipation(t);
                  participer.setText("Participer");
                  ps.deleteParticipation(p);
                  
                  Stage st = new Stage();
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
                  Parent root1 = (Parent) loader.load();
                  st.setTitle("Event");
                  Scene scene1 = new Scene(root1);
                  scene1.getStylesheets().add("css/stylesheet1.css");
                  st.setScene(scene1);
                  
                  EventController mainController = loader.<EventController>getController();
                  mainController.setId(t.getId_Event());
                  
                  st.show();
                      Stage stage = (Stage) participer.getScene().getWindow();
                       stage.close();
              } catch (IOException ex) {
                  Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
        
    }
});
   
         

        
        
    }

    @FXML
    private void getParticipant(ActionEvent event) {
          
        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListParticipants.fxml"));
            Parent root1 = (Parent) loader.load();
            st.setTitle("Event");
            Scene scene1 = new Scene(root1);
            scene1.getStylesheets().add("css/stylesheet1.css");
            st.setScene(scene1);
            ListParticipantsController mainController = loader.<ListParticipantsController>getController();
            mainController.setId(id);
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AddCommantaire(ActionEvent event) {
        String textC =comentaire.getText();
        int id_user =1;
      
        CommentaireEvent com = new CommentaireEvent(id_user,id,textC,LocalDate.now());
        CommentaireEventService cs= new CommentaireEventService();
        cs.addComment(com);
        comentaire.setText("");
        EventService es = new  EventService();
        ArrayList<CommentaireEvent> lC=cs.getCommentaire(es.getEvent(id));
        for(CommentaireEvent c :lC){
        comlist.getItems().add(c.getText());
        }
        
    }
    
}
