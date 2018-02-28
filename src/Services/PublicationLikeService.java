/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Publication;
import Entities.PublicationLike;
//import iServices.ipublicationLike;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author feriel
 */

public class PublicationLikeService {
  Connection cnx;

    public PublicationLikeService() {
        this.cnx=Mydb.getinstance().getCnx();
    }  
    
    public boolean getUserLike(PublicationLike pl) {
        
      try {      
          String requete = "select user_id  from publication_like where user_id ='"+pl.getIdUser()+"' and pub_id ='"+pl.getIdPub()+"'";
             PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
              return true;
            }
            else{
                return false;
            }               
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
      return true;
    }
     public void ajouterLike(PublicationLike pl) {
        
      try {      
             String requete1="INSERT INTO publication_like (pub_id,user_id) VALUES ('"+pl.getIdPub()+"','"+pl.getIdUser()+"')";
            Statement st1=cnx.createStatement();
            st1.executeUpdate(requete1);
            
            System.out.println("like ajouté !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }
     
      public int getNumberLike(int p) {
        String requete = "select count(id) nb from publication_like where pub_id ='"+p+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int nbLike=rs.getInt("nb");
                
              return nbLike;
               // System.out.println(" publication succ");
            }

        } catch (SQLException ex) {
            System.out.println("erreur " + ex.getMessage());
        }   
    return 0;    }
}
