
package Services;

import DataStorage.MySoulmateDB;
import Entities.Event;
import Entities.Notification;
import IServices.INotification;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class NotificationServices implements INotification {
 Connection connexion;
    public NotificationServices(){
          connexion = MySoulmateDB.getinstance().getConnexion();
    }
    @Override
    public void addNotification(Notification n) {
        try {
            String query = "INSERT INTO notification (type_notification,texte_notification,date_notification,id_user_notif,etat_notif,id_origine,image) "
                    + "values ( '"+n.getType_notification()+"','"+n.getTexte_notification()+"','"+java.sql.Date.valueOf(n.getDate_notification())+" ','"+n.getId_user_notif()+"',0,'"+n.getId_origine()+" ','"+n.getImage()+"')";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }

   /* @Override
    public Notification getNotification(Notification n) {
  Notification N = null;
  try {
            String query = "SELECT * FROM notification where id_notification="+n.getId_notification();
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
            int id_user=rs.getInt(5);
            String type=rs.getString(2);
            String text= rs.getString(3);
            LocalDate date= rs.getDate(4).toLocalDate();
            N = new Notification(id,id_user,type,text,date);
           
            }
        } catch (SQLException ex) {
            System.out.println("notification n'existe pas");
        }
    return N;
    }
*/
    @Override
    public ArrayList<Notification> getListNotifications() {
             ArrayList<Notification> listN = new ArrayList<Notification>();
  try {
            String query = "SELECT * FROM notification";
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
            String text=rs.getString(3);
            String type=rs.getString(2);
            LocalDate date= rs.getDate(4).toLocalDate();
            int id_user=rs.getInt(5);
            int etat=rs.getInt(6);
            int id_origine=rs.getInt(7);  
            String image = rs.getString(8);
             Notification e = new Notification(id,etat,id_user,id_origine,type,text,date,image);
             listN.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listN;
    }

    @Override
    public ArrayList<Notification> getMYListNotifications(int id_user) {
                   ArrayList<Notification> listN = new ArrayList<Notification>();
  try {
            String query = "SELECT * FROM notification WHERE id_user_notif="+id_user;
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
            String text=rs.getString(3);
            String type=rs.getString(2);
            LocalDate date= rs.getDate(4).toLocalDate();
            int id_user_not=rs.getInt(5);
            int etat=rs.getInt(6);
             int id_origine=rs.getInt(7);  
             String image = rs.getString(8);
             Notification e = new  Notification(id,etat,id_user,id_origine,type,text,date,image);
             listN.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listN;
    }

    @Override
    public ArrayList<Notification> getNotifUnread(int id_user) {
      ArrayList<Notification> listN = new ArrayList<Notification>();
  try {
            String query = "SELECT * FROM notification WHERE id_user_notif="+id_user+ " and etat_notif = 0 " ;
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
            String text=rs.getString(3);
            String type=rs.getString(2);
            LocalDate date= rs.getDate(4).toLocalDate();
            int id_user_not=rs.getInt(5);
            int etat=rs.getInt(6);
             int id_origine=rs.getInt(7);  
             String image=rs.getString(8);
             Notification e = new Notification(id,etat,id_user,id_origine,type,text,date,image);
             listN.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listN;
    }

    @Override
    public void setNotifEtat(Notification n) {
       try{
          
            String query = "UPDATE notification SET etat_notif = 1 WHERE id_notification = "+ n.getId_notification();
           Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
      
       }
       catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void deleteNotif(int id) {
      try {
            String query = "DELETE FROM notification WHERE id_origine="+id;
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("notif Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    
    }

   
    
}
