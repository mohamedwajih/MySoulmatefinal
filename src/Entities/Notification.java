package Entities;

import java.time.LocalDate;
import java.util.Date;

public class Notification {
    int id_notification ,etat,id_user_notif,id_origine;
    String type_notification,texte_notification,image;
    LocalDate date_notification;

    public Notification(int etat, int id_user_notif, int id_origine, String type_notification, String texte_notification, LocalDate date_notification,String image) {
        this.etat = etat;
        this.id_user_notif = id_user_notif;
        this.id_origine = id_origine;
        this.type_notification = type_notification;
        this.texte_notification = texte_notification;
        this.date_notification = date_notification;
        this.image= image;
    }

    public Notification(int id_notification, int etat, int id_user_notif, int id_origine, String type_notification, String texte_notification, LocalDate date_notification,String image) {
        this.id_notification = id_notification;
        this.etat = etat;
        this.id_user_notif = id_user_notif;
        this.id_origine = id_origine;
        this.type_notification = type_notification;
        this.texte_notification = texte_notification;
        this.date_notification = date_notification;
        this.image=image;
    }

    public int getId_origine() {
        return id_origine;
    }

    public void setId_origine(int id_origine) {
        this.id_origine = id_origine;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setTexte_notification(String texte_notification) {
        this.texte_notification = texte_notification;
    }

    public void setDate_notification(LocalDate date_notification) {
        this.date_notification = date_notification;
    }

    @Override
    public String toString() {
        return "Notification: \n "+ texte_notification + "\n Date" + date_notification ;
    }

   
}
