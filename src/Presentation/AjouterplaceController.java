/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Place;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Services.SPlace;
import Util.PostFile;
import Util.amiaffichage;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterplaceController implements Initializable ,MapComponentInitializedListener {

    @FXML
    private TextField p_libelle;
    @FXML
    private TextField addressTextField;
    
    @FXML
    private ImageView icon;
    
    @FXML
    private TextField file;
    
    @FXML
    private ComboBox<String> type;
    @FXML
    private Button ajoutbtn;
    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;
    LatLong latLong;
    private GeocodingService geocodingService;
    private StringProperty address = new SimpleStringProperty();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());

        Image image = new Image("file:/C:/wamp64/www/Img/ok.png");
        icon.setImage(image);
        type.getItems().addAll("Sport","Art","Musique","Nature","Culturel","Fête");
        file.setEditable(false);
     
                 
           
      
    }    
String path;
    @FXML
    private void choose(ActionEvent event) throws Exception {
        Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
        Image image = new Image("file:/C:/wamp64/www/Img/notok.png");
        icon.setImage(image);
        file.setText("file:\\" + fil.toString());
        System.out.println(fil.getAbsoluteFile());
        //PostFile.upload(fil.getAbsolutePath());
        path=fil.getAbsolutePath();

    }
    
    
    @FXML
    private void ajouterplace(ActionEvent event) throws IOException, Exception {
        if (p_libelle.getText().compareTo("")==0 ||addressTextField.getText().compareTo("")==0 || file.getText().compareTo("")==0 || type.getValue().toString().compareTo("")==0){
            Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ADD");
alert.setHeaderText(null);
alert.setContentText("Ooops, il faut remplir tous les champs");

alert.showAndWait();
        } else {
            

        Place p=new Place(p_libelle.getText(), addressTextField.getText(),"file:/C:/wamp64/www/place_images/"+PostFile.upload(path),type.getValue());
        SPlace sp=new SPlace();
        sp.ajouter(p);
        p_libelle.setText("");
        addressTextField.setText("");
        file.setText("");
        type.setValue("");
         Image image = new Image("file:/C:/wamp64/www/Img/ok.png");
        icon.setImage(image);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Succes");
           alert.setHeaderText(null);
         alert.setContentText("New Place Addes");
         
         alert.showAndWait();
         Stage stage=(Stage) p_libelle.getScene().getWindow();
         stage.close();
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
   

   
}
