/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Preference;
import Entities.Preference;
import Iservices.IPreference;
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
public class SPreference implements IPreference {

    Connection cnx;

    public SPreference() {
        cnx = Mydb.getinstance().getCnx();
    }

    @Override
    public void ajouter(Preference p) {
        try {
            String query = "INSERT INTO preference"
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
            Logger.getLogger(SPreference.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Preference listerOne(int id) {
        String query = "SELECT * FROM preference WHERE idUser=" + id;
         Preference p = new Preference();

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
            Logger.getLogger(SPreference.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<Preference> listerAll() {
        String query = "SELECT * FROM personnalite";
        List<Preference> res = new ArrayList<Preference>();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Preference p = new Preference();
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
        return (ArrayList<Preference>) res;
    }
    @Override
    public List<Preference> listerDifferent(int id) {
        String query = "SELECT * FROM personnalite WHERE idUser!="+id;
        List<Preference> res = new ArrayList<Preference>();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Preference p = new Preference();
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
        return (ArrayList<Preference>) res;
    }
 @Override //EDHEYA GUEDHA
    public boolean ajouterPourcentage(String trait, int nb, int idUser) {
        try {
            String query = "UPDATE preference SET "+trait+"="+trait+"+"+nb+" WHERE idUser="+idUser;

            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Pourcentage de "+trait+" changé dans preferences!");
        } catch (SQLException ex) {
            Logger.getLogger(SPersonnalite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
}
