/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DataStorage.Mydb;
import Entities.Fos_user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class amiaffichage {
    
     java.sql.Connection cnx;
    
    public amiaffichage() {
        cnx=Mydb.getinstance().getCnx();
    }
    
     public ArrayList<Fos_user> listerall(int id) {
         
        String query1="SELECT * FROM amis WHERE ( id_u1="+id+" OR id_u2="+id+" ) AND etat=1";
        List<Fos_user> l1= new ArrayList<Fos_user>();
        int idu=0;
         try {
            Statement st1=cnx.createStatement();
            Statement st2=cnx.createStatement();
            ResultSet res= st1.executeQuery(query1);
             while (res.next()){
                 if(res.getInt("id_u1")==id){
                      idu=res.getInt("id_u2");
                 }else {
                      idu=res.getInt("id_u1");
                 }
                    System.out.println(idu);  
                 String query2="SELECT * FROM fos_user WHERE id="+idu;
                 ResultSet res1= st2.executeQuery(query2);
                 res1.next();
                 Fos_user a = new Fos_user();
                  a.setUsername(res1.getString("username"));
                  a.setId(res1.getInt("id"));
                  a.setPhoto_de_profil(res1.getString("photo_de_profil"));
                  a.setAge(res1.getInt("age"));
                  
                 l1.add(a);
             }
        } catch (SQLException ex) {
            Logger.getLogger(amiaffichage.class.getName()).log(Level.SEVERE, null, ex);
        }
         return (ArrayList<Fos_user>) l1;    
    }
     /////////////////////////////////////////////////////////////////////
     public ArrayList<Fos_user> listerconfirmer(int id) {
         
        String query1="SELECT * FROM amis WHERE (( id_u1="+id+" OR id_u2="+id+" ) AND etat=0 )";
        List<Fos_user> l1= new ArrayList<Fos_user>();
        int idu=0;
         try {
            Statement st1=cnx.createStatement();
            Statement st2=cnx.createStatement();
            ResultSet res= st1.executeQuery(query1);
             while (res.next()){
                 if(res.getInt("id_u1")==id){
                      idu=res.getInt("id_u2");
                 }else {
                      idu=res.getInt("id_u1");
                 }
                    System.out.println(idu);  
                 String query2="SELECT * FROM fos_user WHERE id="+idu;
                 ResultSet res1= st2.executeQuery(query2);
                 res1.next();
                 Fos_user a = new Fos_user();
                  a.setUsername(res1.getString("username"));
                  a.setId(res1.getInt("id"));
                  a.setPhoto_de_profil(res1.getString("photo_de_profil"));
                  a.setAge(res1.getInt("age"));
                  
                 l1.add(a);
             }
        } catch (SQLException ex) {
            Logger.getLogger(amiaffichage.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return (ArrayList<Fos_user>) l1;    
    }
     //////////////////////////////////////////////////////////////////////////
     public Fos_user getphotouserbyid(int id) {
         String query="SELECT * FROM fos_user WHERE id="+id;
        
         ResultSet res;
         Fos_user fs=new Fos_user();
         try {
              Statement st=cnx.createStatement();
             res = st.executeQuery(query);
             res.first();
             fs.setId(res.getInt("id"));
             fs.setPhoto_de_profil(res.getString("photo_de_profil"));
             fs.setUsername(res.getString("username"));
             fs.setAge(res.getInt("age"));
             fs.setAdresse(res.getString("adresse"));
             fs.setPrenom(res.getString("prenom"));
         
         } catch (SQLException ex) {
             Logger.getLogger(amiaffichage.class.getName()).log(Level.SEVERE, null, ex);
         }
           return fs;     
     }
    
}
