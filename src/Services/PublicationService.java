/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Publication;
import Iservices.ipublication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author feriel
 */

public class PublicationService implements ipublication {
 Connection cnx;

    public PublicationService() {
        this.cnx=Mydb.getinstance().getCnx();
    }
    @Override
    public void ajouterPublication(Publication p) {
        Date d =new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String s = df.format(d);
        String i = "bluewave.png";
      try {      
             String requete1="INSERT INTO publication (contenu_pub,date_pub,image,video,id_user) VALUES ('"+p.getContenu_pub()+"','"+s+"','"+p.getImagePublication()+"','"+p.getVideoPublication()+"','1')";
            Statement st1=cnx.createStatement();
            st1.executeUpdate(requete1);
            
            System.out.println("pub ajouté !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }

    @Override
    public void supprimerPublication(Publication p) {
        try {           
            
             String query = "DELETE  FROM publication where id_pub = '"+p.getId_pub()+"'";
                          System.out.println("idddd"+p.getId_pub());

      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.executeUpdate();

      // execute the preparedstatement
    
            System.out.println("supprime pub");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }}

   

    @Override
    public List<Publication> consulterPublication() {
      
             ArrayList<Publication> listN = new ArrayList<Publication>();
  try {
            String query = "SELECT * from publication ORDER BY id_pub DESC";
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
           
                 int id_pub=rs.getInt("id_pub");
                 String contenu_pub=rs.getString("contenu_pub");
                 String date_pub=rs.getString("date_pub");
                  String imagePresentation=rs.getString("image");
                 String videoPresentation=rs.getString("video");
                listN.add(new Publication(id_pub,contenu_pub,date_pub,imagePresentation,videoPresentation));
                System.out.println(imagePresentation+"ssssssss");
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return listN;
       }

    @Override
    public void modifierPublication(Publication pub, String contenu_pub,String date_pub) {
        
         String req ="UPDATE publication SET contenu_pub=?,date_pub=? WHERE id_pub=?";
       try
       {PreparedStatement ps=cnx.prepareCall(req);
       ps.setInt(3, pub.getId_pub());
       ps.setString(1, contenu_pub);
       ps.setString(2, date_pub);
       ps.executeUpdate();
           System.out.println("pub modifié!");
       
           
       }
       catch (SQLException ex){
           
           System.out.println(ex.getMessage());
       }
    }

    @Override
    public void modifierpub(String contenu_pub1,int id_pub1) {
        try {           
          Date d =new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String s = df.format(d);
            String requete3= "UPDATE publication SET contenu_pub='"+contenu_pub1+"' WHERE id_pub='"+id_pub1+"'";
            
            Statement st3=cnx.createStatement();
            st3.executeUpdate(requete3);
            System.out.println("pub modifié !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }
    
 public Publication rechercherPublicationsById(int i) {
        String requete = "select * from publication where id_pub ='"+i+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String contenu_pub=rs.getString("contenu_pub");
                 String date_pub=rs.getString("date_pub");
                  String imagePresentation=rs.getString("image");
                 String videoPresentation=rs.getString("video");
              return(  new Publication(i,contenu_pub,date_pub,imagePresentation,videoPresentation));
               // System.out.println(" publication succ");
            }

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du compte " + ex.getMessage());
        }   
    return null;    }

   
    }
    
  

    

    
    
    

