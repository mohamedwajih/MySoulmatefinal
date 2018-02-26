/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Matching;
import Entities.Personnalite;
import Iservices.IPersonnalite;
import DataStorage.Mydb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatma
 */
public class SPersonnalite implements IPersonnalite {

    Connection cnx;

    public SPersonnalite() {
        cnx = Mydb.getinstance().getCnx();
    }

    @Override
    public void ajouter(Personnalite p) {
        try {
            String query = "INSERT INTO personnalite"
                    + " VALUES (" + p.getIdUser() + "," + p.getNerveux() + "," + p.getAttirant()
                    + "," + p.getSensible() + "," + p.getRomantique() + "," + p.getSportif() + ","
                    + p.getArtiste() + "," + p.getMusicien() + "," + p.getFamille() + "," + p.getActif() + "," + p.getSociable() + ","
                    + p.getFidele() + "," + p.getSoigne() + "," + p.getGenereux() + "," + p.getAmbitieux() + "," + p.getJaloux()
                    + "," + p.getSerieux() + "," + p.getDrole() + "," + p.getMaterialiste() + ","
                    + p.getTolerant() + "," + p.getAventurier() + ")";

            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Personnalitée ajoutée!");
        } catch (SQLException ex) {
            Logger.getLogger(SPersonnalite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Personnalite listerOne(int id) {
        String query = "SELECT * FROM personnalite WHERE idUser=" + id;
        Personnalite p = new Personnalite();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                
                p.setIdUser(result.getInt("idUser"));
                p.setNerveux(result.getInt("Nerveux"));
                p.setAttirant(result.getInt("Attirant"));
                p.setSensible(result.getInt("Sensible"));
                p.setRomantique(result.getInt("Romantique"));
                p.setSportif(result.getInt("Sportif"));
                p.setArtiste(result.getInt("Artiste"));
                p.setMusicien(result.getInt("Musicien"));
                p.setFamille(result.getInt("Famille"));
                p.setActif(result.getInt("Actif"));
                p.setSociable(result.getInt("Sociable"));
                p.setFidele(result.getInt("Fidele"));
                p.setSoigne(result.getInt("Soigne"));
                p.setGenereux(result.getInt("Genereux"));
                p.setAmbitieux(result.getInt("Ambitieux"));
                p.setJaloux(result.getInt("Jaloux"));
                p.setSerieux(result.getInt("Serieux"));
                p.setDrole(result.getInt("Drole"));
                p.setMaterialiste(result.getInt("Materialiste"));
                p.setTolerant(result.getInt("Tolerant"));
                p.setAventurier(result.getInt("Aventurier"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(SPersonnalite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Personnalite> listerAll() {
        String query = "SELECT * FROM personnalite";
        List<Personnalite> res = new ArrayList<Personnalite>();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Personnalite p = new Personnalite();
                p.setIdUser(result.getInt("idUser"));
                p.setNerveux(result.getInt("Nerveux"));
                p.setAttirant(result.getInt("Attirant"));
                p.setSensible(result.getInt("Sensible"));
                p.setRomantique(result.getInt("Romantique"));
                p.setSportif(result.getInt("Sportif"));
                p.setArtiste(result.getInt("Artiste"));
                p.setMusicien(result.getInt("Musicien"));
                p.setFamille(result.getInt("Famille"));
                p.setActif(result.getInt("Actif"));
                p.setSociable(result.getInt("Sociable"));
                p.setFidele(result.getInt("Fidele"));
                p.setSoigne(result.getInt("Soigne"));
                p.setGenereux(result.getInt("Genereux"));
                p.setAmbitieux(result.getInt("Ambitieux"));
                p.setJaloux(result.getInt("Jaloux"));
                p.setSerieux(result.getInt("Serieux"));
                p.setDrole(result.getInt("Drole"));
                p.setMaterialiste(result.getInt("Materialiste"));
                p.setTolerant(result.getInt("Tolerant"));
                p.setAventurier(result.getInt("Aventurier"));
                res.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMatching.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Personnalite>) res;
    }
public List<Personnalite> listerDifferent(int id) {
        String query = "SELECT * FROM personnalite WHERE idUser!="+id;
        List<Personnalite> res = new ArrayList<Personnalite>();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Personnalite p = new Personnalite();
                p.setIdUser(result.getInt("idUser"));
                p.setNerveux(result.getInt("Nerveux"));
                p.setAttirant(result.getInt("Attirant"));
                p.setSensible(result.getInt("Sensible"));
                p.setRomantique(result.getInt("Romantique"));
                p.setSportif(result.getInt("Sportif"));
                p.setArtiste(result.getInt("Artiste"));
                p.setMusicien(result.getInt("Musicien"));
                p.setFamille(result.getInt("Famille"));
                p.setActif(result.getInt("Actif"));
                p.setSociable(result.getInt("Sociable"));
                p.setFidele(result.getInt("Fidele"));
                p.setSoigne(result.getInt("Soigne"));
                p.setGenereux(result.getInt("Genereux"));
                p.setAmbitieux(result.getInt("Ambitieux"));
                p.setJaloux(result.getInt("Jaloux"));
                p.setSerieux(result.getInt("Serieux"));
                p.setDrole(result.getInt("Drole"));
                p.setMaterialiste(result.getInt("Materialiste"));
                p.setTolerant(result.getInt("Tolerant"));
                p.setAventurier(result.getInt("Aventurier"));
                res.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SMatching.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Personnalite>) res;
    }

    @Override //EDHEYA GUEDHA
    public boolean ajouterPourcentage(String trait, int nb, int idUser) {
        try {
            String query = "UPDATE personnalite SET "+trait+"="+trait+"+"+nb+" WHERE idUser="+idUser;

            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Pourcentage de "+trait+" changé dans personnalite!");
        } catch (SQLException ex) {
            Logger.getLogger(SPersonnalite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public float moyenne(String trait) {
String query = "SELECT  AVG("+trait+") AS moyenne FROM personnalite";
      float res=0;

    
            
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);
        result.first();
        res=result.getFloat("moyenne");
            
        }
            
         catch (SQLException ex) {
            Logger.getLogger(SPersonnalite.class.getName()).log(Level.SEVERE, null, ex);
        }
            return res;
                        }
     @Override
    public int nbpourcentagetrait(String trait, int nb) {
String query="SELECT COUNT("+trait+") AS nombre FROM personnalite WHERE "+trait+"="+nb;
        
        int res=0;
        try {
           
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             result.first();
             res=result.getInt("nombre");
             
            
         } catch (SQLException ex) {
             Logger.getLogger(SMatching.class.getName()).log(Level.SEVERE, null, ex);
         }
        System.out.println("res="+res);
         return res;    
  
   }  
}
