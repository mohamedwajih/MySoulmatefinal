/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Services.SPersonnalite;
import Services.SPreference;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class ResultatsTestController implements Initializable {

    @FXML
    private AreaChart<Float, String> AreaChart;
    SPersonnalite sper= new SPersonnalite();
    SPreference spre= new SPreference();
    int id=1;
    @FXML
    private NumberAxis axepourcentage;
    @FXML
    private CategoryAxis axetrait;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series= new XYChart.Series();
        series.getData().add(new XYChart.Data("nerveux",sper.listerOne(id).getNerveux()));
        series.getData().add(new XYChart.Data("attirant",sper.listerOne(id).getAttirant()));
        series.getData().add(new XYChart.Data("sensible",sper.listerOne(id).getSensible()));
         series.getData().add(new XYChart.Data("romantique",sper.listerOne(id).getRomantique()));
        series.getData().add(new XYChart.Data("sportif",sper.listerOne(id).getSportif()));
        series.getData().add(new XYChart.Data("artiste",sper.listerOne(id).getArtiste()));
        series.getData().add(new XYChart.Data("musicien",sper.listerOne(id).getMusicien()));
        series.getData().add(new XYChart.Data("famille",sper.listerOne(id).getFamille()));
        series.getData().add(new XYChart.Data("actif",sper.listerOne(id).getActif()));
         series.getData().add(new XYChart.Data("sociable",sper.listerOne(id).getSociable()));
        series.getData().add(new XYChart.Data("fidele",sper.listerOne(id).getFidele()));
        series.getData().add(new XYChart.Data("soigne",sper.listerOne(id).getSoigne()));
        
        series.getData().add(new XYChart.Data("genereux",sper.listerOne(id).getGenereux()));
        series.getData().add(new XYChart.Data("ambitieux",sper.listerOne(id).getAmbitieux()));
        series.getData().add(new XYChart.Data("jaloux",sper.listerOne(id).getJaloux()));
         series.getData().add(new XYChart.Data("romantique",sper.listerOne(id).getRomantique()));
        series.getData().add(new XYChart.Data("serieux",sper.listerOne(id).getSerieux()));
        series.getData().add(new XYChart.Data("drole",sper.listerOne(id).getDrole()));
        series.getData().add(new XYChart.Data("materialiste",sper.listerOne(id).getMaterialiste()));
        series.getData().add(new XYChart.Data("tolerant",sper.listerOne(id).getTolerant()));
        series.getData().add(new XYChart.Data("aventurier",sper.listerOne(id).getAventurier()));
         series.setName("Personnalité");
        AreaChart.getData().addAll(series);
        
          XYChart.Series series2= new XYChart.Series();
        series2.getData().add(new XYChart.Data("nerveux",spre.listerOne(id).getNerveux()));
        series2.getData().add(new XYChart.Data("attirant",spre.listerOne(id).getAttirant()));
        series2.getData().add(new XYChart.Data("sensible",spre.listerOne(id).getSensible()));
         series2.getData().add(new XYChart.Data("romantique",spre.listerOne(id).getRomantique()));
        series2.getData().add(new XYChart.Data("sportif",spre.listerOne(id).getSportif()));
        series2.getData().add(new XYChart.Data("artiste",spre.listerOne(id).getArtiste()));
        series2.getData().add(new XYChart.Data("musicien",spre.listerOne(id).getMusicien()));
        series2.getData().add(new XYChart.Data("famille",spre.listerOne(id).getFamille()));
        series2.getData().add(new XYChart.Data("actif",spre.listerOne(id).getActif()));
         series2.getData().add(new XYChart.Data("sociable",spre.listerOne(id).getSociable()));
        series2.getData().add(new XYChart.Data("fidele",spre.listerOne(id).getFidele()));
        series2.getData().add(new XYChart.Data("soigne",spre.listerOne(id).getSoigne()));
        
        series2.getData().add(new XYChart.Data("genereux",spre.listerOne(id).getGenereux()));
        series2.getData().add(new XYChart.Data("ambitieux",spre.listerOne(id).getAmbitieux()));
        series2.getData().add(new XYChart.Data("jaloux",spre.listerOne(id).getJaloux()));
         series2.getData().add(new XYChart.Data("romantique",spre.listerOne(id).getRomantique()));
        series2.getData().add(new XYChart.Data("serieux",spre.listerOne(id).getSerieux()));
        series2.getData().add(new XYChart.Data("drole",spre.listerOne(id).getDrole()));
        series2.getData().add(new XYChart.Data("materialiste",spre.listerOne(id).getMaterialiste()));
        series2.getData().add(new XYChart.Data("tolerant",spre.listerOne(id).getTolerant()));
        series2.getData().add(new XYChart.Data("aventurier",spre.listerOne(id).getAventurier()));
         
        series2.setName("Préférences");
        AreaChart.getData().addAll(series2);
        
    }

    @FXML
    private void terminer(ActionEvent event) {
    }
}
