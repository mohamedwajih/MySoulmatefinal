/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Place;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class TestviewController implements Initializable {

    @FXML
    private ListView<Place> listamis;
    @FXML
    private ImageView testimage;
    private  ObservableList<Place> data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listamis.setVisible(true);
                Image image = new Image("file:/C:/Users/asus/Documents/NetBeansProjects/Mysoulmatewajih/Ress/11.png");
                testimage.setImage(image);
             
                
                Place p=new Place();
                p.setPhoto("file:/C:/Users/asus/Documents/NetBeansProjects/Mysoulmatewajih/Ress/notok.png");
                p.setLibelle("rades");
                p.setAdr("fdfdf");
                
data.add(p);
                listamis.setCellFactory(new Callback<ListView<Place>, ListCell<Place>>(){
                   
                    @Override
                    public ListCell<Place> call(ListView<Place> arg0) {
                        
                        ListCell<Place> cell=new ListCell<Place>(){
                            @Override
                            protected void updateItem(Place p, boolean bll){
                               
                                super.updateItem(p,bll);
                                if(p!=null) {
                                    
                                    Image pic=new Image(p.getPhoto(),40,55, bll, bll);
                                    
                                    ImageView imageview=new ImageView(pic);
                                    setGraphic(imageview);
                                    setText(p.getLibelle());
                                    
                                }
                            }
                        };
                        return cell;
                    }
                });
                 
               listamis.setItems(data);
    }    
    
}
