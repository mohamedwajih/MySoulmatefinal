/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Event;
import Entities.Notification;
import static Presentation.AjouterEvennementController.image;
import static Presentation.AjouterEvennementController.n;
import static Presentation.AjouterEvennementController.stage;
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
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    @FXML
    private Button modimage;
    @FXML
    private Button anuul;
    
    
    
     File file;
      Event e1 ;
      
      
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
        n=rd.nextInt(10000)+1;
       try {
           
            e1 = new Event();
            EventService es = new EventService();
            
           
         if ( nomFichier== null || nomFichier.length() == 0) 
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs Invalides");
            alert.setHeaderText("Veuillez remplir les champs");
            alert.setContentText("Ajoutez un photo !!");
            alert.showAndWait();

             
        }
             else{
              e1.setId_Event(id);
            e1.setId_user_event(es.getEvent(id).getId_user_event());
            
            e1.setDate_Event(date.getValue());
            e1.setLieu_Event(lieu.getText());
            e1.setTitre_Event(titre.getText());
            e1.setTexte_Event(text.getText());
            e1.setType_Event(type.getValue());
            
            e1.setImage("http://localhost/mysoulmate/photo/"+nomFichier+n+".png");
          
             }
            
           
            
         
            
            es.setEvent(e1);
            
             ArrayList<Integer> liC=es.getIdUserCibleEvent(e1);
          
          
            for(int i=0;i<liC.size();i++){
            NotificationServices ns =new NotificationServices();
            Notification n=new Notification(0,liC.get(i),id,"event",e1.getTitre_Event(),LocalDate.now(),e1.getImage());
            ns.addNotification(n);
            }
            try 
                { 
                        File nomfichier = new File("C:/wamp/www/mysoulmate/photo/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(Image.getImage(),null), "png", nomfichier);
                        insertionBase(nomFichier+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(EditEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(EditEventController.class.getName()).log(Level.SEVERE, null, ex);
                   
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atualité ajoutée");
            alert.setHeaderText("Atualité ajoutée avec succès");
            alert.setContentText("Atualité "+titre.getText()+" ajout avec succès");
            alert.showAndWait();
            close(event);
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

    @FXML
    private void modim(ActionEvent event) {
         setExtFilters(fileChooser);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) 
                {
                  //url.setText(file.getAbsolutePath());
                  image = new Image(file.toURI().toString());
        Image.setFitHeight(200);
        Image.setPreserveRatio(true);
        Image.setImage(image);
        Image.setSmooth(true);
        Image.setCache(true);
                    nomFichier = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier);
                }
    }
      public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                EditEventController.URLBuilder urlb = new EditEventController.URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("mysoulmate");
                urlb.addSubfolder("uploadimage.PHP");
                urlb.addParameter("image","http://localhost/mysoulmate/photo/"+nomFile+".png");
                urlb.addParameter("titre",e1.getId_Event()+"");
        
                String url = urlb.getURL();
              
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
    private void annuler(ActionEvent event) {
        try {
             EventService es = new EventService();  
             Event e =es.getEvent(id);
            Stage stage = (Stage)anuul.getScene().getWindow();
              ArrayList<Integer> liC=es.getIdUserCibleEvent(e);
          
          
            for(int i=0;i<liC.size();i++){
            NotificationServices ns =new NotificationServices();
            Notification n=new Notification(0,liC.get(i),id,"event",e.getTitre_Event(),LocalDate.now(),e.getImage());
            ns.addNotification(n);
            }
            stage.close();
            
            Parent root = FXMLLoader.load(getClass().getResource("ConsulteListEventRespnsable.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet1.css");
            Stage   primaryStage = new Stage();
            primaryStage.setTitle("Event");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(EditEventController.class.getName()).log(Level.SEVERE, null, ex);
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
