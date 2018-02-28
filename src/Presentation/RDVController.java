/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import Entities.Place;
import Entities.Rdv;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import Services.SPlace;
import Services.SRdv;
import Util.amiaffichage;
import Util.datapik;
import Util.sendsms;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.DateCell;
import javafx.scene.control.Tooltip;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RDVController implements Initializable {

    @FXML
    private ImageView pdp;
    @FXML
    private Label nomami;
    @FXML
    private Label ageami;
    @FXML
    private DatePicker daterdv;
    @FXML
    private ComboBox<Place> place;
    @FXML
    private ImageView photoplace;
    @FXML
    private Label pays;
    @FXML
    private ChoiceBox<String> heure;
    @FXML
    private ChoiceBox<String> minute;
    @FXML
    private ImageView userlogo;
    @FXML
    private ImageView plus;
    @FXML
    private Label theme;
    
    amiaffichage af=new amiaffichage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String the = null;
        if(af.getpref(NewFXMain.idu2).compareTo("sportif")==0)
            the="Sport";
        if(af.getpref(NewFXMain.idu2).compareTo("sociable")==0)
            the="Fête";
        if(af.getpref(NewFXMain.idu2).compareTo("aventurier")==0)
            the="Nature";
        if(af.getpref(NewFXMain.idu2).compareTo("artiste")==0)
            the="Culturel";
        theme.setText(the);
        
        /////////////////////////////
       /*  final Callback<DatePicker, DateCell> dayCellFactory = 
        new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(daterdv.getValue().plusDays(1))) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                        }
                        long p = ChronoUnit.DAYS.between(
                                daterdv.getValue(), item
                        );
                        setTooltip(new Tooltip(
                            "You're about to stay for " + p + " days")
                        );
                }
            };
        }
    };
    daterdv.setDayCellFactory(dayCellFactory);*/
        Callback<DatePicker, DateCell> dayCellFactory= datapik.getDayCellFactory();
        daterdv.setDayCellFactory(dayCellFactory);
        //////////////////////////COMBO BOX
        place.setCellFactory(new Callback<ListView<Place>,ListCell<Place>>(){
            @Override
            public ListCell<Place> call(ListView<Place> p) {
                 
                final ListCell<Place> cell = new ListCell<Place>(){
 
                    @Override
                    protected void updateItem(Place t, boolean bln) {
                        super.updateItem(t, bln);
                         
                        if(t != null){
                            setText(t.getLibelle());
                            setFont(Font.font(17));
                            if(t.getType().compareTo(theme.getText())==0){
                                setTextFill(color(0.0, 1.0, 0.0));
                                
                            }
                           
                        }else{ 
                            setText("Choisissez le lieu de Rendez_vous !");
                        }
                    }
  
                };
                 
                return cell;
            }
        }); 
        String s;
        for (int i = 0; i < 24; i++) {
            if(i<10){
                 s="0"+i;
                             heure.getItems().add(s);
            } else {
                       heure.getItems().add(String.valueOf(i));
            }
               
        }
        
        for (int i = 0; i < 60; i++) {
            if(i<10){
                 s="0"+i;
                             minute.getItems().add(s);
            } else {
                       minute.getItems().add(String.valueOf(i));
            }
               
        }
                
        
      ////////////////////////END COMBO BOX
        
        
        SPlace sp= new SPlace();
        List<Place> l1=new ArrayList<Place>();
        l1=sp.lister();
        ObservableList<Place> dataplace=FXCollections.observableArrayList();
        dataplace.addAll(l1);
        place.getItems().addAll(dataplace);
        
     ////////////////////////PROFIL
        amiaffichage af=new amiaffichage();
        Fos_user fs=new  Fos_user();
        fs=af.getphotouserbyid(NewFXMain.idu2);
        Image image = new Image(fs.getPhoto_de_profil());
        pdp.setImage(image);
        System.out.println(fs.getPhoto_de_profil());
        nomami.setText(fs.getPrenom());
        ageami.setText(String.valueOf(fs.getAge())+" Ans");
        pays.setText(fs.getAdresse());
     /////////////////////END PROFIL
        
        
        
        
    }    

 

    @FXML
    private void addrdv(MouseEvent event) {
        if(place.getValue()==null || daterdv.getValue()==null || heure.getValue()==null || minute.getValue()==null){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Ooops, il faut remplir tous les champs");
        alert.showAndWait();
        } else {
        SRdv sr=new SRdv();
        Rdv r=new Rdv();
        r.setId1(NewFXMain.idu1);
        r.setId2(NewFXMain.idu2);
        r.setEtat(0);
        r.setLieu(place.getValue().getId_place());  
        r.setDate(String.valueOf(daterdv.getValue()),Integer.parseInt(heure.getValue()),Integer.parseInt(minute.getValue()));
            System.out.println( sr.rechercher(r.getId1(), r.getId2(), r.getDate()));
        if(sr.rechercher(r.getId1(), r.getId2(), r.getDate())==false){
            Fos_user fu=new Fos_user();
            amiaffichage af=new amiaffichage();
            fu=af.getphotouserbyid(NewFXMain.idu2);
           sr.ajouter(r);
           String msg="Quelqu'un vous a proposé un rendez-LOVE , Consultez votre Compte MySoulmate pour le confirmer.";
           sendsms sm=new sendsms();
          // sm.send(fu.getNum_tel(), msg);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("succes");
           alert.setHeaderText(null);
           alert.setContentText(fu.getPrenom()+" Va recevoir un SMS pour confirmer votre rendez-vous");
           alert.showAndWait();
           Stage stage= (Stage) place.getScene().getWindow();
           stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR); 
            alert.setTitle("Error");
            alert.setHeaderText(null); 
            alert.setContentText("Vous Avez deja un rendez-vous avec cette personne dans cette date !");
            alert.showAndWait();
        }
        
    }}

    @FXML
    private void showim(MouseEvent event) {
            if (place.getSelectionModel().getSelectedItem() != null) {
        Place u = place.getSelectionModel().getSelectedItem(); 
        Image image = new Image(u.getPhoto());
        photoplace.setImage(image);
            System.out.println(u.getPhoto());
    }
    }
}
    

