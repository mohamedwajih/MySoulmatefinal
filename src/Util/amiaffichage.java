/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DataStorage.Mydb;
import Entities.Fos_user;
import Entities.Rdv;
import Services.SRdv;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                  a.setPrenom(res1.getString("prenom"));
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
                  a.setAdresse(res1.getString("adresse"));
                  a.setPrenom(res1.getString("prenom"));
                  a.setNum_tel(res1.getInt("num_tel"));
                  a.setNom(res1.getString("nom"));
                  
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
             fs.setNom(res.getString("nom"));
             fs.setAdresse(res.getString("adresse"));
             fs.setPrenom(res.getString("prenom"));
             fs.setNum_tel(res.getInt("num_tel"));
         
         } catch (SQLException ex) {
             Logger.getLogger(amiaffichage.class.getName()).log(Level.SEVERE, null, ex);
         }
           return fs;     
     }
     
     ////////////////////////////////////////////////////////////////////////
     
     public int getpourcent(int id1,int id2){
         String query="SELECT pourcentage FROM matching WHERE idUser1="+id1+" AND idUser2="+id2;
         int p = 0;
         try {
             Statement st=cnx.createStatement();
             ResultSet rs=st.executeQuery(query);
             rs.first();
             p=rs.getInt("pourcentage");
         } catch (SQLException ex) {
             Logger.getLogger(amiaffichage.class.getName()).log(Level.SEVERE, null, ex);
         }
         return p;
     }
    //////////////////////////////////////////////////////////////
     public String getpref(int id){
         String res="";
         try {
             String query="SELECT sportif,artiste,aventurier,sociable FROM  personnalite WHERE idUser="+id;
             Statement st=cnx.createStatement();
             Map<String,Integer> map=new HashMap<String,Integer>();
             ResultSet rs=st.executeQuery(query);
             rs.first();
             map.put("sportif",rs.getInt("sportif"));
              map.put("artiste",rs.getInt("artiste"));
               map.put("aventurier",rs.getInt("aventurier"));
                map.put("sociable",rs.getInt("sociable"));
             res=map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

             
         } catch (SQLException ex) {
             Logger.getLogger(amiaffichage.class.getName()).log(Level.SEVERE, null, ex);
         }
         return res;
     }
     ////////////////////////////////////////////////////////
     public int daysleft(Rdv r){
         int res=0;
         try {
             String query="SELECT round(extract(day from date) - extract(day from sysdate())) AS days ,round(extract(month from date) - extract(month from sysdate())) AS month FROM rdv WHERE id_1="+r.getId1()+" AND id_2="+r.getId2()+" AND date='"+r.getDate()+"'";
             Statement st=cnx.createStatement();
             ResultSet rs= st.executeQuery(query);
             rs.first();
             int day=rs.getInt("days");
             int mon=rs.getInt("month");
             res=day+(mon*30);
             
             
         } catch (SQLException ex) {
             Logger.getLogger(amiaffichage.class.getName()).log(Level.SEVERE, null, ex);
         }
     return res;
     }
}
