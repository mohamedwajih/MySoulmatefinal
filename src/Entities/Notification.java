package Entities;

import java.time.LocalDate;
import java.util.Date;

public class Notification {
    int id_notification ,etat,id_user_notif;
    String type_notification,texte_notification,date_notification2;
    LocalDate date_notification;

    public Notification(int id_notification, int etat, int id_user_notif, String type_notification, String texte_notification, LocalDate date_notification) {
        this.id_notification = id_notification;
        this.etat = etat;
        this.id_user_notif = id_user_notif;
        this.type_notification = type_notification;
        this.texte_notification = texte_notification;
        this.date_notification = date_notification;
    }

    public Notification(int etat, int id_user_notif, String type_notification, String texte_notification, LocalDate date_notification) {
        this.etat = etat;
        this.id_user_notif = id_user_notif;
        this.type_notification = type_notification;
        this.texte_notification = texte_notification;
        this.date_notification = date_notification;
    }

    public Notification(int etat, int id_user_notif, String type_notification, String texte_notification, String date_notification2) {
        this.etat = etat;
        this.id_user_notif = id_user_notif;
        this.type_notification = type_notification;
        this.texte_notification = texte_notification;
        this.date_notification2 = date_notification2;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getId_user_notif() {
        return id_user_notif;
    }

    public void setId_user_notif(int id_user_notif) {
        this.id_user_notif = id_user_notif;
    }
    


   

    public Notification() {
    }
    

    public int getId_notification() {
        return id_notification;
    }

    public String getType_notification() {
        return type_notification;
    }

    public String getTexte_notification() {
        return texte_notification;
    }

    public LocalDate getDate_notification() {
        return date_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public void setType_notification(String type_notification) {
        this.type_notification = type_notification;
    }

    public String getDate_notification2() {
        return date_notification2;
    }

    public void setTexte_notification(String texte_notification) {
        this.texte_notification = texte_notification;
    }

    public void setDate_notification(LocalDate date_notification) {
        this.date_notification = date_notification;
    }
    public void setDate_notification2(String date,int hh,int mm) {
        this.date_notification2 =date+" "+hh+":"+mm+":00";
    }
    

    @Override
    public String toString() {
        return "Notification: \n "+ texte_notification + "\n Date" + date_notification + '}';
    }

   
}
