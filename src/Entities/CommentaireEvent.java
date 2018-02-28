/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import sun.util.resources.LocaleData;

/**
 *
 * @author Ste GHRIB-INFO
 */
public class CommentaireEvent {
    int id,id_user,id_event;
    String text;
    LocalDate date;

    public CommentaireEvent() {
    }

    public CommentaireEvent(int id, int id_user, int id_event, String text,LocalDate date) {
        this.id = id;
        this.id_user = id_user;
        this.id_event = id_event;
        this.text = text;
        this.date=date;
    }

    public CommentaireEvent(int id_user, int id_event, String text,LocalDate date) {
        this.id_user = id_user;
        this.id_event = id_event;
        this.text = text;
        this.date= date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
