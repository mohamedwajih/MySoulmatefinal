/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import Services.EventService;
import Entities.Event;
import Entities.Notification;
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
import java.io.File;
import Utils.PostFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class AjouterEvennementController implements Initializable ,MapComponentInitializedListener {
    @FXML
    private TextField titre;
    private TextField lieu;
    @FXML
    private Button ajout;
    @FXML
    private ChoiceBox<String> type;
    
    @FXML
    private TextArea texte;
    @FXML
    private DatePicker date;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField addressTextField;
     private GoogleMap map;
     LatLong latLong;
     File file;
    
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    @FXML
    private ImageView image;
    @FXML
    private Button addImage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       date.setValue(LocalDate.now());
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
         mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
        EventService es =new EventService();
        ArrayList<String> EventT= es.getListTypeEvents();
      for(int i=0 ;i<EventT.size();i++){
          type.getItems().add(EventT.get(i));
      }
    }    

    @FXML
    private void addEvent(ActionEvent event) {
        try {
            EventService es1 =new EventService();
           
           
            Event e = new Event();
            int id_user_event= 1;
            String typeEv=type.getValue();
            System.out.println(typeEv);
            String title=titre.getText();
            LocalDate dateEvenet=date.getValue();
            String description = texte.getText();
     
            
            e.setId_user_event(id_user_event);
            e.setType_Event(typeEv);
            e.setTitre_Event(title);
            e.setTexte_Event(description);
            e.setLieu_Event(addressTextField.getText());
            e.setDate_Event(dateEvenet);
            e.setPart(0);
            e.setImage("css/images.jpg");
            es1.addEvent(e);
            
           ArrayList<Integer> liC=es1.getIdUserCibleEvent(e);
           int id;
           id= es1.getIdEvent(e);
            for(int i=0;i<liC.size();i++){
            NotificationServices ns =new NotificationServices();
            Notification n=new Notification(0,liC.get(i),id,"event",e.getTitre_Event(),LocalDate.now(),e.getImage());
            ns.addNotification(n);
            }
            
        /*   Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evennement ajouté avec succès");
            alert.showAndWait();*/
           /* titre.clear();
            lieu.clear();
            texte.clear();*/
            Stage stage = (Stage) ajout.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("ConsulteListEventRespnsable.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet1.css");
            Stage   primaryStage = new Stage();
            primaryStage.setTitle("Event");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjouterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void addressTextFieldAction(ActionEvent event) {
             geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
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

        map = mapView.createMap(mapOptions);
        }

    @FXML
    private void AddImage(ActionEvent event) throws MalformedURLException {
              FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null)
        {
            String imageFile= selectedFile.toURI().toURL().toString();
            this.file= selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsoluteFile());
            //PostFile.upload(selectedFile.getAbsolutePath());
       
        }
    }
    
}
