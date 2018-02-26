/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Event;
import Entities.Notification;
import Services.EventService;
import Services.NotificationServices;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class EditEventController implements Initializable,MapComponentInitializedListener {
    @FXML
    private TextArea text;
    @FXML
    private DatePicker date;
    @FXML
    private Button edit;
    @FXML
    private TextField titre;
    @FXML
    private TextField lieu;
    int id;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private ImageView Image;
     LatLong latLong;
 
    
    private GeocodingService geocodingService;
    @FXML
    private GoogleMapView mapV;
    private GoogleMap map;

     public void setIdE(int id_event) {
         
        
    
       id=id_event;
      EventService es = new EventService();  
      Event e =es.getEvent(id);
      titre.setText(e.getTitre_Event());
      date.setValue(e.getDate_Event());
      lieu.setText(e.getLieu_Event());
      text.setText(e.getTexte_Event());
      type.setValue(e.getType_Event());
      Image im= new Image(e.getImage());
      Image.setImage(im);
     
     
        ArrayList<String> EventT= es.getListTypeEvents();
      for(int i=0 ;i<EventT.size();i++){
          type.getItems().add(EventT.get(i));
      }
      
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
      @Override
      public DateCell call(final DatePicker datePicker) {
        return new DateCell() {
          @Override
          public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);

            if (item.isBefore(LocalDate.now().plusDays(1))) {
              setDisable(true);
              setStyle("-fx-background-color: #EEEEEE;");
            }
          }
        };
      }
    };
        date.setDayCellFactory(dayCellFactory);
        mapV.addMapInializedListener(this);
     
    }    

    @FXML
    private void editEvent(ActionEvent event) {
       try {
           
            Event e = new Event();
            EventService es = new EventService();
            e.setDate_Event(date.getValue());
            e.setLieu_Event(lieu.getText());
            e.setTitre_Event(titre.getText());
            e.setTexte_Event(text.getText());
            e.setType_Event(type.getValue());
            e.setId_Event(id);
            e.setImage("css/x.png");
            System.out.println(e);
            es.setEvent(e);
            
             ArrayList<Integer> liC=es.getIdUserCibleEvent(e);
          
          
            for(int i=0;i<liC.size();i++){
            NotificationServices ns =new NotificationServices();
            Notification n=new Notification(0,liC.get(i),id,"event",e.getTitre_Event(),LocalDate.now(),e.getImage());
            ns.addNotification(n);
            }
           
            System.out.println(es.getEvent(id));
            
        Stage stage = (Stage) edit.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("ConsulteListEventRespnsable.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet1.css");
            Stage   primaryStage = new Stage();
            primaryStage.setTitle("Mes Evenements");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addressTextFieldAction(ActionEvent event) {
        
         geocodingService.geocode(lieu.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
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

        map = mapV.createMap(mapOptions);
        
         geocodingService.geocode(lieu.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
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
    
}
