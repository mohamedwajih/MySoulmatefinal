/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MySoulmateDB;
import Entities.CommentaireEvent;
import Entities.Event;
import IServices.ICommentaireEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Ste GHRIB-INFO
 */
public class CommentaireEventService implements ICommentaireEvent {
 Connection connexion;
    public CommentaireEventService(){
          connexion = MySoulmateDB.getinstance().getConnexion();
    }
    @Override
    public void addComment(CommentaireEvent c) {
         
        try {
            String query = "INSERT INTO comantaireevent (id_user,id_event,TextCom,dateC) "
                    + "values ( '"+c.getId_user()+"','"+c.getId_event()+"','"+c.getText()+"','"+java.sql.Date.valueOf(c.getDate())+"')";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        } }

    @Override
    public ArrayList<CommentaireEvent> getCommentaire(Event e) {
         ArrayList<CommentaireEvent> listC = new ArrayList<CommentaireEvent>();
  try {
            String query = "SELECT * FROM comantaireevent WHERE id_event = "+e.getId_Event();
            
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
           int id_user=rs.getInt(2);
           int id_event= rs.getByte(3);
           String text=rs.getString(4);
           LocalDate date=rs.getDate(5).toLocalDate();
           CommentaireEvent com=new CommentaireEvent(id,id_user,id_event,text,date);
           listC.add(com);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listC;
    }

    @Override
    public void deleteCommentaire(CommentaireEvent c) {
     try {
            String query = "DELETE FROM comantaireevent WHERE idC="+c.getId();
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Event Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
}
