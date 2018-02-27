/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

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

    public void deleteNotif(int id);
 
  
}
