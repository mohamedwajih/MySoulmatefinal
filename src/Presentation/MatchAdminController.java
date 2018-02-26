/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entities.Fos_user;
import Entities.Matching;
import Services.SMatching;
import Services.SPersonnalite;
import Services.SPreference;
import Services.SUser;
import Util.util;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Fatma
 */
public class MatchAdminController implements Initializable {

   
   
   
   
    SUser su= new SUser();
    SMatching sm= new SMatching(); 
    
    @FXML
    private BarChart<String, Float> BarChart;
    @FXML
    private NumberAxis axepourcentage;
    @FXML
    private CategoryAxis axetraits;
    @FXML
    private LineChart<String, Integer> LineChart;
    @FXML
    private Label nerveuxper;
    @FXML
    private Label attirantper;
    @FXML
    private Label sensibleper;
    @FXML
    private Label romantiqueper;
    @FXML
    private Label sporitfper;
    @FXML
    private Label artisteper;
    @FXML
    private Label musicienper;
    @FXML
    private Label familleper;
    @FXML
    private Label actifper;
    @FXML
    private Label sociableper;
    @FXML
    private Label fideleper;
    @FXML
    private Label soigneper;
    @FXML
    private Label genereuxper;
    @FXML
    private Label ambitieuxper;
    @FXML
    private Label jalouxper;
    @FXML
    private Label serieuxper;
    @FXML
    private Label materialisteper;
    @FXML
    private Label tolerantper;
    @FXML
    private Label aventurierper;
    @FXML
    private Label droleper;
    @FXML
    private Label nerveuxpre;
    @FXML
    private Label attirantpre;
    @FXML
    private Label sensiblepre;
    @FXML
    private Label romantiquepre;
    @FXML
    private Label sporitfpre;
    @FXML
    private Label artistepre;
    @FXML
    private Label musicienpre;
    @FXML
    private Label famillepre;
    @FXML
    private Label actifpre;
    @FXML
    private Label sociablepre;
    @FXML
    private Label fidelepre;
    @FXML
    private Label soignepre;
    @FXML
    private Label genereuxpre;
    @FXML
    private Label ambitieuxpre;
    @FXML
    private Label jalouxpre;
    @FXML
    private Label serieuxpre;
    @FXML
    private Label drolepre;
    @FXML
    private Label materialistepre;
    @FXML
    private Label tolerantpre;
    @FXML
    private Label aventurierpre;
     SPersonnalite sper = new SPersonnalite();
    SPreference spre= new SPreference();
    @FXML
    private Label labelmatch;
    @FXML
    private Label labelselection;
    @FXML
    private PieChart PieChartPersonnalite;
    @FXML
    private PieChart PieChartPreference;
    @FXML
    private ChoiceBox<String> trait;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       moyennes();
              statmoyennes();
    statmatchs();
    }    
    public void moyennes()
    {
         //PERSONNALITE MOYENNE
        actifper.setText(actifper.getText()+" - "+sper.moyenne("actif")+"%");
        ambitieuxper.setText(ambitieuxper.getText()+" - "+sper.moyenne("ambitieux")+"%");
        artisteper.setText(artisteper.getText()+" - "+sper.moyenne("artiste")+"%");
        attirantper.setText(attirantper.getText()+" - "+sper.moyenne("attirant")+"%");
        aventurierper.setText(aventurierper.getText()+" - "+sper.moyenne("aventurier")+"%");
        droleper.setText(droleper.getText()+" - "+sper.moyenne("drole")+"%");
        familleper.setText(familleper.getText()+" - "+sper.moyenne("famille")+"%");
        fideleper.setText(fideleper.getText()+" - "+sper.moyenne("fidele")+"%");
        genereuxper.setText(genereuxper.getText()+" - "+sper.moyenne("genereux")+"%");
        jalouxper.setText(jalouxper.getText()+" - "+sper.moyenne("jaloux")+"%");
        materialisteper.setText(materialisteper.getText()+" - "+sper.moyenne("materialiste")+"%");
        musicienper.setText(musicienper.getText()+" - "+sper.moyenne("musicien")+"%");
        nerveuxper.setText(nerveuxper.getText()+" - "+sper.moyenne("nerveux")+"%");
        romantiqueper.setText(romantiqueper.getText()+" - "+sper.moyenne("romantique")+"%");
        sensibleper.setText(sensibleper.getText()+" - "+sper.moyenne("sensible")+"%");
        serieuxper.setText(serieuxper.getText()+" - "+sper.moyenne("serieux")+"%");
        sociableper.setText(sociableper.getText()+" - "+sper.moyenne("sociable")+"%");
        soigneper.setText(soigneper.getText()+" - "+sper.moyenne("soigne")+"%");
        sporitfper.setText(sporitfper.getText()+" - "+sper.moyenne("sportif")+"%");
        tolerantper.setText(tolerantper.getText()+" - "+sper.moyenne("tolerant")+"%");
       
        //PREFERENCES MOYENNE
        actifpre.setText(actifpre.getText()+" - "+spre.moyenne("actif")+"%");
        ambitieuxpre.setText(ambitieuxpre.getText()+" - "+spre.moyenne("ambitieux")+"%");
        artistepre.setText(artistepre.getText()+" - "+spre.moyenne("artiste")+"%");
        attirantpre.setText(attirantpre.getText()+" - "+spre.moyenne("attirant")+"%");
        aventurierpre.setText(aventurierpre.getText()+" - "+spre.moyenne("aventurier")+"%");
        drolepre.setText(drolepre.getText()+" - "+spre.moyenne("drole")+"%");
        famillepre.setText(famillepre.getText()+" - "+spre.moyenne("famille")+"%");
        fidelepre.setText(fidelepre.getText()+" - "+spre.moyenne("fidele")+"%");
        genereuxpre.setText(genereuxpre.getText()+" - "+spre.moyenne("genereux")+"%");
        jalouxpre.setText(jalouxpre.getText()+" - "+spre.moyenne("jaloux")+"%");
        materialistepre.setText(materialistepre.getText()+" - "+spre.moyenne("materialiste")+"%");
        musicienpre.setText(musicienpre.getText()+" - "+spre.moyenne("musicien")+"%");
        nerveuxpre.setText(nerveuxpre.getText()+" - "+spre.moyenne("nerveux")+"%");
        romantiquepre.setText(romantiquepre.getText()+" - "+spre.moyenne("romantique")+"%");
        sensiblepre.setText(sensiblepre.getText()+" - "+spre.moyenne("sensible")+"%");
        serieuxpre.setText(serieuxpre.getText()+" - "+spre.moyenne("serieux")+"%");
        sociablepre.setText(sociablepre.getText()+" - "+spre.moyenne("sociable")+"%");
        soignepre.setText(soignepre.getText()+" - "+spre.moyenne("soigne")+"%");
        sporitfpre.setText(sporitfpre.getText()+" - "+spre.moyenne("sportif")+"%");
        tolerantpre.setText(tolerantpre.getText()+" - "+spre.moyenne("tolerant")+"%");
    }
    // BAR CHART MOYENNES
public void statmoyennes(){
        XYChart.Series series = new XYChart.Series<>();
                List<String> traits= new ArrayList<>(util.listerTraits()); 
                for(int i=0; i<traits.size();i++){
                    series.getData().add(new XYChart.Data(traits.get(i),sper.moyenne(traits.get(i))));
                }
                series.setName("Personnalité");
                BarChart.getData().addAll(series);
                
                XYChart.Series series2 = new XYChart.Series<>();
                 
                for(int i=0; i<traits.size();i++){
                    series2.getData().add(new XYChart.Data(traits.get(i),spre.moyenne(traits.get(i))));
                }
                series2.setName("Préférences");
                
                BarChart.getData().addAll(series2);
    }
// LINE CHART MATCHS
public void statmatchs(){
        XYChart.Series series = new XYChart.Series<>();
                
                for(int i=50; i<=100;i++){
                    series.getData().add(new XYChart.Data(i+"",sm.nbpourcentage(i)));
                }
                series.setName("Nombre de matchs");
                LineChart.getData().addAll(series);
                
      
    }
    @FXML
    private void visualiser(ActionEvent event) {
//PIE CHART PERSONNALITE
   
    ObservableList<PieChart.Data> details= FXCollections.observableArrayList(
   
    new PieChart.Data(0+"%", sper.nbpourcentagetrait(trait.getValue(),0)),
            new PieChart.Data(25+"%", sper.nbpourcentagetrait(trait.getValue(),25)),
                    new PieChart.Data(50+"%", sper.nbpourcentagetrait(trait.getValue(),50)),
                            new PieChart.Data(75+"%", sper.nbpourcentagetrait(trait.getValue(),75)),
                            new PieChart.Data(100+"%", sper.nbpourcentagetrait(trait.getValue(),100))
            
    );
     PieChartPersonnalite.setData(details);
     
    //PIE CHART PREFERENCE 
    ObservableList<PieChart.Data> details2= FXCollections.observableArrayList(
   
    new PieChart.Data(0+"%", spre.nbpourcentagetrait(trait.getValue(),0)),
            new PieChart.Data(25+"%", spre.nbpourcentagetrait(trait.getValue(),25)),
                    new PieChart.Data(50+"%", spre.nbpourcentagetrait(trait.getValue(),50)),
                            new PieChart.Data(75+"%", spre.nbpourcentagetrait(trait.getValue(),75)),
                            new PieChart.Data(100+"%", spre.nbpourcentagetrait(trait.getValue(),100))
            
    );
     PieChartPreference.setData(details2);
    } 

}
    