/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Event;
import Entities.Notification;
import java.util.ArrayList;

/**
 *
 * @author Ste GHRIB-INFO
 */
public interface INotification {
    public void addNotification(Notification n);
    //public Notification getNotification(Notification n);
    public ArrayList<Notification> getListNotifications();
    public ArrayList<Notification> getMYListNotifications(int id_user);
    public ArrayList<Notification> getNotifUnread(int id_user);
    public void setNotifEtat(Notification n);
<<<<<<< HEAD
<<<<<<< HEAD
     public void deleteNotif(int id);
=======
=======
>>>>>>> 11d6054ad30d2d9224d93ec307caeae86a12c2d9
    public void deleteNotif(int id);
    
>>>>>>> origin/master
  
}
