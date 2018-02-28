/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Event;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class EventResponsableController implements Initializable,MapComponentInitializedListener {
int id;
    @FXML
    private Label titre;
    @FXML
    private Label lieu;
    @FXML
    private Label date;
    @FXML
    private Text text;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    EventService es ;
    Event e ;
      LatLong latLong;
      String lie;
     private GoogleMap map;
    
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    int i;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private ImageView image;
    public void setId(int id_event) {
       id=id_event;
      es= new EventService();  
      e=es.getEvent(id);
      titre.setText(e.getTitre_Event());
      date.setText(date.getText()+e.getDate_Event());
      lieu.setText(lieu.getText()+e.getLieu_Event());
      text.setText(e.getTexte_Event());
      Image im= new Image(e.getImage());
      image.setImage(im);
      
       lie=e.getLieu_Event();
     i=e.getPart();
       if(e.getPart()!=0){
       edit.setDisable(true);
       delete.setDisable(true);
       }
      
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          mapView.addMapInializedListener(this);
        address.bind(lieu.textProperty());
      
    }    

    @FXML
    private void editEvent(ActionEvent event) {
    try {
        
        NotificationServices ns= new NotificationServices();
        ns.deleteNotif(id);
        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditEvent.fxml"));
        Parent root1 = (Parent) loader.load();
        st.setTitle("My Event");
        Scene scene1 = new Scene(root1);
        scene1.getStylesheets().add("css/stylesheet1.css");
        st.setScene(scene1);

        EditEventController mainController = loader.<EditEventController>getController();
        mainController.setIdE(id);
        st.show();
         Stage stage = (Stage) edit.getScene().getWindow();
         stage.close();
        
    } catch (IOException ex) {
        Logger.getLogger(EventResponsableController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void deleteEvent(ActionEvent event) {
    try {
        NotificationServices ns= new NotificationServices();
        ns.deleteNotif(id);
        EventService es = new EventService();
        Event e = new Event();
       // e.setId_Event(id);
       e= es.getEvent(id);
        es.archverEvennement(e);
        es.deleteEvent(e);
        
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("ConsulteListEventRespnsable.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/stylesheet1.css");
        Stage   primaryStage = new Stage();
        primaryStage.setTitle("Mes Evenements");
        primaryStage.setScene(scene);
        primaryStage.show();
    } catch (IOException ex) {
        Logger.getLogger(EventResponsableController.class.getName()).log(Level.SEVERE, null, ex);
    }
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
    
}
