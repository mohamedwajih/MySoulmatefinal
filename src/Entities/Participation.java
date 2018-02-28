/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ste GHRIB-INFO
 */
public class Participation {
    int id,id_user,id_event;

    public Participation(int id, int id_user, int id_event) {
        this.id = id;
        this.id_user = id_user;
        this.id_event = id_event;
    }

    public Participation() {
    }

    public Participation(int id_user, int id_event) {
        this.id_user = id_user;
        this.id_event = id_event;
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

    @Override
    public String toString() {
        return "Perticipation{" + "id=" + id + ", id_user=" + id_user + ", id_event=" + id_event + '}';
    }
    
    
}
