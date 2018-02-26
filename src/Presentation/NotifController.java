package Presentation;

import Entities.Event;
import Entities.Notification;
import Services.NotificationServices;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class NotifController implements Initializable {
    @FXML
    private Button notif;
    @FXML
    private Label nbr;
    @FXML
    private Circle cercle;
    ArrayList<Notification> list;
    @FXML
    private ListView<Notification> listN;
    Image IMAGE_TWITTER ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       listN.setVisible(false);
        NotificationServices nf = new NotificationServices();
         
        int x=nf.getNotifUnread(1).size();
        if(x!=0)
        {nbr.setText(x+"");}
        else 
        { nbr.setVisible(false);
             cercle.setVisible(false);
        }
    }    

    @FXML
    private void getnotif(ActionEvent event) {
        if(listN.isVisible()==false)
        { 
            listN.setVisible(true);
            nbr.setVisible(false);
             cercle.setVisible(false);
        NotificationServices nf = new NotificationServices();
       
       list= nf.getMYListNotifications(1);
         Collections.sort(list, new Comparator<Notification>() {
    @Override
    public int compare(Notification o1, Notification o2) {
        return o1.getDate_notification().compareTo(
                o2.getDate_notification());
    }
});
       ObservableList<Notification> items =FXCollections.observableArrayList(list) ;
        listN.setItems(items);
         listN.setCellFactory(param -> new ListCell<Notification>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(Notification name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    IMAGE_TWITTER = new Image(name.getImage());
                    imageView.setImage(IMAGE_TWITTER);
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    setText(name.toString());
                    setGraphic(imageView);
                }
            }
        });
       
         /*for(Notification n:list){
               listN.getItems().add(n);
          }*/
        }
        else {
             for(Notification n:list){
               listN.getItems().removeAll(n);
            }
      
            listN.setVisible(false);
        }      
    }

    @FXML
    private void setnotif(MouseEvent event) {
         NotificationServices nf = new NotificationServices();
          listN.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try {
                Notification e=listN.getSelectionModel().getSelectedItem();
                nf.setNotifEtat(e);
                  Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Event.fxml"));
        Parent root1;
           
                root1 = (Parent) loader.load();
            
        st.setTitle("Event");
        Scene scene1 = new Scene(root1);
        scene1.getStylesheets().add("css/stylesheet1.css");
        st.setScene(scene1);
        int id=e.getId_origine();
        EventController mainController = loader.<EventController>getController();
        mainController.setId(id);

        st.show();
                } catch (IOException ex) {
                Logger.getLogger(NotifController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });
    }
    
    
}
