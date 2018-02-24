/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Rdv;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.SRdv;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ConsulterrdvController implements Initializable {

    @FXML
    private TableView<Rdv> table;
    @FXML
    private TableColumn id_u1;
    @FXML
    private TableColumn id_u2;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn id_lieu;
    @FXML
    private TableColumn etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<Rdv> listm = FXCollections.observableArrayList();
        SRdv sp=new SRdv();
        List<Rdv> l1=new ArrayList<Rdv>();
        l1=sp.listerAll();
        listm.addAll(l1);
        System.out.println(l1);
        id_u1.setCellValueFactory(new PropertyValueFactory<Rdv,String>("id1"));
        id_u2.setCellValueFactory(new PropertyValueFactory<Rdv,String>("id2"));
        date.setCellValueFactory(new PropertyValueFactory<Rdv,String>("date"));
        id_lieu.setCellValueFactory(new PropertyValueFactory<Rdv,String>("lieu"));
        etat.setCellValueFactory(new PropertyValueFactory<Rdv,String>("etat"));
        
        table.setItems(listm);
        
        
        
    }    
    
}
