/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Commentaire;
import Entities.Publication;
import Iservices.icommentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author feriel
 */
public class CommentaireService implements icommentaire{
    Connection cnx;

    public CommentaireService() {
        this.cnx=Mydb.getinstance().getCnx();
    }

    public void ajouterCommentaire(Commentaire c) {
        
        Date d =new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String s = df.format(d);
      try {      
             String requete1="INSERT INTO commentaire (id_pub,contenu_com,date_com) VALUES ('"+c.getId_pub()+"','"+c.getContenu_com()+"','"+s+"') ";
            Statement st1=cnx.createStatement();
            st1.executeUpdate(requete1);
            
            System.out.println("Commentaire ajouté !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }

    @Override
    public void supprimerCommentaire(Commentaire c) {
        try {           
      
             String query = "DELETE  FROM commentaire where id = '"+c.getId_com()+"'";

      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.executeUpdate();

      // execute the preparedstatement
    
            System.out.println("supprime com");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }

    }

    @Override
    public void modifierCommentaire(Commentaire c) {
      try {           
          Date d =new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd/hh-mm");
        String s = df.format(d);
            String requete3= "UPDATE commentaire SET contenu_com='"+c.getContenu_com()+"' WHERE id='"+c.getId_com()+"'";
            
            Statement st3=cnx.createStatement();
            st3.executeUpdate(requete3);
            System.out.println("commentaire modifié !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }

    

    
    public List<Commentaire> consulterComCom(int idpub) {
        
             ArrayList<Commentaire> listN = new ArrayList<Commentaire>();
  try {
            String query = "SELECT * from commentaire WHERE id_pub="+idpub;
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
                //    int id_pub=rs.getInt("id_pub");
                 int id_com=rs.getInt("id");
                 String contenu_com=rs.getString("contenu_com");
                 String date_com=rs.getString("date_com");
              
                listN.add(new Commentaire(id_com,contenu_com,date_com));
              
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  return listN;
    
    
    
    }
 public List<Commentaire> consulterCommentaireTest() {
      
             ArrayList<Commentaire> listN = new ArrayList<Commentaire>();
  try {
            String query = "SELECT * from commentaire ORDER BY id_pub DESC";
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
           
                 int id_com=rs.getInt("id");
                 String contenu_com=rs.getString("contenu_com");
                 String date_com=rs.getString("date_com");
                
                listN.add(new Commentaire(id_com,contenu_com,date_com));
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return listN;
       }

    @Override
    public List<Commentaire> consulterCom(Publication p) {
   
             ArrayList<Commentaire> listN = new ArrayList<Commentaire>();
  try {
            String query = "SELECT * from commentaire WHERE id_pub="+p.getId_pub();
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
           
                 //int id_com=rs.getInt("id_com");
                 //int id_pub=rs.getInt("id_pub");
                 String contenu_com=rs.getString("contenu_com");
                 String date_com=rs.getString("date_com");
              
                listN.add(new Commentaire(contenu_com,date_com));
              
            }
             
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return listN;
    }

    @Override
    public List<Commentaire> consulterComCom(Commentaire c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    



}


    
       
        
        
        
        

