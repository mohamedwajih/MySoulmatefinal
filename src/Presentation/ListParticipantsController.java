/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Event;
import Entities.Notification;
import Entities.Participation;
import Services.ParticipationService;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.P;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ste GHRIB-INFO
 */
public class ListParticipantsController implements Initializable {
    @FXML
    private ListView<Participation> listP;
     int id;
          private final Image IMAGE_TWITTER = new Image("css/images.jpg");

     public void setId(int id_event) {
       id=id_event;
        ParticipationService part=new ParticipationService();
        
         ArrayList<Participation> listPa= part.getParticipations(id);
         ObservableList<Participation> items =FXCollections.observableArrayList(listPa) ;
        listP.setItems(items);
         listP.setCellFactory(param -> new ListCell<Participation>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Participation name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(IMAGE_TWITTER);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    setText(name.toString());
                    setGraphic(imageView);
                }
            }
        });
        
        
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      
    }    

    @FXML
    private void getevent(MouseEvent event) {
    }
    
}
