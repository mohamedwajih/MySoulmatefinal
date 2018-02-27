/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Event;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ste GHRIB-INFO
 */
public interface IEvent {
    
    public void addEvent(Event v);
    public Event getEvent(int id_event);
    public int getIdEvent(Event e);
    public ArrayList<Event> getListEvents();
    public void setEvent(Event e);
    public void deleteEvent(Event e);
    public ArrayList<String> getListTypeEvents();
    public ArrayList<Event> getListEventsUser(int id_user_ev);
    public ArrayList<Integer> getIdUserCibleEvent(Event e);
    public void setParticipation(Event e);
    public void archverEvennement(Event e);
    public ArrayList<Event> getArchive(int id_user);
    public Event getEventArchiv(int id);
    public void deletEventArchive(Event e);
    public ArrayList<Event> getListEventPassé();
    public ArrayList<Event> getListEventsUserPassé(int id_user_ev);
    public List<Event> rechercher(String event) ;
     public void modifierImage(String image,int id);
    public void mapInitialized();

    

}
