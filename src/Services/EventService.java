/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Event;
import IServices.IEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ste GHRIB-INFO
 */
public class EventService  implements IEvent{
      Connection connexion;
    public EventService(){
          connexion = Mydb.getinstance().getCnx();
    }

    @Override
    public void addEvent(Event v) {
      try {
            String query = "INSERT INTO event (titre_event,type_event,texte_event,date_event,lieu_event,id_user_event) "
                    + "values ( '"+v.getTitre_Event()+"','"+v.getType_Event()+"','"+v.getTexte_Event()+"','"+v.getDate_Event()+"','"+v.getLieu_Event()+" ','"+v.getId_user_event()+"')";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }

    @Override
    public Event getEvent(int id_event){
        Event e = new Event();
  try {
          String req5 ="SELECT * FROM event WHERE id_event="+id_event;
            Statement st= connexion.createStatement();
            ResultSet rs = st.executeQuery(req5);
            while(rs.next())
            {
            int id=rs.getInt(1);
            String titre=rs.getString(3);
            String type=rs.getString(2);
            String text= rs.getString(4);
            LocalDate date= rs.getDate(5).toLocalDate();
            String lieu = rs.getString(6);
            int id_user=rs.getInt(7);
             e = new Event(id,id_user,titre,type,text,lieu,date);
            }
            
        } catch (SQLException ex) {
            System.out.println("Evenenment n'existe pas");
        }
    return e;
    }

    @Override
    public ArrayList<Event> getListEvents() {
             ArrayList<Event> listE = new ArrayList<Event>();
  try {
            String query = "SELECT * FROM event";
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
            String titre=rs.getString(3);
            String type=rs.getString(2);
            String text= rs.getString(4);
            LocalDate date= rs.getDate(5).toLocalDate();
            String lieu = rs.getString(6);
            int id_user=rs.getInt(7);
             Event e = new Event(id,id_user,titre,type,text,lieu,date);
             listE.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;
    }
    
      @Override
    public ArrayList<String> getListTypeEvents() {
             ArrayList<String> listTypeE = new ArrayList<String>();
  try {
            String query = "SELECT * FROM typeevent";
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            String type=rs.getString(1);  
             listTypeE.add(type);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listTypeE;
    }
    
    

    @Override
    public void setEvent(Event v) {
        try {
            String query = "UPDATE event SET titre_event='"+v.getTitre_Event()+"',type_event='"+v.getType_Event()+"',texte_event='"+v.getTexte_Event()+"',date_event='"+v.getDate_Event()+"',lieu_event='"+v.getLieu_Event()+"' WHERE id_event = "+v.getId_Event();
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void deleteEvent(Event e) {
   try {
            String query = "DELETE FROM event WHERE id_event="+e.getId_Event();
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Event Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Event> getListEventsUser(int id_user_ev) {
          ArrayList<Event> listE = new ArrayList<Event>();
  try {
            String query = "SELECT * FROM event where id_user_event="+id_user_ev;
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
            String titre=rs.getString(3);
            String type=rs.getString(2);
            String text= rs.getString(4);
            LocalDate date= rs.getDate(5).toLocalDate();
            String lieu = rs.getString(6);
            int id_user=rs.getInt(7);
             Event event = new Event(id,id_user,titre,type,text,lieu,date);
             listE.add(event);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;
    }

    @Override
    public ArrayList<Integer> getIdUserCibleEvent(Event e) {
 ArrayList<Integer> listE = new ArrayList<Integer>();
  try {
            String query ="SELECT idUser FROM personnalite where "+ e.getType_Event() +" > 50;";
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
          
             listE.add(id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;
    }
    
}
