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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

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
      Event e ;
    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    @FXML
    private ImageView imageV;
    @FXML
    private Button addImage;
    
    
    
     Random rd = new Random(); 
    public   static int n ; 
    final FileChooser fileChooser = new FileChooser();
    final   File fileSave = new File("C:\\wamp\\www\\mysoulmate\\photo\\");
    static Image image ; 
    public  static Stage stage ;
    public  String nomFichier ; 
    
    public static Stage getStage() 
    {
        return stage;
    }
    @FXML
    private Label url;
    @FXML
    private Button listE;

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
       n=rd.nextInt(10000)+1;
            EventService es1 =new EventService();
           
            String errorMessage = "";
            e = new Event();
            int id_user_event= 1;
            String typeEv=type.getValue();
            System.out.println(typeEv);
            String title=titre.getText();
            LocalDate dateEvenet=date.getValue();
            String description = texte.getText();
            
               if ( title== null || title.length() == 0) 
        {
            errorMessage += "Titre invalide !\n"; 
        }
               if ( typeEv== null || typeEv.length() == 0) 
        {
            errorMessage += "Type evenement invalide !\n"; 
        }  if ( dateEvenet== null ) 
        {
            errorMessage += "Date evenement invalide !\n"; 
        } if ( description== null || description.length() == 0) 
        {
            errorMessage += "Date evenement invalide !\n"; 
        }
        if ( addressTextField.getText()== null || addressTextField.getText().length() == 0) 
        {
            errorMessage += "Adresse evenement invalide !\n"; 
        }
        if ( nomFichier== null || nomFichier.length() == 0) 
        {
            errorMessage += "Ajoutez image!!! !\n"; 
        }
             if (errorMessage.length() == 0) 
        {   
            e.setId_user_event(id_user_event);
            e.setType_Event(typeEv);
            e.setTitre_Event(title);
            e.setTexte_Event(description);
            e.setLieu_Event(addressTextField.getText());
            e.setDate_Event(dateEvenet);
            e.setPart(0);
            e.setImage("http://localhost/mysoulmate/photo/"+nomFichier+n+".png");
             try {
                es1.addEvent(e);
        
        
         ArrayList<Integer> liC=es1.getIdUserCibleEvent(e);
           int id;
           id= es1.getIdEvent(e);
            for(int i=0;i<liC.size();i++){
            NotificationServices ns =new NotificationServices();
            Notification n=new Notification(0,liC.get(i),id,"event",e.getTitre_Event(),LocalDate.now(),e.getImage());
            ns.addNotification(n);
           
            }
         
          try 
                { 
                  
         
                
                
                        File nomfichier = new File("C:/wamp/www/mysoulmate/photo/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(imageV.getImage(),null), "png", nomfichier);
                        insertionBase(nomFichier+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(AjouterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(AjouterEvennementController.class.getName()).log(Level.SEVERE, null, ex);
                   
        
      
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Evenement ajoutée");
            alert.setHeaderText("Evenement ajoutée avec succès");
            alert.setContentText("Evenement "+titre.getText()+" ajout avec succès");
            alert.showAndWait();
            close(event);
          }    
      
                 
                 
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
        }}
             else 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs Invalides");
            alert.setHeaderText("Veuillez remplir les champs");
            alert.setContentText(errorMessage);
            alert.showAndWait();

         
        }
}
    
    
     private void close(ActionEvent event) throws IOException
    {
    
    Parent homePage = FXMLLoader.load(getClass().getResource(""));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    
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
             setExtFilters(fileChooser);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) 
                {
                  //url.setText(file.getAbsolutePath());
                  image = new Image(file.toURI().toString());
        imageV.setFitHeight(200);
        imageV.setPreserveRatio(true);
        imageV.setImage(image);
        imageV.setSmooth(true);
        imageV.setCache(true);
                    nomFichier = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier);
                }
    
}
    
    public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("mysoulmate");
                urlb.addSubfolder("uploadimage.PHP");
                urlb.addParameter("image","http://localhost/mysoulmate/photo/"+nomFile+".png");
                urlb.addParameter("titre",e.getId_Event()+"");
        
                String url = urlb.getURL();
                this.url.setText(url);
                System.out.println(url);
        
        URL URl_Serveur = new URL(url);
                HttpURLConnection conx = (HttpURLConnection) URl_Serveur.openConnection();
                conx.setRequestMethod("POST");
                conx.setDoOutput(true);
                OutputStream os = conx.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

 
      writer.flush();
                writer.close();
                conx.connect();


                int reponse = conx.getResponseCode();

                if (reponse == HttpsURLConnection.HTTP_OK) 
                {
                    InputStream is = conx.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String ligne = "", resultat = "";

                    while ((ligne = br.readLine()) != null) 
                    {

                        resultat += ligne;
                    }

    }
}  
    
    private void setExtFilters(FileChooser chooser) 
    {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    private void listetE(ActionEvent event) {
         try {
            Stage stage = (Stage)listE.getScene().getWindow();
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

    class URLBuilder 
    {
    private StringBuilder folders, params;
    private String connType, host;

    void setConnectionType(String conn) 
    {
        connType = conn;
    }

    URLBuilder()
    {
        folders = new StringBuilder();
        params = new StringBuilder();
    }

    URLBuilder(String host) 
    {
        this();
        this.host = host;
    }

    void addSubfolder(String folder) 
    {
        folders.append("/");
        folders.append(folder);
    }

    void addParameter(String parameter, String value)
    {
        if(params.toString().length() > 0){params.append("&");}
        params.append(parameter);
        params.append("=");
        params.append(value);
    }

    String getURL() throws URISyntaxException, MalformedURLException
    {
        URI uri = new URI(connType, host, folders.toString(),
                params.toString(), null);
        return uri.toURL().toString();
    }

    String getRelativeURL() throws URISyntaxException, MalformedURLException
    {
        URI uri = new URI(null, null, folders.toString(), params.toString(), null);
        return uri.toString();
    }
    }
    
    
    
}
