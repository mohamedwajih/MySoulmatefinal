/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Event;
import Iservices.IEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String query = "INSERT INTO event (titre_event,type_event,texte_event,date_event,lieu_event,id_user_event,nbr_part,image) "
                    + "values ( '"+v.getTitre_Event()+"','"+v.getType_Event()+"','"+v.getTexte_Event()+"','"+v.getDate_Event()+"','"+v.getLieu_Event()+" ','"+v.getId_user_event()+" ','"+v.getPart()+" ','"+v.getImage()+"')";
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
            int par=rs.getInt(8);
            int id_user=rs.getInt(7);
            String image=rs.getString(9);
             e = new Event(id,id_user,titre,type,text,lieu,date,par,image);
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
            String query = "SELECT * FROM event WHERE date_event > CURDATE()";
            
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
            int par=rs.getInt(8);
            String image= rs.getString(9);
             Event e = new Event(id,id_user,titre,type,text,lieu,date,par,image);
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
            String query = "UPDATE event SET titre_event='"+v.getTitre_Event()+"',type_event='"+v.getType_Event()+"',texte_event='"+v.getTexte_Event()+"',date_event='"+v.getDate_Event()+"',lieu_event='"+v.getLieu_Event()+"',image='"+v.getImage()+"' WHERE id_event = "+v.getId_Event();
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
            String query = "SELECT * FROM event WHERE date_event >=CURDATE() and id_user_event="+id_user_ev;
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
            int par=rs.getInt(8);
           String image= rs.getString(9);
             Event e = new Event(id,id_user,titre,type,text,lieu,date,par,image);
             listE.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;
    }

    @Override
    public ArrayList<Integer> getIdUserCibleEvent(Event e) {
 ArrayList<Integer> listE = new ArrayList<Integer>();
  try {     if (e.getType_Event().equals("international")){
       String query ="SELECT idUser FROM personnalite";
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
          
             listE.add(id);
            }
            }
   else{
            String query ="SELECT idUser FROM personnalite where "+ e.getType_Event() +" > 50;";
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
          
             listE.add(id);
            }
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;
    }

    @Override
    public int getIdEvent(Event e) {
      int id=0;
  try {
          String req5 ="SELECT id_event FROM event WHERE titre_event= '"+e.getTitre_Event()+"' and type_event='"+e.getType_Event()+"' and texte_event= '"+e.getTexte_Event()+"' and date_event= '"+e.getDate_Event()+"' and lieu_event= '"+ e.getLieu_Event()+ "' and id_user_event= '" + e.getId_user_event()+"'";
            Statement st= connexion.createStatement();
            ResultSet rs = st.executeQuery(req5);
            while(rs.next())
            {
             id=rs.getInt(1);
          
            }
            
        } catch (SQLException ex) {
            System.out.println("Evenenment n'existe pas");
        }
    return id;
    }

    @Override
    public void setParticipation(Event e) {
        try {
            String query = "UPDATE event SET nbr_part='"+e.getPart()+"' WHERE id_event = "+e.getId_Event();
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void archverEvennement(Event e) { 
        try {
            String query = "INSERT INTO archiveevent (titre_event,type_event,texte_event,date_event,lieu_event,id_user_event,nbr_part,image) "
                    + "values ( '"+e.getTitre_Event()+"','"+e.getType_Event()+"','"+e.getTexte_Event()+"','"+e.getDate_Event()+"','"+e.getLieu_Event()+" ','"+e.getId_user_event()+" ','"+e.getPart()+" ','"+e.getImage()+"')";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }

    @Override
    public ArrayList<Event> getArchive(int id_user) {
          ArrayList<Event> listE = new ArrayList<Event>();
  try {
            String query = "SELECT * FROM archiveevent where id_user_event="+id_user;
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
            int id_user_e=rs.getInt(7);
            int par=rs.getInt(8);
           String image = rs.getString(9);
             Event event = new Event(id,id_user_e,titre,type,text,lieu,date,par,image);
             listE.add(event);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;
    
    }

    @Override
    public Event getEventArchiv(int id) {
        
          Event e = new Event();
  try {
            String query = "SELECT * FROM archiveevent where id_archve="+id;
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int idA=rs.getInt(1);
            String titre=rs.getString(3);
            String type=rs.getString(2);
            String text= rs.getString(4);
            LocalDate date= rs.getDate(5).toLocalDate();
            String lieu = rs.getString(6);
            int id_user_e=rs.getInt(7);
            int par=rs.getInt(8);
            String image = rs.getString(9);
              e = new Event(id,id_user_e,titre,type,text,lieu,date,par,image);
            
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  return e;
      
    }

    @Override
    public void deletEventArchive(Event e) {
        
        
         try {
            String query = "DELETE FROM archiveevent WHERE id_archve="+e.getId_Event();
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Event Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
    
    }

    @Override
    public ArrayList<Event> getListEventPassé() {
               ArrayList<Event> listE = new ArrayList<Event>();
  try {
            String query = "SELECT * FROM event WHERE date_event < CURDATE()";
            
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
            int par=rs.getInt(8);
            String image= rs.getString(9);
             Event e = new Event(id,id_user,titre,type,text,lieu,date,par,image);
             listE.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;    
    
    }

    @Override
    public ArrayList<Event> getListEventsUserPassé(int id_user_ev) {
     ArrayList<Event> listE = new ArrayList<Event>();
  try {
            String query = "SELECT * FROM event WHERE date_event < CURDATE() and id_user_event="+id_user_ev;
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
            int par=rs.getInt(8);
            String image= rs.getString(9);
             Event event = new Event(id,id_user,titre,type,text,lieu,date,par,image);
             listE.add(event);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE;   
    
    }
     @Override
    public List<Event> rechercher(String event) {
         ArrayList<Event> listE = new ArrayList<Event>();
       try { 
        String query = "SELECT * FROM event WHERE titre_event LIKE '%" + event + "%' OR type_event LIKE '%" + event
                + "%' OR texte_event LIKE '%" + event + "%' OR lieu_event LIKE '%"+ event + "%'";

       
        
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) 
                {
            int id=rs.getInt(1);
            String titre=rs.getString(3);
            String type=rs.getString(2);
            String text= rs.getString(4);
            LocalDate date= rs.getDate(5).toLocalDate();
            String lieu = rs.getString(6);
            int id_user=rs.getInt(7);
            int par=rs.getInt(8);
            String image= rs.getString(9);
             Event e = new Event(id,id_user,titre,type,text,lieu,date,par,image);
             listE.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
      return listE;
        }
    
      @Override
    public void modifierImage(String image,int id) {
        try {
            String query = "UPDATE event SET image='"+image+"' where id='"+id+"'";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
        }

    }
}
