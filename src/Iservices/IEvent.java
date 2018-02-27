/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

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
    public ArrayList<Event> getListEvents();
    public void setEvent(Event e);
    public void deleteEvent(Event e);
    public ArrayList<String> getListTypeEvents();
    public ArrayList<Event> getListEventsUser(int id_user_ev);
    public ArrayList<Integer> getIdUserCibleEvent(Event e);

    
}
