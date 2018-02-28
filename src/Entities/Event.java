
package Entities;

import java.time.LocalDate;
import java.util.Date;


public class Event {
    int id_Event , id_user_event, part;
    String type_Event,titre_Event,texte_Event,lieu_Event,image;
    
    LocalDate date_Event;

   
    public Event(int id_Event, int id_user_event, String type_Event, String titre_Event, String texte_Event, String lieu_Event, LocalDate date_Event,int part,String image) {
        this.id_Event = id_Event;
        this.id_user_event = id_user_event;
        this.type_Event = type_Event;
        this.titre_Event = titre_Event;
        this.texte_Event = texte_Event;
        this.lieu_Event = lieu_Event;
        this.date_Event = date_Event;
        this.part=part;
        this.image=image;
                }

    public Event(int id_user_event, String type_Event, String titre_Event, String texte_Event, String lieu_Event, LocalDate date_Event,int part,String image) {
        this.id_user_event = id_user_event;
        this.type_Event = type_Event;
        this.titre_Event = titre_Event;
        this.texte_Event = texte_Event;
        this.lieu_Event = lieu_Event;
        this.date_Event = date_Event;
        this.part=part;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

   

    public Event() {
    }

    public int getId_Event() {
        return id_Event;
    }

    public String getType_Event() {
        return type_Event;
    }

    public String getTitre_Event() {
        return titre_Event;
    }

    public String getTexte_Event() {
        return texte_Event;
    }

    public String getLieu_Event() {
        return lieu_Event;
    }

    public LocalDate getDate_Event() {
        return date_Event;
    }

    public void setId_Event(int id_Event) {
        this.id_Event = id_Event;
    }

    public void setType_Event(String type_Event) {
        this.type_Event = type_Event;
    }

    public void setTitre_Event(String titre_Event) {
        this.titre_Event = titre_Event;
    }

    public void setTexte_Event(String texte_Event) {
        this.texte_Event = texte_Event;
    }

    public void setLieu_Event(String lieu_Event) {
        this.lieu_Event = lieu_Event;
    }

    public void setDate_Event(LocalDate date_Event) {
        this.date_Event = date_Event;
    }

    public int getId_user_event() {
        return id_user_event;
    }

    public void setId_user_event(int id_user_event) {
        this.id_user_event = id_user_event;
    }

   

    @Override
    public String toString() {
        return titre_Event +  "\n" + lieu_Event + "\n" + date_Event +"\n  Participant:"+part;
    }

    
    
}
