/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Place;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Iservices.IPlace;

/**
 *
 * @author asus
 */
public class SPlace implements IPlace  {
    
    //connection
    
     Connection cnx;
   
   public SPlace(){
       cnx=Mydb.getinstance().getCnx();
   }

   ///////////////Ajouter
   
    @Override
    public void ajouter(Place m) {
    String query="INSERT INTO places (libelle,adr,photo,type) VALUES ('"+m.getLibelle()+"','"+m.getAdr()+"','"+m.getPhoto()+"','"+m.getType()+"')";
         try {
             Statement st=cnx.createStatement();
             st.executeUpdate(query);
             System.out.println("new place added");
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
/////////////////Supprimer
    @Override
    public void supprimer(Place m) {
        String query="DELETE FROM places WHERE id_place="+m.getId_place();
        try {
             Statement st=cnx.createStatement();
             st.executeUpdate(query);
             System.out.println("place removed");
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
/////////////////Modifier
    @Override
    public void modifier(Place m) {
        String query="UPDATE places SET libelle='"+m.getLibelle()+"',adr='"+m.getAdr()+"',photo='"+m.getPhoto()+"',type='"+m.getType()+"'WHERE id_place="+m.getId_place();
        try {
             Statement st=cnx.createStatement();
             st.executeUpdate(query);
             System.out.println("place updated");
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
    }
//////////////////Lister
    @Override
    public ArrayList<Place> lister() {
        String query="SELECT * FROM places";
        List<Place> res= new ArrayList<Place>();
        
        try {
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             
             while (result.next()) { 
                 Place p=new Place();
                p.setId_place(result.getInt("id_place"));
                p.setLibelle(result.getString("libelle"));
                 p.setAdr(result.getString("adr"));
                  p.setPhoto(result.getString("photo"));
                   p.setType(result.getString("type"));
                   res.add(p);
            }
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  (ArrayList<Place>) res;
    }
//////////////////Chercher
    @Override
    public Place chercher(int id) {
        String query="SELECT * FROM places WHERE id_place="+id;
        Statement st;
        Place p=new Place();
        
         try {
             st = cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             result.first();
            p.setId_place(result.getInt(1));
            p.setLibelle( result.getString(2) );
            p.setAdr(result.getString(3));
            p.setPhoto(result.getString(4));
            p.setType(result.getString(5));
            
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
      
            
          return p;
    }
/////////////////////Rechercher
    @Override
    public boolean rechercher(Place m) {
        
        
        String query="SELECT * FROM places WHERE id_place="+m.getId_place();
        ResultSet result=null;
         try {
              Statement st = cnx.createStatement();
              result = st.executeQuery(query);

         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             if(result.next())
                 return true;
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
            
   
}

    @Override
    public ArrayList<Place> rechercher(String s) {
         String query="SELECT * FROM places WHERE libelle LIKE '%"+s+"%' OR adr LIKE '%"+s+"%'" ;
        List<Place> res= new ArrayList<Place>();
        
        try {
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             
             while (result.next()) { 
                 Place p=new Place();
                p.setId_place(result.getInt("id_place"));
                p.setLibelle(result.getString("libelle"));
                 p.setAdr(result.getString("adr"));
                  p.setPhoto(result.getString("photo"));
                   p.setType(result.getString("type"));
                   res.add(p);
            }
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  (ArrayList<Place>) res;
    }
    }

