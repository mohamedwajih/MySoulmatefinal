/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class NewFXMain extends Application {
    
    public static int idu1=6;
    public static int idu2;
    
   @Override
   public void start (Stage stage) throws IOException
<<<<<<< HEAD
   {       
       Parent  root = FXMLLoader.load(getClass().getResource("Ajouterplace.fxml"));
=======
   {   
       Parent  root = FXMLLoader.load(getClass().getResource("MatchAdmin.fxml"));

      

>>>>>>> 73bcf91ce967ae44800b588668de14ce01b0f7f1
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
       
   }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
